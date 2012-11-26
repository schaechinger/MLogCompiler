
package edu.cs.hm.cb.compiler;

//
// Application.java
// Compiler
//
// Created by Manuel Schaechinger on 09.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

import java.util.HashMap;

import edu.cs.hm.cb.compiler.dfaCreator.DFACreator;
import edu.cs.hm.cb.compiler.parser.MLogParser;
import edu.cs.hm.cb.compiler.parser.interfaces.IParser;
import edu.cs.hm.cb.compiler.scanner.DFA;
import edu.cs.hm.cb.compiler.scanner.Driver;
import edu.cs.hm.cb.compiler.scanner.TokenClassAdministrator;
import edu.cs.hm.cb.compiler.scanner.interfaces.IDFA;
import edu.cs.hm.cb.compiler.scanner.interfaces.IScanner;


/**
 * Starts the compiler with all it's components.
 * 
 * @author Manuel Schaechinger
 * 
 */
public class Application
{
	/** Indicates whether the log functionallity is enabled or not. */
	public static boolean log = false;
	public static boolean trace = false;

	/** Contains all enabled flags. */
	private HashMap<String, String> flags = null;


	public Application ()
	{
		flags = new HashMap<String, String> ();
	}


	public void addFlag (String flag, String value)
	{
		flags.put (flag, value);
	}


	public String getFlag (String flag)
	{
		return flags.get (flag);
	}


	/**
	 * The main method of the program.
	 * 
	 * @param args
	 *            contains the flags of the compiler
	 */
	public static void main (String[] args)
	{
		Application app = new Application ();

		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals ("-t"))
			{
				trace = true;
			}
			else if (args[i].equals ("-l"))
			{
				log = true;
			}
			else if (i < args.length - 1)
			{
				app.addFlag (args[i], args[++i]);
			}
		}

		if (args.length < 4)
		{
			System.out
					.println ("usage: mlogc [-mslt] [-m mlog file] [-s dfa structure] [-l enable log] [-t enable trace]");
			System.exit (0);
		}
		else
		{
			new DFACreator (app.getFlag ("-s")).createFile ();

			IDFA dfa = new DFA ();

			IScanner scanner = new Driver (app.getFlag ("-s").substring (0,
					app.getFlag ("-s").lastIndexOf ("."))
					+ "_gen.struct", app.getFlag ("-m"));
			scanner.setDfa (dfa);

			if (log)
			{
				System.out.println (dfa);
				System.out.println (TokenClassAdministrator.getInstance ());
			}

			IParser parser = new MLogParser ();
			parser.setScanner (scanner);
			parser.parse ();
		}
	}
}
