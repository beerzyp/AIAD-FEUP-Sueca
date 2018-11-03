package Behaviours;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Agents.GameAGENT;
import GameLogic.Carta;
import GameLogic.Jogador;
import GameLogic.Jogo;
import GameLogic.Round;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import javafx.util.Pair;

public class AskForPlayerMove extends OneShotBehaviour {

	//AgentGame
	private Carta c1;
	private Jogo sueca;
	private Jogador player;
	private Round currRound;
	private static int playerToMove=1;
	private String BotType;
	public AskForPlayerMove(Jogo Sueca,String botType) {
		sueca=Sueca;
		BotType=botType;
		
	}
	private static int numOfTimesEntered=0;
	
	public AskForPlayerMove(Jogo suecaGame, Jogador playerToMove,Round round) {
		sueca=suecaGame;
		c1=new Carta();
		player=playerToMove;
		currRound=round;
	}
	public AskForPlayerMove(Jogo suecaGame,Round round,String botType) {
		sueca=suecaGame;
		BotType=botType;
		currRound=round;
		c1=new Carta();
	}

//	private static int counter=1;
	
	public  static void incrementCounter() {
		if(playerToMove>=4) {
			playerToMove=1;
		}
		else playerToMove++;
	}
	public static void setCounter(int i) 
	{
		playerToMove=i;
	}
	
	
	public int nextPlayerToMove() {
		if(((GameAGENT)this.myAgent).winner==0)
			return 1;
		else return ((GameAGENT)this.myAgent).winner;
	}
	@Override 
	public void action() {
		
		if (numOfTimesEntered>=4) {
			playerToMove=nextPlayerToMove();
			numOfTimesEntered=0;
		}
		numOfTimesEntered++;
		boolean validPlay=false;
		while(!validPlay) {

		String botToPlay = this.BotType+playerToMove;
		//SEND REQUEST
		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(new AID(botToPlay, AID.ISLOCALNAME));
		request.setLanguage("Portugues");
		request.setOntology("Sueca-Ronda");
	
		//SEND REQUEST TO SMART BOT WITH BOARD STATE
		String s1="";
		for(int i=0;i<currRound.getNumPlays();i++) {
			if(i == 0)
				s1=currRound.returnTableHand().get(i).getKey().toString();
			else
				s1= currRound.returnTableHand().get(i).getKey().toString();			
		}
		byte[] cardsSequenceSplitByComma=s1.getBytes(StandardCharsets.UTF_8);
		request.setByteSequenceContent(cardsSequenceSplitByComma);
		this.myAgent.send(request);
		
		//RECEIVE INFORM
		final ACLMessage inform = this.myAgent.blockingReceive();
		String carta= inform.getContent();
		System.out.println(carta + "\n");
		Carta attempt= new Carta();
		attempt= new Carta(attempt.convertStringToNome(carta), attempt.convertStringToNaipe(carta));
		if(this.sueca.makeMove(attempt, playerToMove, currRound)) {
			validPlay=true;
			break;
		}
		//Tests play for current Round
		}
		currRound.printRonda();
		incrementCounter();
	}	
	



}
