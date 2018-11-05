package Behaviours;
import java.util.Random;

import Agents.RandomBotAGENT;
import Agents.SmartBotAGENT;
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
	private String typeOfBot;
	public MakeMoveBehaviour(Jogo sueca,String typeOfBot) {
		this.sueca=sueca;
		this.typeOfBot=typeOfBot;
	}
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		
		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		String cartaValue="";
		cartaValue=((RandomBotAGENT)this.myAgent).returnPLay().toString();
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
