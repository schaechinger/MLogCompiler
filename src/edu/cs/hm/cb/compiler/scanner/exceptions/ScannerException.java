//
// ScannerException.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.exceptions;


public class ScannerException extends Exception
{
	/** The unique id of the exception for serialization. */
	private static final long serialVersionUID = 5646078577696208782L;

		public ScannerException (String message)
		{
			super (message);
		}
}
