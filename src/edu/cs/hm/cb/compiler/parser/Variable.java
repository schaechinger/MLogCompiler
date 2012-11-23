//
// Variable.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;


public class Variable extends Term
{
	private String name;
	

	/**
	 * @param operator
	 */
	public Variable (String string)
	{
		super ();
	}
	
	
	public String getName ()
	{
		return name;
	}
}
