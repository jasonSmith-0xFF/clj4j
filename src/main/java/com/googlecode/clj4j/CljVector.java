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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import clojure.lang.PersistentVector;

/**
 * Aggregates Clojure's {@link PersistentVector}, adding support for generics and Java sweeteners.
 * Clojure's vector can {@link #conj(Object)} to the end, {@link #peek()} and {@link #pop()} from 
 * the end in constant time. A value can be replaced quickly. Other operations require linear time.
 * It is similar to Java's {@link java.util.ArrayList} in performance characteristics.
 * @author Jason Smith
 *
 * @param <T> Value type.
 */
public class CljVector<T> implements ImmutableList<T>, Cloneable
{
	private final PersistentVector list;
	
	private static final String UNSUPPORTED_MESSAGE = "Object is immutable.";

	/**
	 * Constructor.
	 * @param values The ordered list of initial values.
	 */
	@SafeVarargs
	public CljVector(final T... values)
	{
		this.list = PersistentVector.create(values);
	}
	
	public CljVector(final List<T> list)
	{
		if(list instanceof CljVector)
		{
			this.list = ((CljVector<T>)list).list;
		}
		else if(list instanceof PersistentVector)
		{
			this.list = (PersistentVector)list;
		}
		else
		{
			this.list = PersistentVector.create(list);
		}
	}
	
	@Override
	public int size() 
	{
		return list.size();
	}

	@Override
	public boolean isEmpty() 
	{
		return list.isEmpty();
	}

	@Override
	public boolean contains(final Object o) 
	{
		return list.contains(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() 
	{
		return list.iterator();
	}

	@Override
	public Object[] toArray() 
	{
		return list.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V[] toArray(final V[] a) 
	{
		return (V[])list.toArray(a);
	}

	@Override
	public boolean add(final T e) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean remove(final Object o) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean containsAll(final Collection<?> c) 
	{
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(final Collection<? extends T> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean addAll(final int index, final Collection<? extends T> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean removeAll(Collection<?> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean retainAll(Collection<?> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public void clear() 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(final int index) 
	{
		return (T)list.get(index);
	}

	@Override
	public T set(final int index, final T element) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public void add(final int index, final T element) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public T remove(final int index) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public int indexOf(final Object o) 
	{
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) 
	{
		return list.lastIndexOf(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ListIterator<T> listIterator() 
	{
		return list.listIterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ListIterator<T> listIterator(final int index) 
	{
		return list.listIterator(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> subList(final int fromIndex, final int toIndex) 
	{
		return list.subList(fromIndex, toIndex);
	}
	
	@Override
	public int hashCode() 
	{
		return list.hashCode();
	}

	@Override
	public boolean equals(final Object obj) 
	{
		return list.equals(obj);
	}

	@Override
	protected ImmutableList<T> clone() throws CloneNotSupportedException 
	{
		return new CljVector<T>(this);
	}

	@Override
	public String toString() 
	{
		return list.toString();
	}

	/**
	 * Add the value to end of the vector. So <tt>[1, 2].cons(3)</tt> yields <tt>[1, 2, 3]</tt>.
	 * @param value Value to add.
	 * @return The constructed list with the value added.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final ImmutableList<T> conj(final T value) 
	{
		return new CljVector<T>(list.cons(value));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableList<T> assoc(final int i, final T val) 
	{
		return new CljVector<T>(list.assocN(i, val));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableList<T> pop() 
	{
		return new CljVector<T>(list.pop());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek() 
	{
		return (T)list.peek();
	}
}
