//
// Term.java
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
import edu.cs.hm.cb.compiler.parser.interfaces.ISubList;
import edu.cs.hm.cb.compiler.parser.interfaces.ITerm;


public abstract class Term implements ITerm
{
	protected List<ITerm> terms = null;
	protected IOperator operator = null;
	
	
	public Term ()
	{
		terms = new ArrayList<ITerm> ();
	}


	public Term (IOperator operator)
	{
		this ();
		
		this.operator = operator;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.ITerm#getRank()
	 */
	@Override
	public int getRank ()
	{
		return terms.size ();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.ITerm#getOperator()
	 */
	@Override
	public IOperator getOperator ()
	{
		return operator;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.ITerm#getTerm(int)
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
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.ITerm#iterator()
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
	 * edu.cs.hm.cb.compiler.parser.interfaces.ITerm#substitute(edu.cs.hm.cb
	 * .compiler.parser.interfaces.ISubList)
	 */
	@Override
	public ITerm substitute (ISubList sub)
	{
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.ITerm#unify(edu.cs.hm.cb.compiler
	 * .parser.interfaces.ITerm)
	 */
	@Override
	public ISubList unify (ITerm term)
	{
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * A term is equals to another one if the operator and all it's terms matches.
	 */
	@Override
	public boolean equals (Object other)
	{
		Term term = (Term) other;

		if (getOperator ().equals (term.getOperator ())
				&& getRank () == term.getRank ())
		{
			for (int i = 0; i < getRank (); i++)
			{
				if (!getTerm (i).equals (term.getTerm (i)))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public Object clone ()
	{
		// TODO clone
		return null;
	}

}
