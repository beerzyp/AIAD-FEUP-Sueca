package Agents;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class ExitProcessBehaviour extends OneShotBehaviour {

	@Override
	public void action() {
			Runtime.getRuntime().halt(0);
		 //finalize();
	}



}
