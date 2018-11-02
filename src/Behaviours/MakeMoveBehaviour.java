package Behaviours;
import java.util.Random;

import Agents.RandomBotAGENT;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class MakeMoveBehaviour extends Behaviour{
//	private Round ronda;
//	private Mao hand;
	private Jogo sueca;
	private int player;
	public MakeMoveBehaviour(Jogo sueca) {
		this.sueca=sueca;
	}
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		
		String message = request.getContent();
		
		System.out.println(message);
		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		String cartaValue=((RandomBotAGENT)this.myAgent).returnPLay().toString();
		inform.setContent(cartaValue);
		this.myAgent.send(inform);
		System.out.println("agent " + this.myAgent.getAID().getLocalName() + "\n");
	}
	

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
