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
import java.util.Set;
import java.util.TreeMap;

import clojure.lang.PersistentTreeMap;
import clojure.lang.RT;

/**
 * Adapts Clojure's {@link PersistentTreeMap}, adding support for generics and Java sweeteners.
 * This is an immutable, persistent sorted map for Java.
 * @author Jason Smith
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class CljTreeMap<K,V> implements ImmutableMap<K,V>, Cloneable
{
	private final PersistentTreeMap map;
	
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
	 * Constructor. If this is a {@link PersistentTreeMap} or {@link CljTreeMap}, this is a <tt>O(1)</tt> operation.
	 * @param map The source map to copy.
	 */
	public CljTreeMap(final Map<K,V> map)
	{
		if(map instanceof PersistentTreeMap)
		{
			this.map = (PersistentTreeMap)map;
		}
		else if(map instanceof CljTreeMap)
		{
			this.map = ((CljTreeMap<K,V>)map).map;
		}
		else
		{
			this.map = (PersistentTreeMap)PersistentTreeMap.create(map);
		}
	}
	
	/**
	 * Constructor.
	 * @param entries The collection of entries.
	 */
	@SafeVarargs
	public CljTreeMap(final Map.Entry<K,V>... entries)
	{
		PersistentTreeMap ret = new PersistentTreeMap();
		if(entries != null)
		{
			for(final Map.Entry<K,V> entry : entries)
			{
				ret = ret.assoc(entry.getKey(), entry.getValue());
			}
		}
		this.map = ret;
	}
	
	@Override
	public int size() 
	{
		return map.size();
	}

	@Override
	public boolean isEmpty() 
	{
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(final Object key) 
	{
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) 
	{
		return map.containsValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(final Object key) 
	{
		return (V)map.get(key);
	}

	@Override
	public V put(final K key, final V value) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public V remove(final Object key) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public void putAll(final Map<? extends K, ? extends V> m) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public void clear() 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keySet() 
	{
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() 
	{
		return map.values();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() 
	{
		return map.entrySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableMap<K, V> assoc(final K key, final V val) 
	{
		return new CljTreeMap<K,V>((PersistentTreeMap)map.assoc(key, val));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableMap<K, V> without(final K key) 
	{
		return new CljTreeMap<K,V>((PersistentTreeMap)map.without(key));
	}
	
	@Override
	public int hashCode() 
	{
		return map.hashCode();
	}

	@Override
	public boolean equals(final Object obj) 
	{
		return map.equals(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected CljTreeMap<K,V> clone() throws CloneNotSupportedException 
	{
		return (CljTreeMap<K,V>)super.clone();
	}

	@Override
	public String toString() 
	{
		return new TreeMap<K,V>(this).toString();
	}
}
