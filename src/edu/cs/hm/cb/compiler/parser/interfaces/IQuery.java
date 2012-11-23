//
// IQuery.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.Iterator;


/**
 * Defines a query.
 * <query>: ':'<body>'.'
 * 
 * @author Manuel Schaechinger
 *
 */
public interface IQuery
{
	/** Returns the number of predicates in the body. */
	public int					getLength ();
	/** Returns the predicate at index i of the body. */
	public IPredicate			getBody (int i);
	/** Loops through the predicates of the body. */
	public Iterator<IPredicate>	bodyIterator ();
}
