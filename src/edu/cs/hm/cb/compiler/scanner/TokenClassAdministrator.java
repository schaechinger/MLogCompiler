//
// TokenClassAdministrator.java
// Compiler
//
// Created by Manuel Schaechinger on 11.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import edu.cs.hm.cb.compiler.scanner.exceptions.TokenClassNotFoundException;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClassAdministrator;
import edu.cs.hm.cb.compiler.scanner.tokenClasses.*;


public class TokenClassAdministrator implements ITokenClassAdministrator
{
	private static TokenClassAdministrator administrator = null;
	

		private TokenClassAdministrator ()
		{
			
		}
		
		
		public static TokenClassAdministrator get ()
		{
			if (administrator == null)
			{
				administrator = new TokenClassAdministrator ();
			}
			
			return administrator;
		}
	
	
	@Override
	public ITokenClass getByName (String name) throws TokenClassNotFoundException
	{
		Class c;
		try
		{
			c = Class.forName ("edu.cs.hm.cb.compiler.scanner.tokenClasses." + name + "TokenClass");
			Constructor<TokenClass> consturctor = c.getConstructor ();
			return consturctor.newInstance ();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println ("Class '" + name + "TokenClass' not found.");
		}
		catch (SecurityException e)
		{
			System.out.println ("Could not access default constructor.");
		}
		catch (NoSuchMethodException e)
		{
			System.out.println ("Constructor not found.");
		}
		catch (IllegalArgumentException e)
		{
			System.out.println ("Invalid constructor called.");
		}
		catch (InstantiationException e)
		{
			System.out.println ("Could not instantiate object.");
		}
		catch (IllegalAccessException e)
		{
			System.out.println ("Could not access object.");
		}
		catch (InvocationTargetException e)
		{
			System.out.println ("InvocationTargetException");
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see scanner.interfaces.ITokenClassAdministrator#createToken(java.lang.Class)
	 */
	@Override
	public void add (ITokenClass tokenClass)
	{
		
	}
}
