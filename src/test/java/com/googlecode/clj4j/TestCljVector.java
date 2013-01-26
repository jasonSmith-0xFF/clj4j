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
		assertEquals("Cons operation did not produce expected list.", 
				asList("A", "B", "C"), 
				list("A", "B").cons("C"));
	}
	
	@Test
	public void testAssocN()
	{
		assertEquals("Assoc operation did not produce expected list.",
				asList("A", "B", "C"), 
				list("A", "_", "C").assoc(1, "B"));
	}
	
	@Test
	public void testPop()
	{
		assertEquals("Pop didn't produce the right list.",
				asList("A", "B"), list("A", "B", "C").pop());
	}
	
	@Test
	public void testPeek()
	{
		assertEquals("Peek didn't grab the expected value.",
				"C", list("A", "B", "C").peek());
	}
}
