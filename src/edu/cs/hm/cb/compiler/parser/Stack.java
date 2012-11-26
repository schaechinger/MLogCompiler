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
 * The stack holds a variable number of objects that can be pushed to it's top
 * and popped from there.
 * 
 * @author Manuel Schaechinger
 * 
 */
public class Stack
{
	public static Stack instance = null;

	/** Stack holds the objects */
	private ArrayList<Object> stack = null;


	/**
	 * Creates a new stack object and initializes the stack list.
	 */
	private Stack ()
	{
		stack = new ArrayList<Object> ();
	}


	public static Stack getInstance ()
	{
		if (instance == null)
		{
			instance = new Stack ();
		}

		return instance;
	}


	/**
	 * Add a new object to the top of the stack.
	 * 
	 * @param object
	 *            is the new object
	 */
	public void push (Object object)
	{
		stack.add (object);
	}


	/**
	 * Retrieve the object on top of the stack and remove it.
	 * 
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


	/**
	 * Retrieve the top most object of the stack and let it there!
	 * 
	 * @return the top most object of the stack
	 */
	public Object top ()
	{
		if (stack.size () > 0)
		{
			return stack.get (stack.size () - 1);
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

		for (int i = stack.size () - 1; i >= 0; i--)
		{
			elements += "  " + stack.get (i) + "\n";
		}

		return elements;
	}
}
