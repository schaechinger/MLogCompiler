//
// ISubPair.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import edu.cs.hm.cb.compiler.parser.Variable;


public interface ISubPair
{
	/** Returns the variable. */
	public Variable getVariable ();
	/** Returns the term. */
	public ITerm getTerm ();
}
