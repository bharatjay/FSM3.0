package edu.buffalo.cse.jive.finiteStateMachine.parser.expression.quantified;
import edu.buffalo.cse.jive.finiteStateMachine.models.Context;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.value.StringValueExpression;

public class QuantifiedVariableExpression extends StringValueExpression{
	
	String qvar;
	QuantifiedVariable table; 

	public Object getValue() {
		return QuantifiedVariable.QuantifiedVarsMap.get(qvar);
	}

	public QuantifiedVariableExpression(String qvar) {
		super(qvar);
		this.qvar=qvar;
}
		
	@Override
	public Boolean evaluate(Context context) {
		return true;
	}
}