//
// DFACreator.java
// Compiler
//
// Created by Manuel Schaechinger on 16.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.dfaCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import edu.cs.hm.cb.compiler.Application;
import edu.cs.hm.cb.compiler.scanner.State;
import edu.cs.hm.cb.compiler.scanner.Symbol;


public class DFACreator
{
	private BufferedReader reader = null;
	private PrintWriter writer = null;


	public DFACreator (String structPath)
	{
		try
		{
			reader = new BufferedReader (new FileReader (new File (structPath)));
			writer = new PrintWriter (new File (structPath.substring (0,
					structPath.lastIndexOf (".")) + "_gen.struct"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println ("Could not find dfa struct file.");
		}
	}


	public void createFile ()
	{
		String next = null;

		try
		{
			while ((next = reader.readLine ()) != null)
			{
				String[] components = next.split (" ");

				// Initial state
				if (components[0].equals ("I"))
				{
					writer.println (next);
				}
				// Transtition
				else if (components[0].equals ("T"))
				{
					// Transition with all symbols but the defined one
					if (components[2].length () > 1
							&& components[2].startsWith ("!"))
					{
						String gen = "T " + components[1];

						for (int i = 32; i < 127; i++)
						{
							if (i == (int) components[2].charAt (1)
									&& !components[2].equals ("!all"))
							{
								continue;
							}

							gen += String.format (" \\u%04x", i);
							writer.flush ();
						}

						gen += " " + components[components.length - 1];

						writer.println (gen);
					}
					// Normal transition with one or two symbols
					else if (components.length < 6
							|| components[2].length () == 1)
					{
						writer.println (next);
					}
					// Transition with a range of symbols
					else if (components.length == 6
							&& components[3].equals ("-"))
					{
						String gen = "T " + components[1];

						int start = Integer.parseInt (components[2]
								.substring (2));
						int end = Integer
								.parseInt (components[components.length - 2]
										.substring (2));

						for (int i = start; i <= end; i++)
						{
							gen += String.format (" \\u%04d", i);
						}

						gen += " " + components[components.length - 1];

						writer.println (gen);
					}
				}
				// Epsylon state
				else if (components[0].equals ("E"))
				{
					writer.println (next);
				}
				// Final state
				else if (components[0].equals ("F"))
				{
					writer.println (next);
				}

				writer.flush ();
			}
		}
		catch (IOException e)
		{
			System.out.println ("Could not read from dfa struct file");
		}

		if (Application.log)
		{
			System.out.println ("DFA creation successful");
		}
	}
}
