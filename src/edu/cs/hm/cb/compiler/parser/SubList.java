//
// SubList.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.Iterator;

import edu.cs.hm.cb.compiler.parser.interfaces.ISubList;
import edu.cs.hm.cb.compiler.parser.interfaces.ISubPair;


public class SubList implements ISubList
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.ISubList#add(edu.cs.hm.cb.compiler
	 * .parser.interfaces.ISubPair)
	 */
	@Override
	public void add (ISubPair pair)
	{
		// TODO Auto-generated method stub

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.ISubList#iterator()
	 */
	@Override
	public Iterator<ISubPair> iterator ()
	{
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.cs.hm.cb.compiler.parser.interfaces.ISubList#compose(edu.cs.hm.cb
	 * .compiler.parser.interfaces.ISubList)
	 */
	@Override
	public ISubList compose (ISubList list)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
