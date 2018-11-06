package Agents;

import java.util.Random;

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
	
	public int returnNaipeOfCurrRound() {
		Random r = new Random();
		if(this.sueca.getMatchRounds().get(0).getNumPlays()==0)
			return (r.nextInt(4-0) + 0);
		for(int i=0;i<this.sueca.getMatchRounds().size();i++) {
			if(this.sueca.getMatchRounds().get(i).getNumPlays()<4) {
				return this.sueca.getMatchRounds().get(i).returnTableHand().get(0).getKey().getNaipe();
			}
		}
		return (r.nextInt(4-0) + 0);
	}

}
