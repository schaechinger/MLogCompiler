//
// IdentifierTokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 12.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;


public class CommentTokenClass extends TokenClass
{
	public CommentTokenClass ()
	{
		name = "Comment";
		pass = false;
		variable = true;
	}
}
