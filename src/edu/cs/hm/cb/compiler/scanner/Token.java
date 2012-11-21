//
// Token.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import edu.cs.hm.cb.compiler.scanner.interfaces.IToken;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;


public class Token implements IToken
{
	private ITokenClass	tokenClass;
	private Position	end;
	private Position	start;
	private String		pattern;
	
	
		public Token (ITokenClass tokenClass)
		{
			this.tokenClass = tokenClass;
		}
	
	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#getTokenClass()
	 */
	@Override
	public ITokenClass getTokenClass ()
	{
		return tokenClass;
	}
	

	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#getPattern()
	 */
	@Override
	public String getPattern ()
	{
		return pattern;
	}
	
	
	@Override
	public void setPattern (String pattern)
	{
		this.pattern = pattern;
	}

	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#getPosition()
	 */
	@Override
	public Position getPositionEnd ()
	{
		return end;
	}

	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#getPosition()
	 */
	@Override
	public Position getPositionStart ()
	{
		return start;
	}

	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#setPosition(scanner.Position)
	 */
	@Override
	public void setPositionEnd (Position position)
	{
		this.end = position;
	}

	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IToken#setPosition(scanner.Position)
	 */
	@Override
	public void setPositionStart (Position position)
	{
		this.start = position;
	}
	
	
	@Override
	public String toString ()
	{
		String passed = " ";
		if (getTokenClass ().isPassed ());
		{
			passed = "x";
		}
		
		String variable = " ";
		if (getTokenClass ().isVariable ())
		{
			variable = "x";
		}
		
		return String.format ("%-20s %s - %s   %s   %s   '%s'", tokenClass, start, end, passed, variable, pattern);
	}
}
