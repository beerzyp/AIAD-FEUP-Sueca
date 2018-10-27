import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class GameAGENT extends Agent{
	Jogo suecaGame;
	Jogador playerToMove;
	Behaviour playerMove1,playerMove2,playerMove3,playerMove4,checkWinnerRound,checkWinnerRound1,checkWinnerRoundBehaviour,checkWinnerRoundBehaviour1;
	static int numRonda;
	public GameAGENT(Jogo sueca) {
		this.suecaGame=sueca;
		System.out.println("TRUNFO: " + this.suecaGame.getTrunfo().toString() + "\n\n");
		numRonda=0;
		
	}
	int winner;
	public void setWinner(int n) {
		winner=n;
	}
	public void setup() {
		/*
		 * JOGADA de SUECA
		 */

		
		//Behaviour allRoundsInGame = new RoundBehaviour();

		Behaviour seqRoundBehaviour = new SequentialBehaviour();
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round);
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer1(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer2(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer3(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer4(),round));
		checkWinnerRound=new CheckWinnerBehaviour(suecaGame,round); //CHECKWINNER
		
		checkWinnerRoundBehaviour = new SequentialBehaviour();
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);

		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		//this.addBehaviour(checkWinnerRoundBehaviour); // addbehaviour
		
		
		Behaviour seqRoundBehaviour1 = new SequentialBehaviour();
		Round round1=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round1);
		((SequentialBehaviour) seqRoundBehaviour1).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer1(),round1));
		((SequentialBehaviour) seqRoundBehaviour1).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer2(),round1));
		((SequentialBehaviour) seqRoundBehaviour1).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer3(),round1));
		((SequentialBehaviour) seqRoundBehaviour1).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer4(),round1));
		
		checkWinnerRound1=new CheckWinnerBehaviour(suecaGame,round1);
		
		checkWinnerRoundBehaviour1 = new SequentialBehaviour();
		((SequentialBehaviour) checkWinnerRoundBehaviour1).addSubBehaviour(seqRoundBehaviour1);
		((SequentialBehaviour) checkWinnerRoundBehaviour1).addSubBehaviour(checkWinnerRound1);
		
		Behaviour TwoRounds = new SequentialBehaviour();
		((SequentialBehaviour) TwoRounds).addSubBehaviour(checkWinnerRoundBehaviour);
		((SequentialBehaviour) TwoRounds).addSubBehaviour(checkWinnerRoundBehaviour1);
		this.addBehaviour(TwoRounds);
		//this.myAgent.addBehaviour(checkWinnerRoundBehaviour);
		/*RoundBehaviour b1 = new RoundBehaviour(suecaGame);
		addBehaviour(b1);*/
		
	}
	
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
