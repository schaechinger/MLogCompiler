//
// ConstantString.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;


public class ConstantString extends Constant
{
	private String string = null;
	

	/**
	 * Creates a new ConstantString.
	 * @param string the sring that holds the data
	 */
	public ConstantString (String string)
	{
		super ();
		
		this.string = string;
	}
	
	
	@Override
	public String toString ()
	{
		return string;
	}
}
