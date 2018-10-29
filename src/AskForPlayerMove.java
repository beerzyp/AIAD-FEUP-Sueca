import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.io.IOException;
import java.util.ArrayList;


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
	public AskForPlayerMove(Jogo Sueca) {
		sueca=Sueca;
		
	}
	private static int numOfTimesEntered=0;
	
	public AskForPlayerMove(Jogo suecaGame, Jogador playerToMove,Round round) {
		sueca=suecaGame;
		c1=new Carta();
		player=playerToMove;
		currRound=round;
	}
	public AskForPlayerMove(Jogo suecaGame,Round round) {
		sueca=suecaGame;
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

		String botToPlay = "randomBotAgent"+playerToMove;
		//SEND REQUEST
		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(new AID(botToPlay, AID.ISLOCALNAME));
		request.setLanguage("Portugues");
		request.setOntology("Sueca-Ronda");
		request.setContent("Your turn");
		this.myAgent.send(request);
		
		//RECEIVE INFORM
		final ACLMessage inform = this.myAgent.blockingReceive();
		String carta= inform.getContent();
		System.out.println(carta + "\n");
		Carta attempt;
		attempt= new Carta(c1.convertStringToNome(carta), c1.convertStringToNaipe(carta));
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
