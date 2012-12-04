//
// ConstantInteger.java
// Compiler
//
// Created by Manuel Schaechinger on 26.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;


public class ConstantInteger extends Constant
{
	private int value;


	public ConstantInteger (int value)
	{
		super ();

		this.value = value;
	}


	@Override
	public String toString ()
	{
		return "" + value;
	}
}
