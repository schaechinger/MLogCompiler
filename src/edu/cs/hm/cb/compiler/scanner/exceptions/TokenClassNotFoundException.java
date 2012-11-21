//
// TokenClassNotFoundException.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.exceptions;


public class TokenClassNotFoundException extends Exception
{
	/** The unique id of the exception for serialization. */
	private static final long serialVersionUID = -2081842536629658904L;

	
		/**
		 * This exception is thrown if a new tokenClass with
		 * a specified name should be created that does not exist.
		 * 
		 * @param name the identifier of the tokenClass
		 */
		public TokenClassNotFoundException (String name)
		{
			super ("TokenClass with name '" + name + "' does not exist.");
		}
}
