package Behaviours;
import java.util.ArrayList;

import Agents.GameAGENT;
import GameLogic.Jogo;
import GameLogic.Round;
import javafx.util.*;
import jade.core.behaviours.OneShotBehaviour;

public class CalcScoreRoundBehaviour extends OneShotBehaviour {
	public Jogo sueca;
	public Round round;
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public CalcScoreRoundBehaviour(Jogo sueca,Round ronda) {
		this.sueca=sueca;
		this.round=ronda;
	}
	@Override
	public void action() {
		//this.calculaPontosRonda(this.round);
		System.out.println("entrou" + ((GameAGENT)this.myAgent).getRondas().size()+ "\n");
		GameAGENT.insertScore(this.calculateScore(this.round));
		
	}
	

public Pair<String,Integer> calculateScore (Round rondas) {
		int lastRoundWinner=this.sueca.getGameLogic().winner(rondas, sueca);
		int realWinner=rondas.returnTableHand().get(lastRoundWinner-1).getValue();
		System.out.print("Calculate Score");
		int points=0;
		if( realWinner== 1 || realWinner== 3) {
			for(int j = 0; j < 4; j++ ) {
				points+= rondas.returnTableHand().get(j).getKey().getPonto();
			}	
			return new Pair<String,Integer>("A",points);
		}else {
			for(int j = 0; j < 4; j++ ) {
				points+= rondas.returnTableHand().get(j).getKey().getPonto();
			}	
			return new Pair<String,Integer>("B",points);
		}			
		
}	
		
		
	
		
	
	private void calculaPontosRonda(Round round2) {
		// TODO Auto-generated method stub
		
	}

}
