//
// ITokenClass.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;

public interface ITokenClass
{
	public String	getName ();
	public boolean	isPassed ();
	public boolean	isVariable ();
}
