//
// Node.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cs.hm.cb.compiler.scanner.interfaces.IState;
import edu.cs.hm.cb.compiler.scanner.interfaces.ISymbol;
import edu.cs.hm.cb.compiler.scanner.interfaces.ITokenClass;


/**
 * Represents a state in the dfa and holds it's data like the transition to the
 * next state(s).
 * 
 * @author Manuel Schaechinger
 * 
 */
public class State implements IState
{
	/**
	 * Contains all existing states sorted by their ids to avaid several
	 * instances of the same state.
	 */
	private static HashMap<Integer, State> states;

	/** Contains all symbols that link from this state to another */
	private HashMap<Integer, ArrayList<ISymbol>> symbols = null;
	/** Indicates if this state is a final one */
	private boolean isFinal = false;
	/**
	 * Contains all children states that can be reached with the symbols defined
	 * above.
	 */
	// private HashMap<Integer, State> children = null;
	/** Contains all existing states that can be this state's predecessors */
	private HashMap<Integer, State> parents = null;
	/** The unique id of the state */
	private int id;
	/** If this state is final, the tokenclass indicates the type of token */
	private TokenClass tokenClass = null;


	/**
	 * Creates a new state.
	 * 
	 * @param id
	 *            the unique id of the state
	 */
	private State (int id)
	{
		this.id = id;
		// children = new HashMap<Integer, State> ();
		parents = new HashMap<Integer, State> ();
		symbols = new HashMap<Integer, ArrayList<ISymbol>> ();
	}


	/**
	 * Looks up if an existing state has already this id and returns it,
	 * otherwise creates a new one.
	 * 
	 * @param id
	 *            the unique id to determine if there is an existing state
	 * @return the state that was created or returned from the existing one
	 */
	public static State get (int id)
	{
		if (states == null)
		{
			states = new HashMap<Integer, State> ();
		}
		else if (states.containsKey (id))
		{
			return states.get (id);
		}

		State state = new State (id);
		states.put (id, state);

		return state;
	}


	/**
	 * Parses the id in string form to an integer and calls the getInstance
	 * (int) method.
	 * 
	 * @param id
	 *            the unique id to determine if there is an existing state
	 * @return the state that was created or returned from the existing one
	 */
	public static State get (String id)
	{
		return State.get (Integer.parseInt (id));
	}


	/**
	 * Adds a new state as a child to this state that can be reached with the
	 * symbol.
	 * 
	 * @param child
	 *            is a state that can be reached from this state
	 * @param symbol
	 *            indicates the symbol with that the child state can be reached
	 * @return
	 */
	/*
	 * public boolean addChild (State child, ISymbol symbol)
	 * {
	 * if (!children.containsKey (child.getId ()))
	 * {
	 * children.put (child.getId (), child);
	 * child.addParent (this);
	 * }
	 * 
	 * if (!symbols.containsKey (child.getId ()))
	 * {
	 * symbols.put (child.getId(), new ArrayList<ISymbol> ());
	 * }
	 * 
	 * child.addSymbol (symbol, this);
	 * 
	 * return true;
	 * }
	 */

	/**
	 * Adds a new symbol to the list of symbols that with that this state can be
	 * reached.
	 * 
	 * @param symbol
	 *            the symbol with that this state can be reached
	 * @return if the symbol was added or not (if it was already there)
	 */
	public boolean addSymbol (ISymbol symbol, IState to)
	{
		// create a new arrayList if this symbol connects the state to a new one
		if (!symbols.containsKey (to.getId ()))
		{
			symbols.put (to.getId (), new ArrayList<ISymbol> ());
			((State) to).addParent (this);
		}

		ArrayList<ISymbol> s = symbols.get (to.getId ());

		if (!s.contains (symbol))
		{
			s.add (symbol);
			symbols.put (to.getId (), s);

			return true;
		}

		return false;
	}


	/**
	 * Checks if there is a state that can be reached with the defined state.
	 * 
	 * @param symbol
	 *            the symbol that should cause the transformation to the next
	 *            state
	 * @return the state if there is one, otherwise null
	 */
	public State getChild (ISymbol symbol)
	{
		for (int stateId : symbols.keySet ())
		{
			ArrayList<ISymbol> list = symbols.get (stateId);

			if (list != null && list.contains (symbol))
			{
				return State.get (stateId);
			}
		}

		return null;
	}


	/**
	 * Returns the first parent state that is not equals to this one.
	 * 
	 * @return the predecessor of this state
	 */
	public State getParent ()
	{
		for (State s : parents.values ())
		{
			if (!s.equals (this))
			{
				return s;
			}
		}

		return null;
	}


	/**
	 * Adds a parent state to this state.
	 * 
	 * @param parent
	 *            the state that is a predecessor of this one
	 */
	public void addParent (State parent)
	{
		if (!parent.equals (this))
		{
			parents.put (parent.getId (), parent);
		}
	}


	/**
	 * Returns the unique id of this state.
	 */
	public int getId ()
	{
		return id;
	}


	/**
	 * Returns if this state is final and therefore indicates a token.
	 */
	@Override
	public boolean isFinal ()
	{
		return isFinal;
	}


	/**
	 * Set the state to final and indicate that a token is found when you reach
	 * this state.
	 * 
	 * @param tokenName
	 *            the name of the tokenClass that identifies it
	 * @param pass
	 *            indicates if the tokenClass should be passed to the parser
	 * @param variable
	 *            indicates if the tokenClass has a variable structure
	 */
	@Override
	public void setFinal (String tokenName, boolean variable, boolean pass)
	{
		tokenClass = (TokenClass) TokenClassAdministrator.getInstance ()
				.add (tokenName, variable, pass);
		isFinal = true;
	}


	/**
	 * Returns the tokenclass if this state is final.
	 */
	@Override
	public ITokenClass getTokenClass ()
	{
		return tokenClass;
	}


	/**
	 * Check if the given state is equals to this one by compairing their ids.
	 */
	@Override
	public boolean equals (Object other)
	{
		State state = (State) other;

		if (this.getId () == state.getId ())
		{
			return true;
		}

		return false;
	}


	/**
	 * Used for printing information abouf the state
	 * 
	 * @param offset
	 *            the offset is a number of whitespaces to get a better view
	 * @return a string that holds several information about the state
	 */
	public void display (String offset, boolean deep)
	{
		String string = offset + id + " (" + symbols.size () + " child";
		if (symbols.size () != 1)
		{
			string += "ren";
		}

		string += ")";

		if (isFinal)
		{
			string += " [F " + getTokenClass ().getName () + "]";
		}

		if (symbols.size () > 0)
		{
			string += " { ";

			for (int i : symbols.keySet ())
			{
				ArrayList<ISymbol> s = symbols.get (i);

				if (s.size () > 0)
				{
					string += "[" + i + "]: ";
				}

				for (ISymbol symbol : s)
				{
					char character = symbol.getCharacter ();
					
					if ((int) character > 32 && (int) character < 127)
					{
						string += symbol.getCharacter () + ", ";
					}
					else
					{
						string += String.format ("\\u%04x, ", (int) character);
					}
				}
			}

			string = string.substring (0, string.length () - 2);

			if (!string.endsWith (" "))
			{
				string += " }";
			}
		}

		System.out.println (string);

		if (!deep)
		{
			return;
		}

		for (int keyId : symbols.keySet ())
		{
			State state = State.get (keyId);
			string += "\n";

			boolean goDeep = true;

			if (state.equals (this))
			{
				goDeep = false;
			}

			state.display (offset + "  ", goDeep);
		}

		return;
	}
}
