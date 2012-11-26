//
// MLogParser.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

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
	/** Indicates whether the trace should be printed. */
	private boolean printTrace;
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
	private final String TOKEN_STRING = "String";
	private final String TOKEN_VARIABLE = "Variable";


	/**
	 * Creates a new mlog parser in LL1.
	 */
	public MLogParser (boolean printTrace)
	{
		this.printTrace = printTrace;

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

//		System.out.printf ("%-20s %-9s   %-9s   %s   %s   %s\n", "Token",
//				"From", "To", "Filter", "Variable", "Pattern");
//		System.out
//				.println ("------------------------------------------------------------------------");
//
//		IToken token = null;
//		while ((token = scanner.get ()) != null)
//		{
//			System.out.println (token);
//		}

		boolean accept = deriveRuleSystem ();
		if (accept)
		{
			accept = scanner.get ().getTokenClass ().getName ().equals ("eof");
		}
		
		log ("accepted: " + accept);
		log ("get: " + scanner.get ());
		log (stack.top ().toString ());
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
				
				stack.push (factory.createRuleSystem ((IQuery) stack.pop (), rules)); 

				leave ("ruleSystem");
				return true;
			}
		}

		leave ("ruleSystem");

		return false;
	}


	@Override
	public boolean deriveRule ()
	{
		enter ("rule");
		
		if (deriveRuleHead ())
		{
			if (deriveRuleBody ())
			{
				
			}
			
			IToken token = scanner.get ();
			
			if (token.getPattern ().equals ("."))
			{
				stack.push (factory.createRule ((IPredicate) stack.pop (), new IPredicate[0]));
				
				leave ("rule");
				return true;
			}
			else
			{
				scanner.unget (token);
			}
		}
		
		leave ("rule");
		return false;
	}
	
	
	public boolean deriveRuleHead ()
	{
		enter ("ruleHead");
		
		if (derivePredicate ())
		{
			leave ("ruleHead");
			return true;
		}
		
		leave ("ruleHead");
		return false;
	}
	
	
	public boolean deriveRuleBody ()
	{
		enter ("ruleBody");
		
		IToken token = scanner.get ();
		
		leave ("ruleBody");
		return false;
	}
	
	
	public int deriveRuleList (int rules)
	{
		enter ("ruleList");

		if (deriveRule ())
		{
			rules = deriveRuleList (++rules);
		}

		leave ("ruleList");
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
			IToken token = scanner.get ();
			if (token.getPattern ().equals ("("))
			{
				int listSize = 0;
				if ((listSize = deriveTermList (0)) > 0)
				{
					token = scanner.get ();
					if (token.getPattern ().equals (")"))
					{
						ITerm[] terms = new ITerm[listSize];

						for (int i = listSize - 1; i >= 0; i--)
						{
							ITerm term = (ITerm) stack.pop ();
							// trace ("deriveOperator: " + term);
							terms[i] = term;
						}

						stack.push (factory.createPredicate (
								(IOperator) stack.pop (), terms));

						leave ("predicate");
						return true;
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
		}
		else if (deriveConstantNamed ())
		{
			leave ("predicate");
			return true;
		}

		leave ("predicate");
		return false;
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

		leave ("predicateList");
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

		leave ("predicateList");
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

		leave ("termList");
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
		leave ("constantNamed");

		return result;
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

			leave ("operator");
			return true;
		}
		else
		{
			scanner.unget (token);

			leave ("operator");
			return false;
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
		if (deriveConstantNamed ())
		{
			stack.push (factory.createTerm ((IOperator) stack.pop (), null));

			leave ("term");
			return true;
		}
		else if (deriveVariable ())
		{
			stack.push (factory.createVariable (((IToken) stack.pop ())
					.getPattern ()));

			leave ("term");
			return true;
		}
		else if (deriveConstantString ())
		{
			leave ("term");
			return true;
		}
		else if (derivePredicate ())
		{
			leave ("term");
			return true;
		}

		leave ("term");
		return false;
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
		if (token.getTokenClass ().getName ().equals (TOKEN_VARIABLE))
		{
			// trace (token);
			stack.push (token);

			leave ("variable");
			return true;
		}
		else if (deriveAnonymousVariable ())
		{
			leave ("variable");
			return true;
		}
		else
		{
			scanner.unget (token);
		}

		leave ("variable");
		return false;
	}


	/**
	 * Derive an anonymous variable.
	 * 
	 * Format
	 * '_'
	 */
	public boolean deriveAnonymousVariable ()
	{
		enter ("anonymousVariable");
		IToken token = scanner.get ();
		if (token.getTokenClass ().getName ().equals (TOKEN_ANONYMOUS_VARIABLE))
		{
			stack.push (token);

			leave ("anonymousVariable");
			return true;
		}
		else
		{
			scanner.unget (token);
		}

		leave ("anonymousVariable");
		return false;
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
			// trace (token);
			stack.push (token);

			leave ("constantString");
			return true;
		}
		else
		{
			scanner.unget (token);
		}

		leave ("constantString");
		return false;
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

					leave ("query");
					return true;
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

		leave ("query");

		return false;
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
		trace (">> " + method);

		offset += "  ";
	}


	/**
	 * Called at the end of a method to trace the route of the parser.
	 * 
	 * @param method
	 *            the name of the left method
	 */
	private void leave (String method)
	{
		if (offset.length () >= 2)
		{
			offset = offset.substring (0, offset.length () - 2);
		}

		trace ("<< " + method);
	}


	/**
	 * Called after entering of leaving a method.
	 * 
	 * @param method
	 *            the name of the method
	 */
	private void trace (String method)
	{
		if (printTrace)
		{
			System.out.print ("\n" + offset + method);
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
		System.out.print ("\nLOG\t" + message);
	}
}
