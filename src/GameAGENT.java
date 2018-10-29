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
	int winner=0;
	public void setWinner(int n) {
		winner=n;
	}
	public void setup() {
		/*
		 * JOGADA de SUECA
		 */
		//Behaviour allRoundsInGame = new RoundBehaviour();
		ArrayList<ArrayList<SequentialBehaviour>> allRounds = new ArrayList<ArrayList<SequentialBehaviour>>();
		Behaviour TenRounds = new SequentialBehaviour();
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callBaseRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		
		
		this.addBehaviour(TenRounds);
		
	}
	
	
	public Behaviour callBaseRoundBehaviour() {
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
		return checkWinnerRoundBehaviour;
	}
	
	
	public Behaviour callNextRoundBehaviour() {
		Behaviour seqRoundBehaviour = new SequentialBehaviour();
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round);
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,round));
		checkWinnerRound=new CheckWinnerBehaviour(suecaGame,round); //CHECKWINNER
		
		checkWinnerRoundBehaviour = new SequentialBehaviour();
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);

		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		//this.addBehaviour(checkWinnerRoundBehaviour); // addbehaviour
		return checkWinnerRoundBehaviour;
	}
	public Jogo getSueca(){
		return this.suecaGame;
	}
	
}
