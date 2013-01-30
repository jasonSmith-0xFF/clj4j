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

import static com.googlecode.clj4j.Clj4jUtil.e;
import static com.googlecode.clj4j.Clj4jUtil.smap;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestCljTreeMap extends Assert
{
	@Test
	public void testEquals()
	{
		final ImmutableMap<Integer,String> a = smap(e(1, "A"), e(2, "B"));
		
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
		final ImmutableMap<Integer,String> a = smap(e(1, "A"), e(2, "B"));
		System.out.println(a);
		assertTrue("Missing expected value 'A'.", a.containsValue("A"));
		assertTrue("Missing expected value 'B'.", a.containsValue("B"));
		assertFalse("Contains unexpected value 'C'.", a.containsValue("C"));
	}
	
	@Test
	public void testContainsKey()
	{
		final ImmutableMap<Integer,String> a = smap(e(1, "A"), e(2, "B"));
		assertTrue("Missing expected key '1'.", a.containsKey(1));
		assertTrue("Missing expected key '2'.", a.containsKey(2));
		assertFalse("Contains unexpected key '3'.", a.containsKey(3));
	}
}
