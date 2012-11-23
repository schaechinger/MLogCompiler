//
// Factory.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.List;

import edu.cs.hm.cb.compiler.parser.interfaces.IFactory;
import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;
import edu.cs.hm.cb.compiler.parser.interfaces.IPredicate;
import edu.cs.hm.cb.compiler.parser.interfaces.IQuery;
import edu.cs.hm.cb.compiler.parser.interfaces.IRule;
import edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem;
import edu.cs.hm.cb.compiler.parser.interfaces.ITerm;


public class Factory implements IFactory
{
	private static Factory instance = null;
	
	
	private Factory ()
	{
		
	}
	
	
	public Factory getInstance ()
	{
		if (instance == null)
		{
			instance = new Factory ();
		}
		
		return instance;
	}
	
	
	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createRuleSystem(edu.cs.hm.cb.compiler.parser.interfaces.IQuery, java.util.List)
	 */
	@Override
	public IRuleSystem createRuleSystem (IQuery query, List<IRule> rules)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createRule(edu.cs.hm.cb.compiler.parser.interfaces.IPredicate, edu.cs.hm.cb.compiler.parser.interfaces.IPredicate[])
	 */
	@Override
	public IRule createRule (IPredicate head, IPredicate[] body)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createQuery(edu.cs.hm.cb.compiler.parser.interfaces.IPredicate[])
	 */
	@Override
	public IQuery createQuery (IPredicate[] body)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createPredicate(edu.cs.hm.cb.compiler.parser.interfaces.IOperator, edu.cs.hm.cb.compiler.parser.interfaces.ITerm[])
	 */
	@Override
	public IPredicate createPredicate (IOperator operator, ITerm[] arguments)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates a new term with an operator and arguments.
	 */
	@Override
	public ITerm createTerm (IOperator operator, ITerm[] arguments)
	{
		ITerm term = null;
		
		if (arguments.length > 0)
		{
			term = new TermComposed (operator);
		}
		else if (operator.getType ().getName ().equals ("Variable"))
		{
			term = createVariable (operator.getName ());
		}
		else if (operator.getType ().getName ().equals ("String"))
		{
			term = createConstantString (operator.getName ());
		}
		else
		{
			term = createConstantNamed (operator);
		}
		
		for (ITerm argument : arguments)
		{
			((TermComposed) term).add (argument);
		}
		
		return term;
	}

	/**
	 * Creates a new variable or an anonymousVariable if the string is '_'.
	 */
	@Override
	public Variable createVariable (String string)
	{
		if (string.equals ("_"))
		{
			return new AnonymousVariable ();
		}
		else
		{
			return new Variable (string);
		}
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createConstantNamed(edu.cs.hm.cb.compiler.parser.interfaces.IOperator)
	 */
	@Override
	public ConstantNamed createConstantNamed (IOperator operator)
	{
		return new ConstantNamed (operator);
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createConstantString(java.lang.String)
	 */
	@Override
	public ConstantString createConstantString (String string)
	{
		return new ConstantString (string);
	}
	
}
