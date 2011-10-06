package com.thumbtack.expression;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExpressionBuilder {


	private LinkedList<String> inputValues;
	private List<LinkedList<String>> permutations = new ArrayList<LinkedList<String>>();
	private List<LinkedList<String>> expressions = new ArrayList<LinkedList<String>>();


	public ExpressionBuilder(LinkedList<String> inputValues )
	{
		this.inputValues = inputValues;
	}

	public List<LinkedList<String>> buildExpressions()
	{
		buildValuePermutation();
		iteratePostFixOpsLocation();
		
		return expressions;
		
	}
	
	
	private void buildValuePermutation()
	{

		for (int i=0; i<inputValues.size(); i++)
		{
			LinkedList<String> lst2 = new LinkedList<String>(inputValues);
			lst2.remove(i);

			for (int j=0; j<lst2.size(); j++)
			{
				LinkedList<String> lst3 = new LinkedList<String>(lst2);
				lst3.remove(j);

				for (int k=0; k<lst3.size(); k++)
				{
					LinkedList<String> lst4 = new LinkedList<String>(lst3);
					lst4.remove(k);

					for (int l=0; l<lst4.size(); l++)
					{
						LinkedList<String> rslt = new LinkedList<String>();
						rslt.addLast(inputValues.get(i));
						rslt.addLast(lst2.get(j));
						rslt.addLast(lst3.get(k));
						rslt.addLast(lst4.get(l));
						permutations.add(rslt);

					}

				}
			}

		}

	}


	private void iteratePostFixOpsLocation()
	{
		int[] input = {2, 3, 4, 5};  

		for (int i = 0; i < input.length; i++) { 
			for (int j = i + 1; j < input.length; j++) { 
				
				if (i == 0 && i == j - 1)
					continue;

				for(LinkedList<String> lst: permutations)
				{
					int opsLoc[] = new int[3];
					opsLoc[0] = input[i];
					opsLoc[1] = input[j];
					opsLoc[2] = 6;
					generateExpressions(lst, opsLoc);
				}


			} 
		} 

	}

	private void generateExpressions(LinkedList<String> val, int[] loc)
	{
		String[] ops = {"+","-","*","/"};

		for (int i = 0; i < ops.length; i++)
			for (int j = 0; j < ops.length; j++)
				for (int k = 0; k < ops.length; k++)
				{
					LinkedList<String> rslt = new LinkedList<String>(val);
					rslt.add(loc[0], ops[i]);
					rslt.add(loc[1], ops[j]);
					rslt.add(loc[2], ops[k]);
					
					expressions.add(rslt);
					
					System.out.println(rslt.toString());
					
				}

	}


}

