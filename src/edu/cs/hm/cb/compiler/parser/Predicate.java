//
// Predicate.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;
import edu.cs.hm.cb.compiler.parser.interfaces.IPredicate;
import edu.cs.hm.cb.compiler.parser.interfaces.ISubList;
import edu.cs.hm.cb.compiler.parser.interfaces.ITerm;


public class Predicate implements IPredicate
{
	private IOperator operator = null;
	private List<ITerm> terms = null;


	public Predicate (IOperator operator)
	{
		this.operator = operator;

		terms = new ArrayList<ITerm> ();
	}


	public void addTerm (ITerm term)
	{
		terms.add (term);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#getRank()
	 */
	@Override
	public int getRank ()
	{
		return terms.size ();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#getOperator()
	 */
	@Override
	public IOperator getOperator ()
	{
		return operator;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#getTerm(int)
	 */
	@Override
	public ITerm getTerm (int i)
	{
		if (i < getRank ())
		{
			return terms.get (i);
		}

		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#iterator()
	 */
	@Override
	public Iterator<ITerm> iterator ()
	{
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#substitute(edu.cs.
	 * hm.cb.compiler.parser.interfaces.ISubList)
	 */
	@Override
	public IPredicate substitute (ISubList sub)
	{
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.IPredicate#unify(edu.cs.hm.cb
	 * .compiler.parser.interfaces.IPredicate)
	 */
	@Override
	public ISubList unify (IPredicate predicate)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString ()
	{
		String info = operator + " (";
		
		for (ITerm term : terms)
		{
			info += " " + term;
		}
		
		return info + " )";
	}
}
