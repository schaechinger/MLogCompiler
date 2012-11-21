//
// IScanner.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;


public interface IScanner
{
	public IToken	get ();
	public void		unget (IToken token);
}
