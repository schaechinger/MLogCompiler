//
// IScanner.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;


/**
 * Interface for the scanner that filters every token out of the source file.
 * 
 * @author Manuel Schaechinger
 *
 */
public interface IScanner
{
	/** Returns the next passed token. */
	public IToken	get ();
	/** Gives the last read token back to the scanner to put it on the stack and retrieve it the next time. */
	public void		unget (IToken token);
	/** Set the dfa object {Strategy}. */
	public void		setDfa (IDFA dfa);
}
