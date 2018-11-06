package Agents;

import Behaviours.GetGreedyPlayBehaviour;
import Behaviours.MakeMoveBehaviour;
import GameLogic.Jogador;
import GameLogic.Jogo;
import jade.core.Agent;

public class GreedyAGENT extends Agent{
	private Jogo sueca;
	private Jogador player;
	public GreedyAGENT(Jogo sueca,Jogador player) {
		this.sueca=sueca;
		this.player=player;
		
	}
	public void setup() {
		addBehaviour(new GetGreedyPlayBehaviour(this.sueca,this.player));

	}

	protected void takeDown() {

	}

}
