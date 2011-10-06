package com.thumbtack.expression;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {
	
	
	List<LinkedList<String>> matches = new ArrayList<LinkedList<String>>();
	
	public List<LinkedList<String>> evaluate(List<LinkedList<String>> expressions, int target)
	{
		
		for (LinkedList<String> exp : expressions)
		{
			if (target == processPostFixExpression(exp))
			{
				matches.add(exp);
			}
		}
		
		return matches;
		
	}
	

	private int processPostFixExpression(LinkedList<String> expression)
	{

		Stack<String> theStack = new Stack<String>();

		double num1, num2, interAns;

		for(String val : expression)
		{

			if(isDouble(val)) // if it's a number
				theStack.push( val ); // push it
			else // it's an operator
			{
				num2 = Double.parseDouble(theStack.pop());
				num1 = Double.parseDouble(theStack.pop());

				char ch = val.charAt(0);

				switch(ch) // do arithmetic
				{
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				}

				theStack.push(Double.toString(interAns));
			} 
		} 

		interAns = Double.parseDouble(theStack.pop());

		return (int) Math.round(interAns);

	} 

	private static boolean isDouble(String s)
	{

		try {
			Double.parseDouble(s);
			return true;
		}
		catch(NumberFormatException nFE) {
			return false;
		}


	}

} 

