import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class GameAGENT extends Agent{
	Jogo suecaGame;
	Jogador playerToMove;
	Behaviour playerMove1,playerMove2,playerMove3,playerMove4,checkWinnerRound;
	public GameAGENT(Jogo sueca) {
		this.suecaGame=sueca;
		System.out.println("TRUNFO: " + this.suecaGame.getTrunfo().toString() + "\n\n");
	}
	public void setup() {
		/*
		 * JOGADA de SUECA
		 */


		//Behaviour allRoundsInGame = new RoundBehaviour();
		Behaviour seqRoundBehaviour = new SequentialBehaviour(this);
		Behaviour checkWinnerRoundBehaviour = new SequentialBehaviour(this);
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round);
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer1(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer2(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer3(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer4(),round));

		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);
		checkWinnerRound=new CheckWinnerBehaviour(suecaGame,round);
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		this.addBehaviour(checkWinnerRoundBehaviour);
		//this.myAgent.addBehaviour(checkWinnerRoundBehaviour);
		/*RoundBehaviour b1 = new RoundBehaviour(suecaGame);
		addBehaviour(b1);*/
		
	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
