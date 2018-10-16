import jade.core.behaviours.Behaviour;

import java.io.IOException;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import javafx.util.Pair;

public class AskForPlayerMove extends Behaviour {
	//AgentGame
	private Jogo sueca;
	public AskForPlayerMove(Jogo Sueca) {
		sueca=Sueca;
	}
	
	private int roundNumber=1;
	
	@Override 
	public void action() {
		
		while(roundNumber==1){
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(sueca, jogadas);
		this.sueca.insertRound(round);
		System.out.println("entrou");
		//SEND REQUEST
		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(new AID("randomBotAgent", AID.ISLOCALNAME));
		request.setLanguage("Portugues");
		request.setOntology("Sueca-Ronda");
		request.setContent("Your turn");
		this.myAgent.send(request);
		
		//RECEIVE INFORM
		final ACLMessage inform = this.myAgent.blockingReceive();
		System.out.println(inform.getContent());
		Carta attempt = new Carta(null, null);
		if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer1(), round)){
			roundNumber++;
			break;
		}
		//receive Play From Agent

		}
		
		this.done();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
