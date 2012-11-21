//
// BracketCloseTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class BracketCloseTokenClass extends TokenClass
{
	public BracketCloseTokenClass ()
	{
		name = "BracketClose";
		pass = true;
		variable = false;
	}
}
