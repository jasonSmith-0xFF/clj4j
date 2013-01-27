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

import java.util.List;

public interface ImmutableList<T> extends List<T>
{
	/**
	 * Add the value to the optimal end of the list; the end depends on the implementation.
	 * @param values Value to add.
	 * @return The constructed list with the value added.
	 */
	ImmutableList<T> conj( final T value);
	
	/**
	 * Insert the value at the given index.
	 * @param i The index.
	 * @param val The value.
	 * @return The constructed list with the value added.
	 */
	ImmutableList<T> assoc(int i, final T val);
	
	ImmutableList<T> pop();
	
	T peek();
}
