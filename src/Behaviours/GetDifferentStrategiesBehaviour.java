package Behaviours;

import java.util.ArrayList;
import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.util.Pair;

public class GetDifferentStrategiesBehaviour extends OneShotBehaviour {
	private ArrayList<String> botsToBroadcast;
	private ArrayList<Pair<String,Carta>> validLogicPlays;
	private ArrayList<Pair<String,Carta>> strategyPlays;
	private Jogo sueca;
	public GetDifferentStrategiesBehaviour(ArrayList<String> strats,Jogo sueca) {
		botsToBroadcast=new ArrayList<String>(strats);
		strategyPlays = new ArrayList<Pair<String,Carta>>();
		validLogicPlays = new ArrayList<Pair<String,Carta>>();
		this.sueca=sueca;
	}
	@Override
	public void action() {
		helpBot(botsToBroadcast);

	}
	public void helpBot(ArrayList<String> botStrategies) {
		//Broadcasts message to get different strategies from strat agents
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);
		for(int i=0;i<botStrategies.size();i++) { //sends BroadCast Message to N agents
			AID botStrategy=new AID(botStrategies.get(i), AID.ISLOCALNAME);
			inform.addReceiver(botStrategy);
			inform.setLanguage("Portugues");
			inform.setOntology("Strat");
			this.myAgent.send(inform);
		}
		while(true) { //n tries for every Strategy (bot to broadcast) maybe-> timeouts 
			//RECEIVES MESSAGE FROM STACK IF AGENT strategy X SEND REQUEST
			if(this.strategyPlays.size()==botStrategies.size())
				break;
			ACLMessage msg = this.myAgent.receive();
			String carta =null;
			if (msg != null) {
				String agentStrategy = msg.getSender().getName();
				carta = msg.getContent();
				System.out.println(carta + "\n");
				Carta attempt= new Carta();
				attempt= new Carta(this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNome(carta), this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNaipe(carta));
				
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
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String validOrNotPlay= getLogicConfirmation.getContent();
				String[] aux = validOrNotPlay.split(",");
				String validOrNot = aux[0];
				String play = aux[1];
				Carta cardConfirmed=null;
				cardConfirmed= new Carta(this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNome(play), this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNaipe(play));
				if(validOrNot=="VALID-PLAY") {
					cardConfirmed= new Carta(cardConfirmed.convertStringToNome(play), cardConfirmed.convertStringToNaipe(play));
					this.validLogicPlays.add(new Pair<String,Carta>(agentStrategy,cardConfirmed));
				}
			}
			else {
				System.out.println("No Strategy Bots Available");
				this.block(); 		//<... do something else like block() ...>
			}
	
		}
	}

}
