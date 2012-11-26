//
// Constant.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;


public class Constant extends Term
{	
	/**
	 * @param operator
	 */
	public Constant ()
	{
		super ();
	}
	
	
	public Constant (IOperator operator)
	{
		super (operator);
	}
	
	
	@Override
	public String toString ()
	{
		return this.getOperator ().getName ();
	}
}
