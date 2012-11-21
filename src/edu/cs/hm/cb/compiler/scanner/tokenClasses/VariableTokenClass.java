//
// VariableTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 21.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class VariableTokenClass extends TokenClass
{
	public VariableTokenClass ()
	{
		name = "Variable";
		pass = true;
		variable = true;
	}
}
