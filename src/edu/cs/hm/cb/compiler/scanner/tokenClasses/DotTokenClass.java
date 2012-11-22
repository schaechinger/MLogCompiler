//
// DotTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 12.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class DotTokenClass extends TokenClass
{
	public DotTokenClass ()
	{
		name = "Dot";
		pass = true;
		variable = false;
	}
}
