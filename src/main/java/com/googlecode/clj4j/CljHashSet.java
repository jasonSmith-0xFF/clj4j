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
import java.util.LinkedHashSet;

import clojure.lang.PersistentHashSet;

public class CljHashSet<T> implements ImmutableSet<T>, Cloneable
{
	final PersistentHashSet set;
	
	/**
	 * Constructor.
	 * @param values The collection of initial values.
	 */
	@SafeVarargs
	public CljHashSet(final T... values)
	{
		this.set = PersistentHashSet.create(values);
	}
	
	public CljHashSet(final Collection<T> values)
	{
		if(values instanceof CljHashSet)
		{
			this.set = ((CljHashSet<T>)values).set;
		}
		else if(values instanceof PersistentHashSet)
		{
			this.set = (PersistentHashSet)values;
		}
		else
		{
			this.set = PersistentHashSet.create(values);
		}
	}

	@Override
	public int size() 
	{
		return set.size();
	}

	@Override
	public boolean isEmpty() 
	{
		return set.isEmpty();
	}

	@Override
	public boolean contains(final Object o) 
	{
		return set.contains(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() 
	{
		return set.iterator();
	}

	@Override
	public Object[] toArray() 
	{
		return set.toArray();
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> T[] toArray(final T[] a) 
	{
		return (T[])set.toArray(a);
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
		return set.containsAll(c);
	}

	@Override
	public boolean addAll(final Collection<? extends T> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean retainAll(final Collection<?> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public boolean removeAll(final Collection<?> c) 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}

	@Override
	public void clear() 
	{
		throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
	}
	
	@Override
	public int hashCode() 
	{
		return set.hashCode();
	}

	@Override
	public boolean equals(final Object obj) 
	{
		return set.equals(obj);
	}

	@Override
	protected ImmutableSet<T> clone() throws CloneNotSupportedException 
	{
		return new CljHashSet<T>(this);
	}

	@Override
	public String toString() 
	{
		return new LinkedHashSet<T>(this).toString();
	}
}
