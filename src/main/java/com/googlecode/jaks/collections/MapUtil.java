package com.googlecode.jaks.collections;

import java.util.Map;

public class MapUtil 
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
}
