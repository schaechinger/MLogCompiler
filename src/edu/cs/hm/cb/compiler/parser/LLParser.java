//
// LLParser.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IParser;
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
		public LLParser (int k)
		{
			lookahead = k;
			
			stack = new Stack ();
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

		System.out.printf ("%-20s %-9s   %-9s   %s   %s   %s\n", "Token", "From", "To", "Filter", "Variable", "Pattern");
		System.out.println ("------------------------------------------------------------------------");
		
		while (true)
		{
			IToken token = scanner.get ();
			if (token == null)
			{
				break;
			}
			
			System.out.println (token);
		}
	}


	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IParser#deriveRuleSystem()
	 */
	@Override
	public void deriveRuleSystem ()
	{
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IParser#deriveRule()
	 */
	@Override
	public void deriveRule ()
	{
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IParser#deriveOredicate()
	 */
	@Override
	public void deriveOredicate ()
	{
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IParser#deriveTerm()
	 */
	@Override
	public void deriveTerm ()
	{
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void setScanner (IScanner scanner)
	{
		this.scanner = scanner;
	}
}
