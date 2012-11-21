//
// IToken.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;

import edu.cs.hm.cb.compiler.scanner.Position;


public interface IToken
{
	public ITokenClass	getTokenClass ();
	public String		getPattern ();
	public Position		getPositionEnd ();
	public Position		getPositionStart ();
	public void			setPattern (String pattern);
	public void			setPositionEnd (Position position);
	public void			setPositionStart (Position position);
}
