package edu.buffalo.cse.jive.finiteStateMachine.parser.expression.quantified;
import edu.buffalo.cse.jive.finiteStateMachine.models.Context;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.core.VariableExpression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.expression.Expression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.value.ValueExpression;


public class ExistExpression extends QuantifiedVariable {
	
	Expression exp;  //Object of Expression	
	String quantifiedVariableName;	
	int value;
	VariableExpression variableExpression;	
	int from=-1;  //Strating of Loop	
	int to=-1;  //Ending of Loop

	public ExistExpression(String quantifiedVariableName,int from,int to,Expression exp)
	{
		this.quantifiedVariableName=quantifiedVariableName;
		this.from=from;
		this.to=to;
		this.exp=exp;
	}
	
	public ExistExpression(String quantifiedVariableName,VariableExpression variableExpression,Expression exp)
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
		if(from==-1)
		{
			variableExpression.evaluate(context);
			String listString = (String) ((ValueExpression) variableExpression).getValue();
	
			String[] listValue = listString.split(" |\\]|\\[");
			QuantifiedVarsMap.put(this.quantifiedVariableName,listValue[0]);
			for(String qvar:listValue) {
				if(!(qvar.equals(" ") || qvar.equals("")))
					{Boolean currentResult;
					try {
						QuantifiedVarsMap.replace(quantifiedVariableName,Integer.parseInt(qvar)); }
					catch (NumberFormatException e) {
					    QuantifiedVarsMap.replace(quantifiedVariableName,qvar);
					}
					currentResult = exp.evaluate(context);				
					if(currentResult) {return true;}
			}
				}
		}
		else {
			QuantifiedVarsMap.put(this.quantifiedVariableName, this.from);
			for(int i=from;i<=to;i++) {
				Boolean currentResult;
				QuantifiedVarsMap.replace(quantifiedVariableName,i);
				currentResult = exp.evaluate(context);
				if(currentResult==true) {return true;}
			}
		}
		QuantifiedVarsMap.remove(quantifiedVariableName);
		return false;
	}


}

