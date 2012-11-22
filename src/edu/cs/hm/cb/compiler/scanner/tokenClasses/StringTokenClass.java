//
// StringTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class StringTokenClass extends TokenClass
{
	public StringTokenClass ()
	{
		name = "String";
		pass = true;
		variable = true;
	}
}
