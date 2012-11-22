//
// ParserException.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.exceptions;


public class ParserException extends Exception
{
	/** The unique id of the exception for serialization. */
	private static final long serialVersionUID = -4249194618386867067L;

	
		public ParserException (String message)
		{
			super (message);
		}
}
