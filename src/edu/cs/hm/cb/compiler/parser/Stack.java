//
// Stack.java
// Compiler
//
// Created by Manuel Schaechinger on 13.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.ArrayList;


/**
 * The stack holds a variable number of objects that can be pushed to it's top and popped from there.
 * 
 * @author Manuel Schaechinger
 *
 */
public class Stack
{
	/** Stack holds the objects */
	private ArrayList<Object> stack = null;
	

		/**
		 * Creates a new stack object and initializes the stack list.
		 */
		public Stack ()
		{
			stack = new ArrayList<Object> ();
		}
	
	
	/**
	 * Add a new object to the top of the stack.
	 * @param object is the new object
	 */
	public void push (Object object)
	{
		stack.add (object);
	}
	
	
	/**
	 * Retrieve the object on top of the stack and remove it.
	 * @return the top most object
	 */
	public Object pop ()
	{
		if (stack.size () > 0)
		{
			return stack.remove (stack.size () - 1);
		}
		else
		{
			return null;
		}
	}
	
	
	@Override
	public String toString ()
	{
		String elements = "";
		
		for (Object o : stack)
		{
			elements += "  " + o + "\n";
		}
		
		return elements;
	}
}
