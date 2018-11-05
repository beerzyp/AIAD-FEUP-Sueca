package Behaviours;

import java.util.ArrayList;
import Agents.SmartBotAGENT;
import GameLogic.Carta;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import javafx.util.Pair;

public class GetDifferentStrategiesBehaviour extends Behaviour {
	ArrayList<String> botsToBroadcast;
	public GetDifferentStrategiesBehaviour(ArrayList<String> strats) {
		botsToBroadcast=new ArrayList<String>(strats);
	}
	@Override
	public void action() {
		Carta c1 = helpBot(botsToBroadcast);

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Carta helpBot(ArrayList<String> botStrategies) {
		//Broadcasts message to get different strategies from strat agents
		ArrayList<Pair<String,Carta>> validLogicPlays;
		ArrayList<Pair<String,Carta>> strategyPlays;
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		for(int i=0;i<botStrategies.size();i++) {
			AID botStrategy=new AID(botStrategies.get(i), AID.ISLOCALNAME);
			inform.addReceiver(botStrategy);
		}
		inform.setLanguage("Portugues");
		inform.setOntology("Strat");
		this.myAgent.send(inform);
		
		for(int j=0;j<botStrategies.size();j++) { //n tries for every Strategy (bot to broadcast) maybe-> timeouts 
			//RECEIVES MESSAGE FROM STACK IF AGENT strategy X SEND REQUEST
			ACLMessage msg = this.myAgent.receive();
			String carta =null;
			if (msg != null) {
				carta = msg.getContent();
				System.out.println(carta + "\n");
				Carta attempt= new Carta();
				attempt= new Carta(attempt.convertStringToNome(carta), attempt.convertStringToNaipe(carta));
				
				//SEND PLAY TO VALIDATE PLAY TO LOGIC
				ACLMessage informLogic= new ACLMessage(ACLMessage.REQUEST);
				informLogic.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
				informLogic.setLanguage("Portugues");
				informLogic.setOntology("Logic-Validation");
				String cartaValue="";
				cartaValue=carta;
				informLogic.setContent("attempt,"+cartaValue);
				this.myAgent.send(informLogic);
				
				//Wait For Logic confirmation
				this.myAgent.blockingReceive();
				
			}
			else {
				System.out.println("No Strategy Bots Available");
				this.block(); 		//<... do something else like block() ...>
			}
		}
		return null;
	}

}
