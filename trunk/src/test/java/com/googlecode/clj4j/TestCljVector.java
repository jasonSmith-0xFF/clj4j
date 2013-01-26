package com.googlecode.clj4j;

import static com.googlecode.clj4j.Clj4jUtil.list;
import static java.util.Arrays.asList;

import org.junit.Assert;
import org.junit.Test;

public class TestCljVector extends Assert
{
	@Test
	public void testCons()
	{
		final ImmutableList<String> list = list("A", "B");
		
		assertEquals("Cons operation did not produce expected list.", 
				asList("A", "B", "C"), list.cons("C"));
	}
	
	@Test
	public void testAssocN()
	{
		final ImmutableList<String> list = list("A", "_", "C");
		
		assertEquals("Assoc operation did not produce expected list.",
				asList("A", "B", "C"), list.assoc(1, "B"));
	}
	
	@Test
	public void testPop()
	{
		final ImmutableList<String> list = list("A", "B", "C");
		
		assertEquals("Pop didn't produce the right list.",
				asList("A", "B"), list.pop());
	}
	
	@Test
	public void testPeek()
	{
		final ImmutableList<String> list = list("A", "B", "C");
		
		assertEquals("Peek didn't grab the expected value.",
				"C", list.peek());
	}
}
