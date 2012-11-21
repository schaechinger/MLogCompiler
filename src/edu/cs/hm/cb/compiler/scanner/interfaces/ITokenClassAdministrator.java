//
// ITokenClassAdministrator.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner.interfaces;

import edu.cs.hm.cb.compiler.scanner.exceptions.TokenClassNotFoundException;


public interface ITokenClassAdministrator
{
	public void			add (ITokenClass tokenClass);
	public ITokenClass	getByName (String name) throws TokenClassNotFoundException;
}
