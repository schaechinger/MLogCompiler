//
// TokenClassAdministrator.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import java.util.HashMap;

import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClassAdministrator;
import edu.cs.hm.cb.compiler.scanner.TokenClass;


public class TokenClassAdministrator implements ITokenClassAdministrator
{
	private static TokenClassAdministrator administrator = null;

	private HashMap<String, ITokenClass> tokenClasses = null;


	private TokenClassAdministrator ()
	{
		tokenClasses = new HashMap<String, ITokenClass> ();
	}


	public static TokenClassAdministrator getInstance ()
	{
		if (administrator == null)
		{
			administrator = new TokenClassAdministrator ();
		}

		return administrator;
	}


	@Override
	public ITokenClass getByName (String tokenName)
	{
		return tokenClasses.get (tokenName);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * scanner.interfaces.ITokenClassAdministrator#createToken(java.lang.Class)
	 */
	@Override
	public ITokenClass add (String tokenName, boolean variable, boolean pass)
	{
		ITokenClass tokenClass = null;
		
		if (!tokenClasses.containsKey (tokenName))
		{
			tokenClass = new TokenClass (tokenName, variable, pass);
			tokenClasses.put (tokenName, tokenClass);
		}
		
		return tokenClass;
	}
	
	
	@Override
	public String toString ()
	{
		String tokens = "Available TokenClasses:\n";
		
		for (ITokenClass t : tokenClasses.values ())
		{
			tokens += " - " + t.getName () + "\n";
		}
		
		return tokens;
	}
}
