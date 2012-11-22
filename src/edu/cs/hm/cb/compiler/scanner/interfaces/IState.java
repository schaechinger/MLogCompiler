//
// IState.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;


public interface IState
{
	public boolean		isFinal ();
	public int			getId ();
	public ITokenClass	getTokenClass ();
	public void			setFinal (String tokenName, boolean passed, boolean variable);
}
