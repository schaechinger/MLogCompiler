//
// IRuleSystem.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.Iterator;


/**
 * Defines a ruleSystem that consists of a query and it's rules with facts.
 * 
 * @author Manuel Schaechinger
 *
 */
public interface IRuleSystem
{
	/** Returns the query. */
	public IQuery	getQuery ();
	/** Returns the number of rules. */
	public int		getLength ();
	/** Returns the rule at index i. */
	public IRule getRule (int i);
	/** Loops through the rules. */
	public Iterator<IRule>	ruleIterator ();
}
