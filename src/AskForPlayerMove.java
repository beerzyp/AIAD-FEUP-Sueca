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
	public AskForPlayerMove(Jogo Sueca) {
		sueca=Sueca;
		
	}
	
	public AskForPlayerMove(Jogo suecaGame, Jogador playerToMove,Round round) {
		sueca=suecaGame;
		c1=new Carta();
		player=playerToMove;
		currRound=round;
	}

//	private static int counter=1;
	
//	public  static void incrementCounter() {
//		if(counter>=4) {
//			counter=1;
//		}
//		else counter++;
//	}
//	public static void setCounter(int i) 
//	{
//		counter=i;
//	}
	
	/*
	 * if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer1(), round)){
					this.sueca.getMao1().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer1().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer1().getJogNum()));
					System.out.println("size hand player1: " +this.sueca.getMao1().getMao().size());
					incrementCounter();
				}
	 */
	/*
	 * (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override 
	public void action() {
		
		
		
		boolean validPlay=false;
		while(!validPlay) {
		String botToPlay = "randomBotAgent"+player.getJogNum();
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
		if(this.sueca.makeMove(attempt, player, currRound)) {
			validPlay=true;
			break;
		}
		//Tests play for current Round
		}
	}	
	



}
