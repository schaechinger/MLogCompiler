//
// ISymbol.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;

import edu.cs.hm.cb.compiler.scanner.Position;

public interface ISymbol
{
	public char			getCharacter ();
	public Position		getPosition ();
	public void			setPosition (Position position);
}
