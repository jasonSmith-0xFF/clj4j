package com.googlecode.clj4j;

import java.util.List;

public interface ImmutableList<T> extends List<T>
{
	/**
	 * Add the value to the optimal end of the list; the end depends on the implementation.
	 * @param value Value to add.
	 * @return The constructed list with the value added.
	 */
	ImmutableList<T> cons(T value);
	
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
