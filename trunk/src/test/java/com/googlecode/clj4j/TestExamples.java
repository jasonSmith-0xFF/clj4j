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
 * This shows promise. Creating immutable maps and lists in the first place is 
 * pretty clear. Syntax for modifying structures of these things, since they have
 * to be reconstructed from the lowest level all the way up, is still a bit
 * iffy.
 * @author Jason Smith
 */
public class TestExamples extends Assert
{
	@Test
	public void exampleUpdateListContainedInMap()
	{
		final ImmutableMap<String,ImmutableList<Integer>> data = 
				map(
					e("A", list(1,2,3)), 
					e("B", list(4,5,6)), 
					e("C", list(7,8,9)));
		
		//TODO: Need a way to do this without specifying key twice.
		/*
		 * Append 11 to the list associated with key "B".
		 */
		System.out.println(data.assoc("B", conj(data.get("B"), 11)));
		
		/*
		 * Calling cons takes care of creating the list if it does not exist.
		 */
		System.out.println(data.assoc("D", conj(data.get("D"), 12)));
	}
	
	@Test
	public void exampleUpdateMapContainedInMap()
	{
		final ImmutableMap<String,ImmutableMap<String,Integer>> data = 
				map(
					e("A", map(e("A.1", 1), e("A.2", 2))), 
					e("B", map(e("B.3", 3), e("B.4", 4))));
		
		//TODO: Need a way to do this without specifying key twice.
		System.out.println(data.assoc("B", data.get("B").assoc("B.3", 99)));
	}
}
