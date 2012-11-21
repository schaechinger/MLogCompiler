//
// TokenNotFoundException.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.exceptions;


public class TokenNotFoundException extends Exception
{
	/** The unique id of the exception for serialization. */
	private static final long serialVersionUID = 7102605474303176227L;

	
		public TokenNotFoundException (String pattern)
		{
			super ("Token with pattern '" + pattern + "' does not exist.");
		}
}
