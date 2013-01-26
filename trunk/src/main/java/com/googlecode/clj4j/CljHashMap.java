package com.googlecode.clj4j;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import clojure.lang.ITransientMap;
import clojure.lang.PersistentHashMap;

/**
 * Aggregates Clojure's {@link PersistentHashMap}, adding support for generics and Java sweeteners.
 * This is a truly immutable map for Java.
 * @author Jason Smith
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public class CljHashMap<K,V> implements ImmutableMap<K,V>, Cloneable
{
	private final PersistentHashMap map;

	private static final String UNSUPPORTED_MESSAGE = "Object is immutable.";
	
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
		return map.toString();
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
