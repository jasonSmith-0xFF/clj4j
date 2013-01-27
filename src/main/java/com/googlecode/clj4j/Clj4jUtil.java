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

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public static <K,V> Map.Entry<K, V> e(final K key, final V value)
	{
		return entry(key, value);
	}
	
	@SafeVarargs
	public static <K,V> ImmutableMap<K,V> map(final Entry<K,V>... entries)
	{
		return new CljHashMap<>(entries);
	}
	
	@SafeVarargs
	public static <T> ImmutableList<T> list(final T... values)
	{
		return new CljVector<>(values);
	}
	
	public static <K,V> ImmutableMap<K,V> assoc(final ImmutableMap<K,V> map, final K key, final V value)
	{
		return map == null ? map(e(key,value)) : map.assoc(key, value);
	}
	
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
	
	@SuppressWarnings("unchecked")
	public static <K,V> ImmutableMap<K,V> assoc(final ImmutableMap<K,V> map, final Collection<Entry<K,V>> entries)
	{
		return assoc(map, entries.toArray(new Entry[entries.size()]));
	}
	
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
				result = result.conj(value);
			}
			return result;
		}
	}
	
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
				result = result.conj(value);
			}
			return result;
		}
	}
}
