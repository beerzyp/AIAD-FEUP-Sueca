package Behaviours;

import java.util.ArrayList;
import java.util.Stack;

import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.util.Pair;

public class GetDifferentStrategiesBehaviour extends CyclicBehaviour {
	private ArrayList<String> botsToBroadcast;
	private ArrayList<Pair<String,Carta>> validLogicPlays;
	private ArrayList<Pair<String,Carta>> strategyPlays;
	private Stack<String> stackOfAvailableStrategies;
	private String strategyUsed;
	private Jogo sueca;

	private static int numOfTimes=0;
	public GetDifferentStrategiesBehaviour(ArrayList<String> strats,Jogo sueca) {
		botsToBroadcast=new ArrayList<String>(strats);
		strategyPlays = new ArrayList<Pair<String,Carta>>();
		validLogicPlays = new ArrayList<Pair<String,Carta>>();
		this.sueca=sueca;
	    stackOfAvailableStrategies = new Stack<>();
	    for(int i=0;i<this.botsToBroadcast.size();i++) {
	    	stackOfAvailableStrategies.push(this.botsToBroadcast.get(i));
	    }

	    System.out.println("size:"+this.stackOfAvailableStrategies.size());
	}
	@Override
	public void action() {
		ACLMessage startMsg = this.myAgent.blockingReceive(); //RECEIVES ASK FOR PLAYER MOVE INITIATION MESSAGE
		//Broadcasts message to get different strategies from strat agents
		//n tries for every Strategy (bot to broadcast) maybe-> timeouts 
		boolean validPlay=false;
		//SENDS MESSAGE TO STRATS
		while(validPlay==false) {
			askForNextBroadCastPlay();
			ACLMessage msg = this.myAgent.blockingReceive(); // mesagem do greedy bot
			if (msg != null) {
				if(validatePlay(msg)) {
					validPlay=true;
				}
			}
			else {
				this.block(); 		//<... do something else like block() ...>
			}
		}
		this.done();

		
		//helpBot(botsToBroadcast);

	}
	public String getNextStackbot() {
		if(this.stackOfAvailableStrategies.empty()) {
			fillStackOfBots();
		}
		return this.stackOfAvailableStrategies.pop();
	}
	
	private void fillStackOfBots() {
	    for(int i=0;i<this.botsToBroadcast.size();i++) {
	    	stackOfAvailableStrategies.push(this.botsToBroadcast.get(i));
	    }
		
	}
	private void askForNextBroadCastPlay() {
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		String botToPlay=getNextStackbot();
		this.strategyUsed=botToPlay;
		System.out.println("Sending req to Strategy:" + botToPlay +"\n");
		AID botStrategy=new AID(botToPlay, AID.ISLOCALNAME);
		inform.addReceiver(botStrategy);
		inform.setLanguage("Portugues");
		inform.setOntology("Strat");
		this.myAgent.send(inform);
		
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
			if(this.myAgent.getAID().getName().contains("3")){
				this.sueca.addStrategy(this.strategyUsed);
			}
			return true;
		}
		else if(validOrNot.equals("NOT-VALID")) {
			System.out.println("chegou-> carta:" + cardConfirmed.toString());
			return false;
		}
		else return false;
	}

}
