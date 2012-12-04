//
// MLogParser.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.Application;
import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;
import edu.cs.hm.cb.compiler.parser.interfaces.IParser;
import edu.cs.hm.cb.compiler.parser.interfaces.IPredicate;
import edu.cs.hm.cb.compiler.parser.interfaces.IQuery;
import edu.cs.hm.cb.compiler.parser.interfaces.IRule;
import edu.cs.hm.cb.compiler.parser.interfaces.ITerm;
import edu.cs.hm.cb.compiler.scanner.interfaces.IScanner;
import edu.cs.hm.cb.compiler.scanner.interfaces.IToken;


/**
 * The implementation of the parser.
 * 
 * @author Manuel Schaechinger
 * 
 */
public class MLogParser implements IParser
{
	/** The number of method calls for the parser (used in log mode). */
	private int line = 1;
	/** The factory to create terms and other structure objects. */
	private Factory factory;
	/** The scanner that retrieves the next token. */
	private IScanner scanner = null;
	/** The stack where tokens can be stored. */
	private Stack stack = null;
	/** The tab offset for the tracer. */
	private String offset = "";

	private final String TOKEN_ANONYMOUS_VARIABLE = "_";
	private final String TOKEN_CONSTANT = "Constant";
	private final String TOKEN_INTEGER = "Integer";
	private final String TOKEN_STRING = "String";
	private final String TOKEN_VARIABLE = "Variable";


	/**
	 * Creates a new mlog parser in LL1.
	 */
	public MLogParser ()
	{
		factory = Factory.getInstance ();
		stack = Stack.getInstance ();
	}


	/**
	 * Parse the whole source file with help of the scanner.
	 */
	public void parse ()
	{
		if (scanner == null)
		{
			System.out.println ("Scanner not set!");
			return;
		}

		if (Application.log)
		{
			System.out.printf ("%-20s %-9s   %-9s   %s   %s   %s\n", "Token",
					"From", "To", "Filter", "Variable", "Pattern");
			System.out
					.println ("------------------------------------------------------------------------");
		}

		boolean accept = deriveRuleSystem ();
		if (accept)
		{
			accept = scanner.get ().getTokenClass ().getName ().equals ("eof");
		}

		log ("accepted: " + accept);
		log ("Next tokenClass: " + scanner.get ().getTokenClass ().getName ());
		log ("\n" + stack.top ().toString ());
	}


	@Override
	public boolean deriveRuleSystem ()
	{
		enter ("ruleSystem");

		if (deriveQuery ())
		{
			int listSize;
			if ((listSize = deriveRuleList (0)) > 0)
			{
				IRule[] rules = new IRule[listSize];

				for (int i = listSize - 1; i >= 0; i--)
				{
					IRule rule = (IRule) stack.pop ();
					rules[i] = rule;
				}

				stack.push (factory.createRuleSystem ((IQuery) stack.pop (),
						rules));

				return leave ("ruleSystem", true);
			}
		}

		return leave ("ruleSystem", false);
	}


	@Override
	public boolean deriveRule ()
	{
		enter ("rule");

		if (deriveRuleHead ())
		{
			IPredicate[] predicates = new IPredicate[0];

			if (deriveRuleBody ())
			{
				predicates = (IPredicate[]) stack.pop ();
			}

			IToken token = scanner.get ();

			if (token.getPattern ().equals ("."))
			{
				stack.push (factory.createRule ((IPredicate) stack.pop (),
						predicates));

				return leave ("rule", true);
			}
			else
			{
				scanner.unget (token);
			}
		}

		return leave ("rule", false);
	}


	public boolean deriveRuleHead ()
	{
		enter ("ruleHead");

		if (derivePredicate ())
		{
			return leave ("ruleHead", true);
		}

		return leave ("ruleHead", false);
	}


	public boolean deriveRuleBody ()
	{
		enter ("ruleBody");

		IToken token = scanner.get ();

		if (token.getPattern ().equals (":"))
		{
			IPredicate[] predicates = derivePredicateList ();

			if (predicates.length > 0)
			{
				stack.push (predicates);

				return leave ("ruleBody", true);
			}
		}
		else
		{
			scanner.unget (token);
		}

		return leave ("ruleBody", false);
	}


	public int deriveRuleList (int rules)
	{
		enter ("ruleList");

		if (deriveRule ())
		{
			rules = deriveRuleList (++rules);
		}

		leave ("ruleList", true);
		return rules;
	}


