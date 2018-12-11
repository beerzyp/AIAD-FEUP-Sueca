package Behaviours;

import java.util.ArrayList;
import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.util.Pair;

public class GetDifferentStrategiesBehaviour extends Behaviour {
	private ArrayList<String> botsToBroadcast;
	private ArrayList<Pair<String,Carta>> validLogicPlays;
	private ArrayList<Pair<String,Carta>> strategyPlays;
	private Jogo sueca;
	public boolean validPlay=false;
	private static int numOfTimes=0;
	public GetDifferentStrategiesBehaviour(ArrayList<String> strats,Jogo sueca) {
		botsToBroadcast=new ArrayList<String>(strats);
		strategyPlays = new ArrayList<Pair<String,Carta>>();
		validLogicPlays = new ArrayList<Pair<String,Carta>>();
		this.sueca=sueca;
		
	}
	@Override
	public void action() {
		ACLMessage startMsg = this.myAgent.blockingReceive(); //RECEIVES ASK FOR PLAYER MOVE INITIATION MESSAGE
		//Broadcasts message to get different strategies from strat agents
		//n tries for every Strategy (bot to broadcast) maybe-> timeouts 
		//SENDS MESSAGE TO STRATS
		for(int i=0;i<this.botsToBroadcast.size();i++) {
			ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
			System.out.println("Sending req to Strategy:" + this.botsToBroadcast.get(i) +"\n");
			AID botStrategy=new AID(this.botsToBroadcast.get(i), AID.ISLOCALNAME);
			inform.addReceiver(botStrategy);
			inform.setLanguage("Portugues");
			inform.setOntology("Strat");
			this.myAgent.send(inform);
		}

		while(!validPlay) {
	
			ACLMessage msg = this.myAgent.blockingReceive(); // mesagem do greedy bot
			if (msg != null) {
				validatePlay(msg);
			}
			else {
				this.block(); 		//<... do something else like block() ...>
			}
		}


		//helpBot(botsToBroadcast);

	}
	
	public boolean validatePlay(ACLMessage msg) {
		String agentStrategy = msg.getSender().getName();
		String carta =null;
		carta = msg.getContent();
		System.out.println("received ply from Strat Agent:" + agentStrategy +"\n");
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
		final ACLMessage getLogicConfirmation = this.myAgent.blockingReceive();
		
		String validOrNotPlay= getLogicConfirmation.getContent();
		String[] aux = validOrNotPlay.split(",");
		String validOrNot = aux[0];
		String play = aux[1];
		Carta cardConfirmed=null;
		cardConfirmed= new Carta(this.sueca.getTrunfo().convertStringToNome(play), this.sueca.getTrunfo().convertStringToNaipe(play));
		if(validOrNot.equals("VALID-PLAY")) {
			cardConfirmed= new Carta(cardConfirmed.convertStringToNome(play), cardConfirmed.convertStringToNaipe(play));
			//System.out.println(((SmartBotAGENT)this.myAgent).getName() + "\n");
			((SmartBotAGENT)this.myAgent).insertLogicPlays(new Pair<String,Carta>(agentStrategy,cardConfirmed));

			ACLMessage informLogicValid= new ACLMessage(ACLMessage.INFORM);
			informLogicValid.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
			informLogicValid.setLanguage("Portugues");
			informLogicValid.setOntology("Logic-Validation");
			String cartaValueFinal="";
			cartaValueFinal=((SmartBotAGENT)this.myAgent).getValidLogicPlays().get(0).getValue().toString();
			informLogic.setContent("valid,"+cartaValue);
			this.myAgent.send(informLogic);
			numOfTimes++;
			System.out.println("entered: " + numOfTimes+"\n");
			return true;
		}
		else if(validOrNot.equals("NOT-VALID")) {
			System.out.println("chegou-> carta:" + cardConfirmed.toString());
			return false;
		}
		else return false;
	}
	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
