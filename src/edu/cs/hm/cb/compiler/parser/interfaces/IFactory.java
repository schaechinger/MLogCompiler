//
// IFactory.java
// Compiler
//
// Created by Manuel Schaechinger on 23.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.List;

import edu.cs.hm.cb.compiler.parser.ConstantNamed;
import edu.cs.hm.cb.compiler.parser.ConstantString;
import edu.cs.hm.cb.compiler.parser.Variable;


public interface IFactory
{
	/** Creates a new ruleSystem. */
	public IRuleSystem		createRuleSystem (IQuery query, List<IRule> rules);
	/** Creates a new rule. */
	public IRule			createRule (IPredicate head, IPredicate[] body);
	/** Creates a new query. */
	public IQuery			createQuery (IPredicate[] body);
	/** Creates a new predicate. */
	public IPredicate		createPredicate (IOperator operator, ITerm[] arguments);
	/** Creates a new term. */
	public ITerm			createTerm (IOperator operator, ITerm[] arguments);
	/** Creates a new variable. */
	public Variable			createVariable (String string);
	/** Creates a new constantNamed. */
	public ConstantNamed	createConstantNamed (IOperator operator);
	/** Creates a new constantString. */
	public ConstantString	createConstantString (String string);
}
