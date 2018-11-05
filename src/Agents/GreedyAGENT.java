package Agents;

import Behaviours.GetGreedyPlayBehaviour;
import Behaviours.MakeMoveBehaviour;
import GameLogic.Jogo;
import jade.core.Agent;

public class GreedyAGENT extends Agent{
	private Jogo sueca;
	public GreedyAGENT(Jogo sueca) {
		this.sueca=sueca;
		
	}
	public void setup() {
		addBehaviour(new GetGreedyPlayBehaviour(this.sueca));

	}

	protected void takeDown() {

	}

}
