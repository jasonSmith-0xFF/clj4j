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
import java.util.TreeSet;

import clojure.lang.PersistentTreeSet;

public class CljTreeSet<T> implements ImmutableSet<T>, Cloneable
{
	final PersistentTreeSet set;
	
	/**
	 * Constructor.
	 * @param values The collection of initial values.
	 */
	@SafeVarargs
	public CljTreeSet(final T... values)
	{
		PersistentTreeSet ret = PersistentTreeSet.EMPTY;
		if(values != null)
		{
			for(final T value : values)
			{
				ret = (PersistentTreeSet)ret.cons(value);
			}
		}
		this.set = ret;
	}
	
	public CljTreeSet(final Collection<T> values)
	{
		if(values instanceof CljTreeSet)
		{
			this.set = ((CljTreeSet<T>)values).set;
		}
		else if(values instanceof PersistentTreeSet)
		{
			this.set = (PersistentTreeSet)values;
		}
		else
		{
			PersistentTreeSet ret = PersistentTreeSet.EMPTY;
			if(values != null)
			{
				for(final T value : values)
				{
					ret = (PersistentTreeSet)ret.cons(value);
				}
			}
			this.set = ret;
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

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(final T[] a) 
	{
		return (T[]) set.toArray(a);
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
	public boolean removeAll(Collection<?> c) 
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
		return new CljTreeSet<T>(this);
	}

	@Override
	public String toString() 
	{
		return new TreeSet<T>(this).toString();
	}
}
