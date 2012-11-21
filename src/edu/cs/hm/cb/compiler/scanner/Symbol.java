//
// Symbol.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import edu.cs.hm.cb.compiler.scanner.interfaces.ISymbol;


public class Symbol implements ISymbol
{
	private char character;
	private Position position;
	
	
		public Symbol (char character)
		{
			this.character = character;
		}
	

	/* (non-Javadoc)
	 * @see scanner.interfaces.ISymbol#getString()
	 */
	@Override
	public char getCharacter ()
	{
		return character;
	}


	/**
	 * The position where the symbol is located in the sourcecode.
	 */
	@Override
	public Position getPosition ()
	{
		return position;
	}

	
	/**
	 * Set the position where the symbol is located in the sourcecode.
	 */
	@Override
	public void setPosition (Position position)
	{
		this.position = position;
	}
	
	
	@Override
	public boolean equals (Object other)
	{
		if (((Symbol) other).getCharacter () == this.character)
		{
			return true;
		}
		
		return false;
	}
}
