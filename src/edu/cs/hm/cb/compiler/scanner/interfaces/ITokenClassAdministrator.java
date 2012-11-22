//
// ITokenClassAdministrator.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;


public interface ITokenClassAdministrator
{
	public ITokenClass	add (String tokenName, boolean pass, boolean variable);
	public ITokenClass	getByName (String tokenName);
}
