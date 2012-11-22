//
// ITerm.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

public interface ITerm
{
	public int getRank ();
	public ISymbol getSymbol ();
	public ITerm getTerm (int i);
	public Iterator<ITerm> getTerm (int i);
	public ITerm substitude (ISubList sub);
	public ISubList unify (ITerm t);
}
