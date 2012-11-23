//
// ITerm.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.Iterator;

public interface ITerm
{
	/** Returns the number of terms of the predicate [likes (erna) -> likes]. */
	public int				getRank ();
	/** Returns the operator or constant. */
	public IOperator		getOperator ();
	/** Returns the term at index i or null if it doesn't exists [i >= rank]. */
	public ITerm			getTerm (int i);
	/** Loops through all terms. */
	public Iterator<ITerm>	iterator ();
	/** TODO: wtf? */
	public ITerm			substitute (ISubList sub);
	/** Generates a list of substitutions where every pair should be equal, otherwise null. */
	public ISubList			unify (ITerm term);
}
