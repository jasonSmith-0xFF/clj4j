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
