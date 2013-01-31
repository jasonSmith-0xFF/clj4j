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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import clojure.lang.ITransientMap;
import clojure.lang.PersistentHashMap;

/**
 * Adapts Clojure's {@link PersistentHashMap}, adding support for generics and Java sweeteners.
 * This is immutable, persistent hash-map for Java.
 * @author Jason Smith
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class CljHashMap<K,V> implements ImmutableMap<K,V>, Cloneable
{
	private final PersistentHashMap map;

	/**
	 * Constructor. If this is a {@link PersistentHashMap} or {@link CljHashMap}, this is a <tt>O(1)</tt> operation.
	 * @param map The source map to copy.
	 */
	public CljHashMap(final Map<K,V> map)
	{
		if(map instanceof PersistentHashMap)
		{
			this.map = (PersistentHashMap)map;
		}
		else if(map instanceof CljHashMap)
		{
			this.map = ((CljHashMap<K,V>)map).map;
		}
		else
		{
			this.map = (PersistentHashMap)PersistentHashMap.create(map);
		}
	}
	
	/**
	 * Constructor.
	 * @param entries The collection of entries.
	 */
	@SafeVarargs
	public CljHashMap(final Map.Entry<K,V>... entries)
	{
		ITransientMap ret = PersistentHashMap.EMPTY.asTransient();
		if(entries != null)
		{
			for(final Map.Entry<K,V> entry : entries)
			{
				ret = ret.assoc(entry.getKey(), entry.getValue());
			}
		}
		this.map = (PersistentHashMap)ret.persistent();
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
	protected CljHashMap<K,V> clone() throws CloneNotSupportedException 
	{
		return (CljHashMap<K,V>)super.clone();
	}

	@Override
	public String toString() 
	{
		return new LinkedHashMap<K,V>(this).toString();
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
		return new CljHashMap<K,V>((PersistentHashMap)map.assoc(key, val));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableMap<K, V> without(final K key) 
	{
		return new CljHashMap<K,V>((PersistentHashMap)map.without(key));
	}
}
