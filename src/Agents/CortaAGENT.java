package Agents;

import Behaviours.MakeMoveBehaviour;
import Behaviours.GetCortaPlay;
import GameLogic.Jogador;
import GameLogic.Jogo;
import jade.core.Agent;

public class CortaAGENT extends Agent{

	private Jogo sueca;
	private Jogador player;

	public CortaAGENT(Jogo sueca) {
		this.sueca = sueca;	
	}
	
	public void setup() {
		addBehaviour(new GetCortaPlay(this.sueca));
	}
	
	protected void takeDown() {

	}
	
}
