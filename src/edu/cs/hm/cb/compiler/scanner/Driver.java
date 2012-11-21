//
// Driver.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;

import edu.cs.hm.cb.compiler.scanner.exceptions.TokenClassNotFoundException;
import edu.cs.hm.cb.compiler.scanner.interfaces.IDFA;
import edu.cs.hm.cb.compiler.scanner.interfaces.IScanner;
import edu.cs.hm.cb.compiler.scanner.interfaces.IToken;


/**
 * The driver is a synonym of the scanner that searches the source for tokens.
 * 
 * @author Manuel Schaechinger
 *
 */
public class Driver implements IScanner
{
	/** The maximum number of characters that the pushbackReader can go back. */
	private final int MAX_BUFFER_SIZE = 80;
	
	/** The reader to for the structure file, */
	private BufferedReader br = null;
	/** The dfa with the rules. */
	private IDFA dfa = null;
	/** The current column of the pushbackReader */
	private int column;
	/** The current line of the pushbackReader */
	private int line;
	/** The reader for the sourcecode. */
	private PushbackReader reader = null;
	/**  */
	private ArrayList<IToken> tokenStack = null;

	
		/**
		 * Creates a new Driver that initializes the dfa and scans the sourcecode.
		 * 
		 * @param dfa is the dfa object that holds the compilation rules
		 * @param structPath is the path of the structure file for the dfa
		 * @param sourcePath is the path of the sourcecode file
		 */
		public Driver (IDFA dfa, String structPath, String sourcePath)
		{
			this.dfa = dfa;
			
			tokenStack = new ArrayList<IToken>();
			
			column = 1;
			line = 1;
	
			try
			{
				br  = new BufferedReader (new FileReader (new File (structPath)));
				reader = new PushbackReader (new FileReader (new File (sourcePath)), MAX_BUFFER_SIZE);
			}
			catch (FileNotFoundException e)
			{
				System.out.println ("could not find file on path " + sourcePath);
			}
	
			init ();
		}

	
	/**
	 * Create the dfa tree with all the rules for the dfa.
	 */
	private void init ()
	{
		try
		{
			int line = 1;
			
			String next = null;
			while ((next = br.readLine ()) != null)
			{
				String[] trans = next.split (" ");

				if (trans.length != 0)
				{
					// Initial
					if (trans[0].equals ("I"))
					{
						dfa.setBaseState (State.get (trans[1]));
					}
					// Transition
					else if (trans[0].equals ("T"))
					{
						Symbol symbol = null;
						
						for (int i = 2; i < trans.length - 1; i++)
						{
							if (trans[i].length () == 1)
							{
								symbol = new Symbol (trans[i].charAt (0));
							}
							else if (trans[i].length () > 1)
							{
								symbol = new Symbol ((char) Integer.parseInt (trans[i].substring (2), 16));
							}
							else
							{
								System.out.println("error parsing structure file in line " + line + ": '" + next + "' - missing transition symbol");
								System.exit (0);
							}
							
							dfa.addTrans (
									State.get (trans[1]),
									symbol,
									State.get (trans[trans.length - 1]));
						}
					}
					// Epsilon symbol for nfas
					else if (trans[0].equals ("E"))
					{
						dfa.addTrans (
								State.get (trans[1]),
								null,
								State.get (trans[2]));
					}
					// Final token
					else if (trans[0].equals ("F"))
					{
						State.get (trans[1]).setFinal (trans[2]);
					}
				}
				
				line++;
			}
		}
		catch (IOException e)
		{
			System.out.println ("could not read from bufferedReader (initializing)");
		}
		catch (TokenClassNotFoundException e)
		{
			System.out.println (e.getMessage ());
		}
	}
	
	
	/**
	 * Scans the sourcecode and searches for token.
	 * @return the next token from the sourcecode
	 */
	public IToken get ()
	{
		if (tokenStack.size () > 0)
		{
			return tokenStack.remove (tokenStack.size () - 1);
		}
		
		ArrayList<Character> pushbackBuffer = new ArrayList<Character>();
		ArrayList<Character> tokenString = new ArrayList<Character>();
				
		dfa.resetBase ();
		
		try
		{
			int read = 0;
			boolean eof = false;
			Position endPosition = null;
			Position startPosition = new Position (column, line);
			State savedTokenState = null;
			State lastState = null;
			
			while ((read = reader.read ()) != -1 || !eof)
			{
				line++;
				
				// break detected
				if (read == 10)
				{
					column++;
					line = 1;
				}
				
				if (read == -1)
				{
					eof = true;
				}
				
				State state = (State) dfa.trans (new Symbol ((char) read));
				
				pushbackBuffer.add ((char) read);
				if (pushbackBuffer.size () > MAX_BUFFER_SIZE)
				{
					pushbackBuffer.remove (0);
				}
				
				tokenString.add ((char) read);
				
				// error state
				if (state == null)
				{
					String pattern = "";
					for (int i = 0; i < tokenString.size () - 1; i++)
					{
						pattern += tokenString.get (i);
					}
					
					tokenString.clear ();
					
					if (!eof)
					{
						if (savedTokenState != null)
						{
							for (int i = pushbackBuffer.size () - 1; i >= 0; i--)
							{
								reader.unread (pushbackBuffer.get (i));
							}
							
							dfa.resetBase ();
							
							column = endPosition.getColumn ();
							line = endPosition.getLine ();
						}
					}
					if (savedTokenState != null && (lastState != null && lastState.isFinal ()))
					{
						IToken token = new Token (savedTokenState.getTokenClass ());
						token.setPattern (pattern);
						token.setPositionEnd (endPosition);
						token.setPositionStart (startPosition);

						System.out.println (token);
						return token;
					}
					else if (!eof)
					{
						String errorPattern = "";
						for (int i = 0; i < pushbackBuffer.size (); i++)
						{
							errorPattern += pushbackBuffer.get (i);
						}
						
						System.err.println ("Syntax error:");
						System.err.println ("Unknown pattern '" + errorPattern + "' @ " + startPosition);
						return null;
					}
				}
				// final state detected
				else 
				{
					if (state.isFinal ())
					{
						pushbackBuffer.clear ();
						savedTokenState = state;
						endPosition = new Position (column, line);
					}
				}
				
				lastState = state;
			}
		}
		catch (IOException ioe)
		{
			System.out.println ("could not read from pushBackReader (scanning)");
		}
		
		return null;
	}
	
	
	/**
	 * Take the last token that was read from the source code and put it into a stack, that is accessed when the next token should be read.
	 */
	@Override
	public void unget (IToken token)
	{
		tokenStack.add (token);
	}
}