	/**
	 * Derive a predicate
	 * 
	 * Format
	 * Operator '(' TermList ')'
	 * ConstantNamed
	 */
	@Override
	public boolean derivePredicate ()
	{
		enter ("predicate");
		if (deriveOperator ())
		{
			ITerm[] terms = new ITerm[0];
			IToken token = scanner.get ();
			if (token.getPattern ().equals ("("))
			{
				int listSize = 0;
				if ((listSize = deriveTermList (0)) > 0)
				{
					token = scanner.get ();
					if (token.getPattern ().equals (")"))
					{
						terms = new ITerm[listSize];

						for (int i = listSize - 1; i >= 0; i--)
						{
							ITerm term = (ITerm) stack.pop ();
							terms[i] = term;
						}

						stack.push (factory.createPredicate (
								(IOperator) stack.pop (), terms));

						return leave ("predicate", true);
					}
					else
					{
						scanner.unget (token);
					}
				}
			}
			else
			{
				scanner.unget (token);
				stack.push (factory.createPredicate ((IOperator) stack.pop (),
						new ITerm[0]));

				return leave ("predicate", true);
			}
		}
		else if (deriveConstantNamed ())
		{
			return leave ("predicate", true);
		}

		return leave ("predicate", false);
	}


	public IPredicate[] derivePredicateList ()
	{
		enter ("predicateList");

		int listSize = predicateListLength (0);
		IPredicate[] predicates = new IPredicate[listSize];

		for (int i = listSize - 1; i >= 0; i--)
		{
			IPredicate predicate = (IPredicate) stack.pop ();
			predicates[i] = predicate;
		}

		leave ("predicateList", true);
		return predicates;
	}


	private int predicateListLength (int predicates)
	{
		enter ("predicateList");

		if (derivePredicate ())
		{
			predicates++;

			IToken token = scanner.get ();
			if (token.getPattern ().equals (","))
			{
				predicates = predicateListLength (predicates);
			}
			else
			{
				scanner.unget (token);
			}
		}

		leave ("predicateList", true);
		return predicates;
	}


	/**
	 * Derive a termList.
	 * 
	 * Format
	 * Term
	 * Term ',' TermList
	 * 
	 * @return
	 */
	public int deriveTermList (int terms)
	{
		enter ("termList");

		if (deriveTerm ())
		{
			terms++;

			IToken token = scanner.get ();
			if (token.getPattern ().equals (","))
			{
				terms = deriveTermList (terms);
			}
			else
			{
				scanner.unget (token);
			}
		}

		leave ("termList", true);
		return terms;
	}


	/**
	 * Derive a constantNamed.
	 * 
	 * Format
	 * Operator
	 * 
	 * @return
	 */
	public boolean deriveConstantNamed ()
	{
		enter ("constantNamed");
		boolean result = deriveOperator ();
		return leave ("constantNamed", result);
	}


	/**
	 * Derive an operator, if successful the operator is pushed to the stack.
	 * 
	 * Format
	 * <Constant>
	 */
	public boolean deriveOperator ()
	{
		enter ("operator");
		IToken token = scanner.get ();

		if (token.getTokenClass ().getName ().equals (TOKEN_CONSTANT))
		{
			// trace ("Operator :: " + token);
			stack.push (factory.createOperator (token));

			return leave ("operator", true);
		}
		else
		{
			scanner.unget (token);

			return leave ("operator", false);
		}
	}


	/**
	 * Derive a term.
	 * 
	 * Format
	 * ConstantNamed
	 * Variable
	 * ConstnatString
	 * Predicate
	 */
	@Override
	public boolean deriveTerm ()
	{
		enter ("term");
		if (derivePredicate ())
		{
			return leave ("term", true);
		}
		else if (deriveVariable ())
		{
			stack.push (factory.createVariable (((IToken) stack.pop ())
					.getPattern ()));

			return leave ("term", true);
		}
		else if (deriveConstantString ())
		{
			stack.push (factory.createConstantString (((IToken) stack.pop ())
					.getPattern ()));

			return leave ("term", true);
		}
		else if (deriveConstantInteger ())
		{
			stack.push (factory.createConstantInteger (Integer
					.parseInt (((IToken) stack.pop ()).getPattern ())));

			return leave ("term", true);
		}
		else if (deriveConstantNamed ())
		{
			stack.push (factory.createTerm ((IOperator) stack.pop (), null));

			return leave ("term", true);
		}
		else if (deriveConstantString ())
		{
			return leave ("term", true);
		}

		return leave ("term", false);
	}


