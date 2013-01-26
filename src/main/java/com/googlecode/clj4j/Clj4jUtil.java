package com.googlecode.clj4j;

import java.util.Map;
import java.util.Map.Entry;

public class Clj4jUtil 
{
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
}
