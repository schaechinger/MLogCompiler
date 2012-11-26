//
// Operator.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;
import edu.cs.hm.cb.compiler.scanner.Token;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;


public class Operator implements IOperator
{
	private Token token;


	public Operator (Token token)
	{
		this.token = token;
	}


	@Override
	public String getName ()
	{
		return token.getPattern ();
	}


	@Override
	public ITokenClass getType ()
	{
		return token.getTokenClass ();
	}
	
	
	@Override
	public String toString ()
	{
		return token.getPattern ();
	}
}