	/**
	 * Derive a variable.
	 * 
	 * Format
	 * <Variable>
	 * AnonymousVariable
	 */
	public boolean deriveVariable ()
	{
		enter ("variable");
		IToken token = scanner.get ();
		if (deriveAnonymousVariable (token))
		{
			return leave ("variable", true);
		}
		else if (token.getTokenClass ().getName ().equals (TOKEN_VARIABLE))
		{
			stack.push (token);

			return leave ("variable", true);
		}
		else
		{
			scanner.unget (token);
		}

		return leave ("variable", false);
	}


	/**
	 * Derive an anonymous variable.
	 * 
	 * Format
	 * '_'
	 */
	public boolean deriveAnonymousVariable (IToken token)
	{
		enter ("anonymousVariable");
		if (token.getTokenClass ().getName ().equals (TOKEN_ANONYMOUS_VARIABLE))
		{
			stack.push (token);

			return leave ("anonymousVariable", true);
		}

		return leave ("anonymousVariable", false);
	}


	/**
	 * Derive a constantInteger.
	 * 
	 * Format
	 * <Integer>
	 */
	public boolean deriveConstantInteger ()
	{
		enter ("constantInteger");
		IToken token = scanner.get ();
		if (token.getTokenClass ().getName ().equals (TOKEN_INTEGER))
		{
			stack.push (token);

			return leave ("constantInteger", true);
		}
		else
		{
			scanner.unget (token);
		}

		return leave ("constantInteger", false);
	}


	/**
	 * Derive a constantString.
	 * 
	 * Format
	 * <String>
	 */
	public boolean deriveConstantString ()
	{
		enter ("constantString");
		IToken token = scanner.get ();
		if (token.getTokenClass ().getName ().equals (TOKEN_STRING))
		{
			stack.push (token);

			return leave ("constantString", true);
		}
		else
		{
			scanner.unget (token);
		}

		return leave ("constantString", false);
	}


	/**
	 * Derive a query.
	 * 
	 * Format
	 * ':' Predicate PredicateList '.'
	 */
	public boolean deriveQuery ()
	{
		enter ("query");
		IToken token = scanner.get ();

		if (token.getPattern ().equals (":"))
		{
			IPredicate[] predicates = derivePredicateList ();

			if (predicates.length > 0)
			{
				token = scanner.get ();

				if (token.getPattern ().equals ("."))
				{
					stack.push (factory.createQuery (predicates));

					return leave ("query", true);
				}
				else
				{
					scanner.unget (token);
				}
			}
		}
		else
		{
			scanner.unget (token);
		}

		return leave ("query", false);
	}


	@Override
	public void setScanner (IScanner scanner)
	{
		this.scanner = scanner;
	}


	/**
	 * Called at the start of a method to trace the route of the parser.
	 * 
	 * @param method
	 *            the name of the entered method
	 */
	private void enter (String method)
	{
		trace (">> " + method, true);

		offset += "  ";
	}


	/**
	 * Called when the parser creates an element.
	 * 
	 * @param element
	 *            the name of the created
	 */
	private void create (String element)
	{

	}


	/**
	 * Called at the end of a method to trace the route of the parser.
	 * 
	 * @param method
	 *            the name of the left method
	 */
	private boolean leave (String method, boolean returnState)
	{
		if (offset.length () >= 2)
		{
			offset = offset.substring (0, offset.length () - 2);
		}

		trace ("<< " + method + " [" + returnState + "]", false);

		return returnState;
	}


	/**
	 * Called after entering of leaving a method.
	 * 
	 * @param method
	 *            the name of the method
	 */
	private void trace (String method, boolean lineNumber)
	{
		if (Application.trace)
		{
			if (lineNumber)
			{
				System.out.printf ("%-5d%s%s\n", line++, offset, method);
			}
			else
			{
				System.out.printf ("%5s%s%s\n", "", offset, method);
			}
		}
	}


	/**
	 * Logs a message to the console.
	 * 
	 * @param message
	 *            the message that should be shown on the console.
	 */
	private void log (String message)
	{
		System.out.println ("\t" + message);
	}
}
