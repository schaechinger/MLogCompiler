//
// IDFA.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;


public interface IDFA
{
	public IState trans (ISymbol symbol);
	public ITokenClass getTokenClass ();
	public void addTrans (IState from, ISymbol symbol, IState to);
	public void setBaseState (IState state);
	
	public void resetBase ();
}
