//
// ConstantNamed.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;


public class ConstantNamed extends Constant
{
	/**
	 * @param operator
	 */
	public ConstantNamed (IOperator operator)
	{
		super (operator);
	}
}
