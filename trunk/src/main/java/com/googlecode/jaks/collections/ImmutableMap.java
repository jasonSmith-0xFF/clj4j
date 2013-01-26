package com.googlecode.jaks.collections;

import java.util.Map;

public interface ImmutableMap<K,V> extends Map<K,V>
{
	ImmutableMap<K,V> assoc(K key, V val);

	ImmutableMap<K,V> without(K key) ;
}
