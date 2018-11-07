package Behaviours;

import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class GetCortaPlay extends CyclicBehaviour {

	private Jogo sueca;

	public GetCortaPlay(Jogo sueca) {
		// TODO Auto-generated constructor stub
		this.sueca = sueca;
	}

	@Override
	public void action() {
		//RECEIVE 
		ACLMessage msg = this.myAgent.blockingReceive();
		
		String carta = retornaTrunfo();

		//SEND REQUEST
		ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
		inform.addReceiver(msg.getSender());
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Ronda");
		inform.setContent(carta);
		this.myAgent.send(inform);
	}

	private String retornaTrunfo() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
