
package edu.buffalo.cse.jive.finiteStateMachine.parser.expression.temporal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.buffalo.cse.jive.finiteStateMachine.models.Context;
import edu.buffalo.cse.jive.finiteStateMachine.models.State;
import edu.buffalo.cse.jive.finiteStateMachine.models.State.Status;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.expression.Expression;
import edu.buffalo.cse.jive.finiteStateMachine.parser.expression.expression.UnaryExpression;

/**
 * @author Bharat Jayaraman
 * @eauthor Amlan Gupta
 *
 */
public class AExpression extends UnaryExpression<Expression> {

	public AExpression(Expression expression) {
		super(expression);
	}

	@Override
	public Boolean evaluate(Context context) {
		return evaluate(null, context.getCurrentState(), new HashSet<State>(), context.getStates());
	}

	/**
	 * A Expression Convenience Implementation - Traverses the whole graph and marks
	 * all states as VALID_A or INVALID_A
	 * 
	 * @param prev
	 * @param curr
	 * @param visited
	 * @param expression
	 * @param states
	 * @return
	 */
	private Boolean evaluate(State prev, State curr, Set<State> visited, Map<State, Set<State>> states) {
		boolean currentResult = true;
		if (!states.get(curr).isEmpty()) {
			for (State next : states.get(curr)) {
				currentResult = getExpression().evaluate(new Context(curr, next, states)) && currentResult;
			}
		} 
		//else { currentResult = getExpression().evaluate(new Context(curr, null, states)) && currentResult;}
		if(currentResult)curr.setStatus(Status.VALID_A);
		else curr.setStatus(Status.VALID);
		if (visited.add(curr)) {
			for (State next : states.get(curr)) {
				currentResult = evaluate(curr, next, visited, states) && currentResult;
			}
		}
		return true;   // A operator always returns true
	}
}
