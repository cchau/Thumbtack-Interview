package com.thumbtack.expression;

import java.util.LinkedList;
import java.util.Stack;

public class InFixExpressionBuilder {

	public String doParse(LinkedList<String> expression)
	{
		Stack<String> theStack;

		theStack = new Stack<String>(); 
		char ch;
		String num1, num2, interAns;

		int i = 0;

		for(String val : expression)
		{

			if(isInteger(val)) // if it's a number
				theStack.push( val ); // push it
			else // it's an operator
			{
				num2 = theStack.pop(); // pop operands
				num1 = theStack.pop();

				ch = val.charAt(0);

				switch(ch) // do arithmetic
				{
				case '+':
					interAns = num1 + "+" + num2;
					break;
				case '-':
					interAns = num1 + "-" + num2;
					break;
				case '*':
					interAns = num1 + "*" + num2;
					break;
				case '/':
					interAns = num1 + "/" + num2;
					break;
				default:
					interAns = "";
				}
				
				if (i != expression.size() - 1)
				{
					interAns = "(" + interAns + ")";
				}

				theStack.push(interAns); // push result

			} 
			
			i++;
			
		} 
		interAns = theStack.pop(); // get answer
		return interAns;
	} 

	private boolean isInteger(String s)
	{

		try {
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException nFE) {
			return false;
		}


	}
} 

