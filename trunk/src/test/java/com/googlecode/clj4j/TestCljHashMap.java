package com.googlecode.clj4j;

import static com.googlecode.clj4j.Clj4jUtil.e;
import static com.googlecode.clj4j.Clj4jUtil.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestCljHashMap extends Assert
{
	@Test
	public void testEquals()
	{
		final ImmutableMap<Integer,String> a = map(e(1, "A"), e(2, "B"));
		
		final Map<Integer,String> b = new HashMap<>();
		b.put(1, "A");
		b.put(2, "B");
		
		assertTrue("Maps were not equal as expected.", a.equals(b));
		
		b.put(3, "C");
		assertFalse("Maps were equal, but were expected to be unequal.", a.equals(b));
	}
	
	@Test
	public void testContainsValue()
	{
		final ImmutableMap<Integer,String> a = map(e(1, "A"), e(2, "B"));
		assertTrue("Missing expected value 'A'.", a.containsValue("A"));
		assertTrue("Missing expected value 'B'.", a.containsValue("B"));
		assertFalse("Contains unexpected value 'C'.", a.containsValue("C"));
	}
	
	@Test
	public void testContainsKey()
	{
		final ImmutableMap<Integer,String> a = map(e(1, "A"), e(2, "B"));
		assertTrue("Missing expected key '1'.", a.containsKey(1));
		assertTrue("Missing expected key '2'.", a.containsKey(2));
		assertFalse("Contains unexpected key '3'.", a.containsKey(3));
	}
}
