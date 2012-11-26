//
// Rule.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cs.hm.cb.compiler.parser.interfaces.IPredicate;
import edu.cs.hm.cb.compiler.parser.interfaces.IRule;


public class Rule implements IRule
{
	private IPredicate head = null;
	private List<IPredicate> body = null;
	
	
	public Rule (IPredicate head)
	{
		this.head = head;
		
		body = new ArrayList<IPredicate> ();
	}
	
	
	public void addPredicate (IPredicate predicate)
	{
		body.add (predicate);
	}
	
	
	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRule#getHead()
	 */
	@Override
	public IPredicate getHead ()
	{
		return head;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRule#getLength()
	 */
	@Override
	public int getLength ()
	{
		return body.size ();
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRule#getBody(int)
	 */
	@Override
	public IPredicate getBody (int i)
	{
		if (i < getLength ())
		{
			return body.get (i);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRule#bodyIterator()
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
		String info = "" + head;
		
		if (body.size () > 0)
		{
			info += " : ";
			
			for (IPredicate predicate : body)
			{
				info += predicate;
			}
		}
		
		return info;
	}
}
