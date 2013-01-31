/*
 * Copyright (C) 2013 by Jason Smith
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.clj4j;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import clojure.lang.RT;

/**
 * Utility methods.
 * @author Jason Smith
 */
public final class Clj4jUtil 
{
	/**
	 * Private constructor. This is a utilities class.
	 */
	private Clj4jUtil()
	{
	}
	
	static
	{
		/*
		 * This is necessary to force RT to initialize before trying
		 * to create an instance. Fixes a class-loader bug.
		 */
		@SuppressWarnings("unused")
		final Charset UTF8 = RT.UTF8;
	}

	/**
	 * Create an immutable instance of {@link Entry}.
	 * @param key They key.
	 * @param value The value.
	 * @return The key/value pair.
	 */
	public static <K,V> Map.Entry<K, V> entry(final K key, final V value)
	{
		return 
				new Map.Entry<K, V>() 
				{
					@Override
					public K getKey() 
					{
						return key;
					}
		
					@Override
					public V getValue() 
					{
						return value;
					}
		
					@Override
					public V setValue(V value) 
					{
						throw new UnsupportedOperationException("Object is immutable.");
					}
				};
	}

	/**
	 * Create an immutable instance of {@link Entry}. This is a shortened version
	 * of {@link #entry(Object, Object)}.
	 * @param key They key.
	 * @param value The value.
	 * @return The key/value pair.
	 */
	public static <K,V> Map.Entry<K, V> e(final K key, final V value)
	{
		return entry(key, value);
	}
	
	/**
	 * Construct an immutable map ({@link CljHashMap}) from a collection of entries.
	 * @param entries The collection of entries.
	 * @return The map.
	 */
	@SafeVarargs
	public static <K,V> ImmutableMap<K,V> map(final Entry<K,V>... entries)
	{
		return new CljHashMap<>(entries);
	}
	
	/**
	 * Construct an immutable sorted-map ({@link CljTreeMap}) from a collection of entries.
	 * @param entries The collection of entries.
	 * @return The sorted-map.
	 */
	@SafeVarargs
	public static <K,V> ImmutableMap<K,V> smap(final Entry<K,V>... entries)
	{
		return new CljTreeMap<>(entries);
	}

	/**
	 * Construct an immutable set ({@link CljHashSet}) from a collection of entries.
	 * @param values The values.
	 * @return The set.
	 */
	@SafeVarargs
	public static <T> ImmutableSet<T> set(final T... values)
	{
		return new CljHashSet<>(values);
	}
	
	/**
	 * Construct an immutable sorted-set ({@link CljTreeSet}) from a collection of entries.
	 * @param values The values.
	 * @return The set.
	 */
	@SafeVarargs
	public static <T> ImmutableSet<T> sset(final T... values)
	{
		return new CljTreeSet<>(values);
	}

	/**
	 * Construct an immutable list ({@link CljVector}) from a collection of values.
	 * @param values The values.
	 * @return The list.
	 */
	@SafeVarargs
	public static <T> ImmutableList<T> list(final T... values)
	{
		return new CljVector<>(values);
	}
	
	/**
	 * Construct a new immutable map with the given key and value. If the original map is {@code null},
	 * the initial map is created by calling {@link #map(Entry...)}. If the key already 
	 * exists, this replaces the old value with the new value.
	 * @param map The original map.
	 * @param key The key.
	 * @param value The value.
	 * @return The new version of the map with the key/value pair added.
	 */
	public static <K,V> ImmutableMap<K,V> assoc(final ImmutableMap<K,V> map, final K key, final V value)
	{
		return map == null ? map(e(key,value)) : map.assoc(key, value);
	}
	
	/**
	 * Construct a new immutable map, adding the given entries. If the original map is {@code null},
	 * the initial map is created by calling {@link #map(Entry...)}. If a key already 
	 * exists, this replaces the old value with the new value.
	 * @param map The original map.
	 * @param entries The entries to be added.
	 * @return The new version of the map.
	 */
	@SafeVarargs
	public static <K,V> ImmutableMap<K,V> assoc(final ImmutableMap<K,V> map, final Entry<K,V>... entries)
	{
		if(map == null)
		{
			return map(entries);
		}
		else
		{
			ImmutableMap<K,V> result = map;
			for(final Entry<K,V> entry : entries)
			{
				result = result.assoc(entry.getKey(), entry.getValue());
			}
			return result;
		}
	}
	
	/**
	 * Construct a new immutable map, adding the given entries. If the original map is {@code null},
	 * the initial map is created by calling {@link #map(Entry...)}. If a key already 
	 * exists, this replaces the old value with the new value.
	 * @param map The original map.
	 * @param entries The entries to be added.
	 * @return The new version of the map.
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> ImmutableMap<K,V> assoc(final ImmutableMap<K,V> map, final Collection<Entry<K,V>> entries)
	{
		return assoc(map, entries.toArray(new Entry[entries.size()]));
	}
	
	/**
	 * Construct a new immutable list with the values added. The values are
	 * added either to the beginning or the end, depending on the type of the 
	 * list. If the original list is {@code null}, {@link #list(Object...)} is
	 * called to get the initial list.
	 * @param list The original list.
	 * @param values The values to be added.
	 * @return The newly constructed list with values added.
	 */
	@SafeVarargs
	public static <T> ImmutableList<T> conj(final ImmutableList<T> list, final T... values)
	{
		if(list == null)
		{
			return list(values);
		}
		else
		{
			ImmutableList<T> result = list;
			for(final T value : values)
			{
				result = result.cons(value);
			}
			return result;
		}
	}
	
	/**
	 * Construct a new immutable list with the values added. The values are
	 * added either to the beginning or the end, depending on the type of the 
	 * list. If the original list is {@code null}, {@link #list(Object...)} is
	 * called to get the initial list.
	 * @param list The original list.
	 * @param values The values to be added.
	 * @return The newly constructed list with values added.
	 */
	public static <T> ImmutableList<T> conj(final ImmutableList<T> list, final Collection<T> values)
	{
		if(list == null)
		{
			return conj(Clj4jUtil.<T>list(), values);
		}
		else
		{
			ImmutableList<T> result = list;
			for(final T value : values)
			{
				result = result.cons(value);
			}
			return result;
		}
	}
}
