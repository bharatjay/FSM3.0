package edu.buffalo.cse.jive.finiteStateMachine.parser.expression.quantified;
import edu.buffalo.cse.jive.finiteStateMachine.models.Context;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.core.VariableExpression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.expression.Expression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.relational.LessThanExpression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.value.ValueExpression;

import java.util.ArrayList;
import java.util.List;

public class AllExpression extends QuantifiedVariable {
	
	Expression exp;  //Object of Expression	
	String quantifiedVariableName;	
	int value;
	List<Integer> variableList=new ArrayList<Integer>();	
	
	//int from=-1;  //Starting of Loop	
	//int to=-1;  //Ending of Loop
	
	ValueExpression fromExpr, toExpr;
	
	Expression variableExpression;
	
	Boolean all_flag = true;

	public AllExpression(String quantifiedVariableName,ValueExpression fromExpr, ValueExpression toExpr, Expression exp)
	{
		this.quantifiedVariableName=quantifiedVariableName;
		this.fromExpr = fromExpr;
		this.toExpr = toExpr;
 		this.exp=exp;
 		this.all_flag = false;
	}
	
	public AllExpression(String quantifiedVariableName,VariableExpression variableExpression,Expression exp)
	{
		this.quantifiedVariableName=quantifiedVariableName;		
		this.variableExpression=variableExpression;
		this.exp=exp;
	}
	
	public Object getValue(String qvars) {
		return QuantifiedVarsMap.get(qvars);
	}

	@Override
	public Boolean evaluate(Context context) {
		// TODO Auto-generated method stub
		if (all_flag) {  // List Iteration:  all(N, listid, Expr)
			variableExpression.evaluate(context);
			String listString = (String) ((ValueExpression) variableExpression).getValue();
			System.out.println("AllExpression.listString= "+listString);
			String[] listValue = listString.split(" |\\]|\\[");
			for(String test:listValue) {
				System.out.println("AllExpression.test= "+test);
			}
			QuantifiedVarsMap.put(this.quantifiedVariableName,0);
			//QuantifiedVarsMap.put(this.quantifiedVariableName,listValue[0]);
			for(String qvar:listValue) {
				if(!(qvar.equals(" ") || qvar.equals("")))
					{Boolean currentResult;
					System.out.println("AllExpression.qvar= "+qvar);
					QuantifiedVarsMap.replace(quantifiedVariableName,Integer.parseInt(qvar));
					currentResult = exp.evaluate(context);				
					if(currentResult==false) {return false;}
			}}
		}
		else {	// For Iteration: for(N, from:to, Expr)
			
			fromExpr.evaluate(context);
			toExpr.evaluate(context);
			
			int low = (int) fromExpr.getValue();
			int high = (int) toExpr.getValue();
			
			QuantifiedVarsMap.put(this.quantifiedVariableName, low);
			
			for(int i = low; i <= high; i++) {
				Boolean currentResult;
				QuantifiedVarsMap.replace(quantifiedVariableName,i);
				currentResult = exp.evaluate(context);
				if(currentResult==false) {return false;}
			}
		}
		QuantifiedVarsMap.remove(quantifiedVariableName);
		return true;
	}


}
