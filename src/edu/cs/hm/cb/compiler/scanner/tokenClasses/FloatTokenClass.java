//
// FloatTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 12.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;

import edu.cs.hm.cb.compiler.scanner.interfaces.INumberTokenClass;


public class FloatTokenClass extends TokenClass implements INumberTokenClass
{
	public FloatTokenClass ()
	{
		name = "Float";
		pass = true;
		variable = true;
	}

	
	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.scanner.interfaces.INumberTokenClass#parse(java.lang.Number)
	 */
	@Override
	public Number parse (String number)
	{
		return Float.parseFloat (number);
	}
}
