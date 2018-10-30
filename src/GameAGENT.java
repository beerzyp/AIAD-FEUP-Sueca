import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class GameAGENT extends Agent{
	Jogo suecaGame;
	Jogador playerToMove;
	Behaviour playerMove1,playerMove2,playerMove3,playerMove4,checkWinnerRound,checkWinnerRound1,checkWinnerRoundBehaviour,checkWinnerRoundBehaviour1;
	String typeOfBot;
	static int numRonda;
	private ArrayList<Round> rondas;
	public ArrayList<Round> getRondas() {
		return rondas;
	}
	public void setRondas(ArrayList<Round> rondas) {
		this.rondas = rondas;
	}
	public GameAGENT(Jogo sueca,String typeOfBot) {
		this.suecaGame=sueca;
		this.typeOfBot=typeOfBot;
		System.out.println("TRUNFO: " + this.suecaGame.getTrunfo().toString() + "\n\n");
		this.rondas=new ArrayList<Round>();
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
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		((SequentialBehaviour) TenRounds).addSubBehaviour(this.callNextRoundBehaviour());
		//CALL BEHAVIOUR COUNT POINTS
		
		
		this.addBehaviour(TenRounds);
		
	}
	
	/*
	 * CALL NEXT ROUND BEHAVIOUR
	 */
	public Behaviour callNextRoundBehaviour() {
		Behaviour seqRoundBehaviour = new SequentialBehaviour();
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round);
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,round,this.typeOfBot));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,round,this.typeOfBot));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,round,this.typeOfBot));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,round,this.typeOfBot));
		checkWinnerRound=new CheckWinnerBehaviour(suecaGame,round); //CHECKWINNER
		
		checkWinnerRoundBehaviour = new SequentialBehaviour();
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);

		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(new CalcScoreRoundBehaviour(this.suecaGame,round));
		//this.addBehaviour(checkWinnerRoundBehaviour); // addbehaviour
		return checkWinnerRoundBehaviour;
	}
	public Jogo getSueca(){
		return this.suecaGame;
	}
	public void insertRonda(Round ronda) {
		this.rondas.add(ronda);	
	}
	
}
