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
import edu.cs.hm.cb.compiler.scanner.Token;
import edu.cs.hm.cb.compiler.scanner.interfaces.IToken;


public class Factory implements IFactory
{
	private static Factory instance = null;


	private Factory ()
	{

	}


	public static Factory getInstance ()
	{
		if (instance == null)
		{
			instance = new Factory ();
		}

		return instance;
	}


	/**
	 * Creates a rule system.
	 * 
	 * @param query
	 * @param rules
	 */
	@Override
	public IRuleSystem createRuleSystem (IQuery query, IRule[] rules)
	{
		IRuleSystem ruleSystem = new RuleSystem (query);

		for (IRule rule : rules)
		{
			((RuleSystem) ruleSystem).addRule (rule);
		}

		return ruleSystem;
	}


	/**
	 * Creates a new rule.
	 * 
	 * @param head
	 *            the method name
	 * @param body
	 *            the list of arguments
	 */
	@Override
	public IRule createRule (IPredicate head, IPredicate[] body)
	{
		IRule rule = new Rule (head);

		for (IPredicate predicate : body)
		{
			((Rule) rule).addPredicate (predicate);
		}

		return rule;
	}


	/**
	 * Creates a new operator.
	 * 
	 * @param name
	 *            the name of the operator which is the pattern of the token.
	 */
	@Override
	public IOperator createOperator (IToken token)
	{
		return new Operator ((Token) token);
	}


	/**
	 * Creates a new query.
	 * 
	 * @param body
	 *            a list of predicates
	 */
	@Override
	public IQuery createQuery (IPredicate[] body)
	{
		IQuery query = new Query ();

		for (IPredicate predicate : body)
		{
			((Query) query).addPredicate (predicate);
		}

		return query;
	}


	/**
	 * Creates a new predicate
	 * Format
	 * Operator : '(' TermList ')'
	 * ConstantNamed
	 */
	@Override
	public IPredicate createPredicate (IOperator operator, ITerm[] arguments)
	{
		IPredicate predicate = new Predicate (operator);

		for (ITerm term : arguments)
		{
			((Predicate) predicate).addTerm (term);
		}

		return predicate;
	}


	/**
	 * Creates a new term with an operator and arguments.
	 */
	@Override
	public ITerm createTerm (IOperator operator, ITerm[] arguments)
	{
		ITerm term = null;
		
		if (operator.getType ().getName ().equals ("Variable"))
		{
			term = createVariable (operator.getName ());
		}
		else if (operator.getType ().getName ().equals ("String"))
		{
			term = createConstantString (operator.getName ());
		}
		else if (operator.getType ().getName ().equals ("Constant"))
		{
			term = createConstantNamed (operator);
		}
		else if (arguments.length > 0)
		{
			term = new TermComposed (operator);
			
			for (ITerm argument : arguments)
			{
				((TermComposed) term).add (argument);
			}
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


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createConstantNamed(
	 * edu.cs.hm.cb.compiler.parser.interfaces.IOperator)
	 */
	@Override
	public ConstantNamed createConstantNamed (IOperator operator)
	{
		return new ConstantNamed (operator);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.IFactory#createConstantString
	 * (java.lang.String)
	 */
	@Override
	public ConstantString createConstantString (String string)
	{
		return new ConstantString (string);
	}

}
