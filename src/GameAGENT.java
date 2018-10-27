import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class GameAGENT extends Agent{
	Jogo suecaGame;
	Jogador playerToMove;

	public GameAGENT(Jogo sueca) {
		this.suecaGame=sueca;
		System.out.println("TRUNFO: " + this.suecaGame.getTrunfo().toString() + "\n\n");
	}
	public void setup() {
		/*
		 * JOGADA de SUECA
		 */


		//Behaviour allRoundsInGame = new RoundBehaviour();
	
		RoundBehaviour b1 = new RoundBehaviour(suecaGame);
		addBehaviour(b1);
		
	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
