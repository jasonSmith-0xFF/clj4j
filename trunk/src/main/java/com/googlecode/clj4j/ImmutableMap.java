package com.googlecode.clj4j;

import java.util.Map;

public interface ImmutableMap<K,V> extends Map<K,V>
{
	ImmutableMap<K,V> assoc(K key, V val);

	ImmutableMap<K,V> without(K key) ;
}
