//
// RuleSystem.java
// Compiler
//
// Created by Manuel Schaechinger on 22.11.2012
// Copyrigtht (c) 2012 Delivery Studios. All rights reserved.
//

package edu.cs.hm.cb.compiler.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.cs.hm.cb.compiler.parser.interfaces.IQuery;
import edu.cs.hm.cb.compiler.parser.interfaces.IRule;
import edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem;


public class RuleSystem implements IRuleSystem
{
	private IQuery query = null;
	private List<IRule> rules = null;
	
	
	public RuleSystem (IQuery query)
	{
		this.query = query;
		rules = new ArrayList<IRule> ();
	}
	
	
	public void addRule (IRule rule)
	{
		rules.add (rule);
	}
	
	
	
	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem#getQuery()
	 */
	@Override
	public IQuery getQuery ()
	{
		return query;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem#getLength()
	 */
	@Override
	public int getLength ()
	{
		return rules.size ();
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem#getRule(int)
	 */
	@Override
	public IRule getRule (int i)
	{
		if (i < getLength ())
		{
			return rules.get (i);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cs.hm.cb.compiler.parser.interfaces.IRuleSystem#ruleIterator()
	 */
	@Override
	public Iterator<IRule> ruleIterator ()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String toString ()
	{
		String info = "RuleSystem:\nQuery:\n  " + query + "\nRuleList:";
		
		for (IRule rule : rules)
		{
			info += "\n  " + rule;
		}
		
		return info;
	}
}
