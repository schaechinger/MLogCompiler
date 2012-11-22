//
// LLParser.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IParser;
import edu.cs.hm.cb.compiler.scanner.DFA;
import edu.cs.hm.cb.compiler.scanner.Driver;
import edu.cs.hm.cb.compiler.scanner.TokenClassAdministrator;
import edu.cs.hm.cb.compiler.scanner.interfaces.IDFA;
import edu.cs.hm.cb.compiler.scanner.interfaces.INumberTokenClass;
import edu.cs.hm.cb.compiler.scanner.interfaces.IScanner;
import edu.cs.hm.cb.compiler.scanner.interfaces.IToken;


/**
 * The implementation of the parser.
 * 
 * @author Manuel Schaechinger
 *
 */
public class LLParser implements IParser
{
	/** The dfa with all the rules for tokens. */
	private IDFA		dfa = null;
	/** The number of read tokes at a time. */
	private int			lookahead;
	/** The scanner that retrieves the next token. */
	private IScanner	scanner = null;
	/** The stack where tokens can be stored. */
	private Stack		stack = null;
	
	
		/**
		 * Creates a new parser object and initializes it's object variables.
		 * 
		 * @param k is the number of lookaheads (parser is a LL(k) parser)
		 * @param structPath the path of the initialization file for the dfa
		 * @param sourcePath the path of the sourcecode
		 */
		public LLParser (int k, String structPath, String sourcePath)
		{
			lookahead = k;
			
			stack = new Stack ();
			dfa = new DFA ();
			scanner = new Driver (dfa, structPath, sourcePath);
		}
	
	
	/**
	 * Displays the structure of the dfa with all it's states and transitions.
	 */
	public void showTree ()
	{
		System.out.println (dfa);
		System.out.println (TokenClassAdministrator.getInstance ());
	}
	
	
	/**
	 * Parse the whole source file with help of the scanner.
	 */
	public void parse ()
	{
		showTree ();

		System.out.printf ("%-20s %-9s   %-9s   %s   %s   %s\n", "Token", "From", "To", "Filter", "Variable", "Pattern");
		System.out.println ("------------------------------------------------------------------------");
		
//		getNumber ();
		
		while (true)
		{
			IToken token = scanner.get ();
			if (token == null)
//			if (getArith ())
			{
				break;
			}
			
			System.out.println (token);
		}
		
//		System.out.println ("-----\nsolution: " + ((IToken) stack.pop ()).getPattern ());
	}
	
	
	/**
	 * Gets the next token, checks if it is a number and stores it in the stack if so.
	 * @return if the read token is a number
	 */
	private boolean getNumber ()
	{
		IToken token = null;
		
		if ((token = scanner.get ()) != null)
		{			
			if (token.getTokenClass ().getClass ().getInterfaces ()[0].equals (INumberTokenClass.class))
			{
				stack.push (token);
				
				return true;
			}
			else
			{
				scanner.unget (token);
			}
		}

		return false;
	}
	
	
	/**
	 * Read the next token, checks if it is an arithmetic expression and executes it's meaing with the upcoming number.
	 * @return if the operation was executed successfully
	 */
	private boolean getArith ()
	{
		IToken token = null;
		
		if ((token = scanner.get ()) != null)
		{
			if (getNumber ())
			{
				if (token.getTokenClass ().getName ().equals ("Add"))
				{
					add ();
				}
				else if (token.getTokenClass ().getName ().equals ("Sub"))
				{
					sub ();
				}
				else if (token.getTokenClass ().getName ().equals ("Div"))
				{
					div ();
				}
				else if (token.getTokenClass ().getName ().equals ("Mul"))
				{
					mul ();
				}
				else
				{
					return false;
				}
			}
			else
			{
				scanner.unget (token);
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	

	/**
	 * Takes the two next tokens on the stack and adds them.
	 */
	private void add ()
	{
		IToken secondSummand = (IToken) stack.pop ();
		IToken firstSummand = (IToken) stack.pop ();
		
		secondSummand.setPattern ("" +
				(parseDouble (firstSummand.getPattern ()) +
				 parseDouble (secondSummand.getPattern ())));
		stack.push (secondSummand);
	}
	

	/**
	 * Takes the two next tokens on the stack and substracts them.
	 */
	private void sub ()
	{
		IToken subdrahend = (IToken) stack.pop ();
		IToken minuend = (IToken) stack.pop ();
		
		subdrahend.setPattern ("" +
				(parseDouble (minuend.getPattern ()) -
				 parseDouble (subdrahend.getPattern ())));
		stack.push (subdrahend);
	}
	

	/**
	 * Takes the two next tokens on the stack and divides them.
	 */
	private void div ()
	{
		IToken divisor = (IToken) stack.pop ();
		IToken divident = (IToken) stack.pop ();
		
		divisor.setPattern ("" +
				(parseDouble (divident.getPattern ()) /
				 parseDouble (divisor.getPattern ())));
		stack.push (divisor);
	}
	

	/**
	 * Takes the two next tokens on the stack and multiplies them.
	 */
	private void mul ()
	{
		IToken secondFactor = (IToken) stack.pop ();
		IToken firstFactor = (IToken) stack.pop ();
		
		secondFactor.setPattern ("" +
				(parseDouble (firstFactor.getPattern ()) %
				 parseDouble (secondFactor.getPattern ())));
		stack.push (secondFactor);
	}
	
	
	private double parseDouble (String pattern)
	{
		return Double.parseDouble (pattern);
	}
}
