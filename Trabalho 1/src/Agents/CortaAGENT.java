package Agents;

import Behaviours.MakeMoveBehaviour;

import java.util.Random;

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
	
	public int returnNaipeOfCurrRound() {
		Random r = new Random();
		if(this.sueca.getMatchRounds().get(0).getNumPlays()==0)
			return (r.nextInt(4-0) + 0);
		for(int i=0;i<this.sueca.getMatchRounds().size();i++) {
			if(this.sueca.getMatchRounds().get(i).getNumPlays()<4) {
				if(this.sueca.getMatchRounds().get(i).getNumPlays()==0)
					return (r.nextInt(4-0) + 0);
				else return this.sueca.getMatchRounds().get(i).returnTableHand().get(0).getKey().getNaipe();
			}
			
		}
		return (r.nextInt(4-0) + 0);
	}
	
}
