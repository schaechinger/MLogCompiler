
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
import edu.cs.hm.cb.compiler.parser.LLParser;
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
	/** Contains all enabled flags. */
	public static HashMap<String, String> flags = new HashMap<String, String> ();


	/**
	 * The main method of the program.
	 * 
	 * @param args
	 *            contains the flags of the compiler
	 */
	public static void main (String[] args)
	{
		for (int i = 0; i <= args.length / 2; i += 2)
		{
			flags.put (args[i], args[i + 1]);
		}

		if (args.length < 4)
		{
			System.out
					.println ("usage: mlogc [-ms] [-m mlog file] [-s dfa structure]");
			System.exit (0);
		}
		else
		{
			new DFACreator (flags.get ("-s")).createFile ();

			IDFA dfa = new DFA ();

			IScanner scanner = new Driver (flags.get ("-s").substring (0,
					flags.get ("-s").lastIndexOf ("."))
					+ "_gen.struct", flags.get ("-m"));
			scanner.setDfa (dfa);

			System.out.println (dfa);
			System.out.println (TokenClassAdministrator.getInstance ());

			IParser parser = new LLParser (1);
			parser.setScanner (scanner);
			parser.parse ();
		}
	}
}
