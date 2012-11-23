//
// ISubList.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.Iterator;

public interface ISubList
{
	/** Adds a new substitution pair to the list. */
	public void					add (ISubPair pair);
	/** Loops through the pairs. */
	public Iterator<ISubPair>	iterator ();
	/** Merges two substitution lists. */
	public ISubList				compose (ISubList list);
}
