//
// DoubleTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 14.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;

import edu.cs.hm.cb.compiler.scanner.interfaces.INumberTokenClass;


public class DoubleTokenClass extends TokenClass implements INumberTokenClass
{
	public DoubleTokenClass ()
	{
		name = "Double";
		pass = true;
		variable = true;
	}

	
	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.scanner.interfaces.INumberTokenClass#parse(java.lang.String)
	 */
	@Override
	public Number parse (String number)
	{
		return Double.parseDouble (number);
	}
}
