package Agents;
import java.util.ArrayList;

import Behaviours.AskForPlayerMove;
import Behaviours.CalcScoreRoundBehaviour;
import Behaviours.CallNeuralBehaviour;
import Behaviours.CheckWinnerBehaviour;
import Behaviours.CreateDataSetBehaviour;
import GameLogic.Carta;
import GameLogic.Jogador;
import GameLogic.Jogo;
import GameLogic.Round;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class GameAGENT extends Agent{
	Jogo suecaGame;
	Jogador playerToMove;
	Behaviour playerMove1,playerMove2,playerMove3,playerMove4,checkWinnerRound,checkWinnerRound1,checkWinnerRoundBehaviour,checkWinnerRoundBehaviour1,calculateScore;
	String typeOfBot;
	static int numRonda;
	private ArrayList<Round> rondas;
	private static int teamPointsA=0,teamPointsB=0;
	private static int initialteamPointsA=0,initialteamPointsB=0;
	public static int getInitialteamPointsB() {
		return initialteamPointsB;
	}
	public static void setInitialteamPointsB(int initialteamPointsB) {
		GameAGENT.initialteamPointsB = initialteamPointsB;
	}
	public static void setInitialteamPointsA(int initialteamPointsA) {
		GameAGENT.initialteamPointsA = initialteamPointsA;
	}
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
	public int winner=0;
	private CreateDataSetBehaviour createDataSets;
	private CallNeuralBehaviour neuralbehaviour;
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
		//((SequentialBehaviour) TenRounds).addSubBehaviour(createDataSets=new CreateDataSetBehaviour(this.suecaGame,false));
		//((SequentialBehaviour) TenRounds).addSubBehaviour(createDataSets=new CreateDataSetBehaviour(this.suecaGame,true));
		//((SequentialBehaviour) TenRounds).addSubBehaviour(neuralbehaviour=new CallNeuralBehaviour());

		
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
		CalcScoreRoundBehaviour calculateScore = new CalcScoreRoundBehaviour(this.suecaGame,round);//Score
		
		checkWinnerRoundBehaviour = new SequentialBehaviour();
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(calculateScore);
		//this.addBehaviour(checkWinnerRoundBehaviour); // addbehaviour
		return checkWinnerRoundBehaviour;
	}
	

	public static int getTeamPointsA() {
		return teamPointsA;
	}
	public void setTeamPointsA(int teamPointsA) {
		this.teamPointsA = teamPointsA;
	}
	public static int getTeamPointsB() {
		return teamPointsB;
	}
	public void setTeamPointsB(int teamPointsB) {
		this.teamPointsB = teamPointsB;
	}
	public Jogo getSueca(){
		return this.suecaGame;
	}
	public void insertRonda(Round ronda) {
		this.rondas.add(ronda);	
	}
	
	public static boolean insertScore(Pair<String, Integer> calculateScore2) {
		if(calculateScore2.getKey()=="A") {
			teamPointsA+=calculateScore2.getValue();
			System.out.print("Equipa A pontos: "+ Integer.toString(GameAGENT.getTeamPointsA())+ "\n");
			return true;
		}
		else if(calculateScore2.getKey()=="B") {
			teamPointsB+=calculateScore2.getValue();
			System.out.print("Equipa B pontos: " + Integer.toString(GameAGENT.getTeamPointsB())+ "\n");
			return true;
		}
		else return false;
	}
	
}
