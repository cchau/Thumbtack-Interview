package com.thumbtack.expression;

import java.util.LinkedList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			throw new IllegalArgumentException("\n\nAllow only one argument. " +
					"Please put your argument in double quote and the following format: " +
				    "\"[v1, v2, v3, v4] = r\"" + " where v's is your values and r is your expected result.");
		}
			
		
		String input[] = args[0].split("=");
		input[0] = input[0].trim();
		input[1] = input[1].trim();
		
		if (input[0].charAt(0) != '[' || 
				input[0].charAt(input[0].length() - 1) != ']')
		{
			throw new IllegalArgumentException("The value argument is not well formed: " + "Not opened with '['"
												+ "or Closed with ']'");
		}
		
		String values[] = input[0].substring(1, input[0].length()-1).split(",");
		
		if (values.length != 4)
		{
			throw new IllegalArgumentException("The value argument is not well formed: " + 
														"It has to be a comma seperated list");
		}
		
		LinkedList<String> inputValues = new LinkedList<String>();
		for (String s : values)
		{
			if (!isInteger(s.trim()))
			{
				throw new IllegalArgumentException("The value argument is not well formed: " + 
						"Only Integer values are allowed in the value argument");
			}
			
			
			inputValues.addLast(s.trim());
			
			
		}
		
		System.out.println(inputValues);
		
		ExpressionBuilder eb = new ExpressionBuilder(inputValues);
		List<LinkedList<String>> allPossibleExpressions = eb.buildExpressions();

		int target;
		
		if (!isInteger(input[1].trim()))
		{
			throw new IllegalArgumentException("The value argument is not well formed: " + 
					"Only Integer values are allowed in the result argument");
		}
		
		target = Integer.parseInt(input[1].trim());
		
		ExpressionEvaluator ev = new ExpressionEvaluator();
		

		List<LinkedList<String>> matches = ev.evaluate(allPossibleExpressions, target);
		
		System.out.println("We found " + matches.size() + " matche(s)");
		
		InFixExpressionBuilder expBuilder = new InFixExpressionBuilder();
		for(LinkedList<String> lst : matches)
		{
			System.out.println(expBuilder.doParse(lst));
		}
		
		

	}
	
	private static boolean isInteger(String s)
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
