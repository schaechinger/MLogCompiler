//
// Position.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

public class Position
{
	private int column;
	private int line;
	
	
		public Position (int column, int line)
		{
			this.column = column;
			this.line = line;
		}
	
	
	public int getColumn ()
	{
		return column;
	}
	
	
	public int getLine ()
	{
		return line;
	}
	
	
	@Override
	public String toString ()
	{
		return String.format ("%4d:%-4d", column, line);
	}
}
