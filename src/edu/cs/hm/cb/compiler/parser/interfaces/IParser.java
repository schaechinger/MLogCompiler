//
// IParser.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser.interfaces;

import edu.cs.hm.cb.compiler.scanner.interfaces.IScanner;


public interface IParser
{
	public void		parse ();
	public boolean	deriveRuleSystem ();
	public boolean	deriveRule ();
	public boolean	derivePredicate ();
	public boolean	deriveTerm ();
	/** Set the scanner {Strategy}. */
	public void		setScanner (IScanner scanner);
}
