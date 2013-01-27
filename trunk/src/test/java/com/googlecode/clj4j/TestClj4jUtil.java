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

import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.clj4j.Clj4jUtil.*;

/**
 * Unit tests for static functions on {@link Clj4jUtil}.
 * @author Jason Smith
 */
public class TestClj4jUtil extends Assert
{
	/**
	 * I can construct a new list using {@link Clj4jUtil#conj(ImmutableList, java.util.Collection)}.
	 */
	@Test
	public void testConsWithCollection()
	{
		assertEquals("Conj did not produce the expected list.",
				list("A", "B", "C", "D"),
				conj(list("A", "B"), list("C", "D")));
	}
	
	/**
	 * I can construct a new list using {@link Clj4jUtil#conj(ImmutableList, Object...)}.
	 */
	@Test
	public void testConsWithVarargs()
	{
		assertEquals("Conj did not produce the expected list.",
				list("A", "B", "C", "D"),
				conj(list("A", "B"), "C", "D"));
	}
}
