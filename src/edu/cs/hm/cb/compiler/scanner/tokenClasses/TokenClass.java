//
// TokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.tokenClasses;

import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;


public class TokenClass implements ITokenClass
{
	/** Indicates whether tokens of this tokenClass have a variable pattern */
	protected boolean	variable;
	/** Indicates whether tokens of this tokenClass should be be passed to the parser */
	protected boolean	pass;
	/** The name of the tokenClass */
	protected String	name;
	

	/* (non-Javadoc)
	 * @see scanner.interfaces.ITokenClass#getName()
	 */
	@Override
	public String getName()
	{
		return name;
	}
	

	/* (non-Javadoc)
	 * @see scanner.interfaces.ITokenClass#isVariable()
	 */
	@Override
	public boolean isVariable()
	{
		return variable;
	}
	
	
	@Override
	public boolean isPassed ()
	{
		return pass;
	}
	
	
	@Override
	public String toString ()
	{
		return name;
	}
}
