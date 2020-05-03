package edu.buffalo.cse.jive.finiteStateMachine.parser.expression.quantified;
import edu.buffalo.cse.jive.finiteStateMachine.models.Context;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.expression.Expression;
import java.util.HashMap;


public class QuantifiedVariable extends Expression{
	
	static HashMap<String,Object> QuantifiedVarsMap=new HashMap<>();  //Hash Map to save Quantified Variable as it's key and its value as value of key


	@Override
	public Boolean evaluate(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
