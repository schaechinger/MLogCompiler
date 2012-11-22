//
// IPredicate.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import java.util.Iterator;

import edu.cs.hm.cb.compiler.scanner.interfaces.ISymbol;

public interface IPredicate
{
	public int getRank ();
	public ISymbol getSymbol ();
	public ITerm getTerm (int i);
	public Iterator<ITerm> iterator ();
	public IPredicate substitute (ISubList sub);
	public ISubList unify (IPredicate predicate);
}
