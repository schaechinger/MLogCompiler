//
// Query.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cs.hm.cb.compiler.parser.interfaces.IPredicate;
import edu.cs.hm.cb.compiler.parser.interfaces.IQuery;


public class Query implements IQuery
{
	private List<IPredicate> body = null;


	public Query ()
	{
		body = new ArrayList<IPredicate> ();
	}


	public void addPredicate (IPredicate predicate)
	{
		body.add (predicate);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IQuery#getLength()
	 */
	@Override
	public int getLength ()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IQuery#getBody(int)
	 */
	@Override
	public IPredicate getBody (int i)
	{
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IQuery#bodyIterator()
	 */
	@Override
	public Iterator<IPredicate> bodyIterator ()
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString ()
	{
		String info = ": ";

		for (IPredicate predicate : body)
		{
			info += predicate + " , ";
		}

		return info.substring (0, info.length () - 3) + " .";
	}
}
