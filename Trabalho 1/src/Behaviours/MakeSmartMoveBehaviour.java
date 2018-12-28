package Behaviours;

import java.nio.charset.StandardCharsets;

import Agents.RandomBotAGENT;
import Agents.SmartBotAGENT;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class MakeSmartMoveBehaviour extends Behaviour {
	private Jogo sueca;
	private String carta;
	public MakeSmartMoveBehaviour(Jogo sueca, String string) {
		this.sueca=sueca;
		this.carta=string;
	}

	@Override
	public void action() {
		this.myAgent.blockingReceive();
		((SmartBotAGENT) this.myAgent).removeCartasJogadasDaRonda();
		((SmartBotAGENT) this.myAgent).getCartasDaRondas();
		System.out.println("\nagent " + this.myAgent.getAID().getLocalName() + "\n");
		((SmartBotAGENT) this.myAgent).printOddOfNaipe();
		System.out.println("\nagent " + this.myAgent.getAID().getLocalName() + "\n");
		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.INFORM);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		String cartaValue="";
		cartaValue=((SmartBotAGENT)this.myAgent).returnPLay().toString();
		String value = "valid,";
		inform.setContent(value + cartaValue);
		this.myAgent.send(inform);

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
