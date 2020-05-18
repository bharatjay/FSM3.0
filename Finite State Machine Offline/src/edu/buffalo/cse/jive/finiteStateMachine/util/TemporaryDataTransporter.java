package edu.buffalo.cse.jive.finiteStateMachine.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.buffalo.cse.jive.finiteStateMachine.models.State;

public class TemporaryDataTransporter {
	
	public static Set<State> F_success_states = new HashSet<State>();

	public static List<Pair<State, State>> path;  // path leading to E success
	public static boolean shouldHighlight;

	public static List<Pair<State, State>> getPath() {
		return path;
	}

	public static void setPath(List<Pair<State, State>> path) {
		TemporaryDataTransporter.path = path;
	}
	

	
	

}
