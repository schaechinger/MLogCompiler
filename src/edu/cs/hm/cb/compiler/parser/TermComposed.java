//
// TermComposed.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import edu.cs.hm.cb.compiler.parser.interfaces.IOperator;
import edu.cs.hm.cb.compiler.parser.interfaces.ITerm;
import edu.cs.hm.cb.compiler.parser.interfaces.ITermComposed;


public class TermComposed extends Term implements ITermComposed
{
	public TermComposed (IOperator operator)
	{
		super (operator);
	}
	
	
	@Override
	public void add (ITerm term)
	{
		terms.add (term);
	}
}
