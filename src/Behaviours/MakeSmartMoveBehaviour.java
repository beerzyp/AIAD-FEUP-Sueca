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
		final ACLMessage request= this.myAgent.blockingReceive();
		byte[] cardsByComma = request.getByteSequenceContent();
		String s1 =  new String(cardsByComma,StandardCharsets.UTF_8);

		//SEND PLAY TO LOGIC
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		inform.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Jogada");
		String cartaValue="";
		cartaValue=((SmartBotAGENT)this.myAgent).returnPLay().toString();
		inform.setContent(cartaValue);
		this.myAgent.send(inform);
		((SmartBotAGENT) this.myAgent).getCartasDaRonda(s1);
		((SmartBotAGENT) this.myAgent).removeCartasJogadasDaRonda();
		
		((SmartBotAGENT) this.myAgent).printOddOfNaipe();
		System.out.println("agent " + this.myAgent.getAID().getLocalName() + "\n");

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
