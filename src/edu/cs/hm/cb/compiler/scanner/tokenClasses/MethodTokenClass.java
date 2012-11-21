//
// MethodTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 21.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class MethodTokenClass extends TokenClass
{
	public MethodTokenClass ()
	{
		name = "Method";
		pass = true;
		variable = true;
	}
}
