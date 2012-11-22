//
// DFA.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import edu.cs.hm.cb.compiler.scanner.interfaces.IDFA;
import edu.cs.hm.cb.compiler.scanner.interfaces.IState;
import edu.cs.hm.cb.compiler.scanner.interfaces.ISymbol;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;


public class DFA implements IDFA
{
	private State base = null;
	private State init = null;
	
	
		public DFA ()
		{
			base = State.get (0);
			init = State.get (0);
		}
	
	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IDFA#trans(scanner.interfaces.IState, scanner.interfaces.ISymbol)
	 */
	@Override
	public IState trans(ISymbol symbol)
	{
		State next = base.getChild (symbol);
		
		if (next != null)
		{
			base = next;
		}
		
		return next;
	}
	
	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IDFA#getTokenClass(scanner.interfaces.IState)
	 */
	@Override
	public ITokenClass getTokenClass ()
	{
		return base.getTokenClass ();
	}

	
	/* (non-Javadoc)
	 * @see scanner.interfaces.IDFA#addTrans(scanner.interfaces.IState, scanner.interfaces.ISymbol, scanner.interfaces.IState)
	 */
	@Override
	public void addTrans (IState from, ISymbol symbol, IState to)
	{
		if (base.equals (from))
		{
			if (base.addSymbol (symbol, (State) to))
			{
				base = (State) to;
			}
		}
		else
		{
			base = base.getParent ();
			addTrans (from, symbol, to);
		}
	}
	
	
	@Override
	public void setBaseState (IState state)
	{
		this.base = (State) state;
	}
	
	
	@Override
	public void resetBase ()
	{
		base = State.get (init.getId ());
	}
	
	
	@Override
	public String toString ()
	{
		System.out.println ("DFA structure:");
		init.display ("  ", true);
		return "";
	}
}
