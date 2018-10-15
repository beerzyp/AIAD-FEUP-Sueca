import jade.core.behaviours.Behaviour;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

public class AskForPlayerMove extends Behaviour {
	//AgentGame
	public AskForPlayerMove() {
		
	}
	@Override
	public void action() {
		//SEND REQUEST
		System.out.println("entrou");
		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(new AID("randomBotAgent", AID.ISLOCALNAME));
		request.setLanguage("Portugues");
		request.setOntology("Sueca-Ronda");
		try {
			request.setContentObject(((AgentGame) this.myAgent).getSueca());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.myAgent.send(request);
		//RECEIVE INFORM
		final ACLMessage inform = this.myAgent.blockingReceive();
		//receive Play From Agent
		System.out.println(inform.getContent());
		this.done();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
