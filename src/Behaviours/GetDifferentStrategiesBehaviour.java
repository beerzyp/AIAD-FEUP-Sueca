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

public class GetDifferentStrategiesBehaviour extends Behaviour {
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
		ACLMessage startMsg = this.myAgent.blockingReceive(); //RECEIVES ASK FOR PLAYER MOVE INITIATION MESSAGE
		//Broadcasts message to get different strategies from strat agents
		ACLMessage inform= new ACLMessage(ACLMessage.REQUEST);

		AID botStrategy=new AID(botStrategies.get(0), AID.ISLOCALNAME);
		inform.addReceiver(botStrategy);
		inform.setLanguage("Portugues");
		inform.setOntology("Strat");
		this.myAgent.send(inform);
	
		while(true) { //n tries for every Strategy (bot to broadcast) maybe-> timeouts 
			//RECEIVES MESSAGE FROM STACK IF AGENT strategy X SEND REQUEST
			if(((SmartBotAGENT)this.myAgent).getValidLogicPlays().size()==botStrategies.size()){
//				ACLMessage returnToAskForPlayMove= new ACLMessage(ACLMessage.INFORM);
//				returnToAskForPlayMove.addReceiver(new AID("gameAgent", AID.ISLOCALNAME));
//				returnToAskForPlayMove.setLanguage("Portugues");
//				returnToAskForPlayMove.setOntology("Sueca-Jogada");
//				this.myAgent.send(returnToAskForPlayMove);
				this.done();
			}
			ACLMessage msg = this.myAgent.blockingReceive();
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
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Wait For Logic confirmation
				final ACLMessage getLogicConfirmation = this.myAgent.blockingReceive();
				String validOrNotPlay= getLogicConfirmation.getContent();
				String[] aux = validOrNotPlay.split(",");
				String validOrNot = aux[0];
				String play = aux[1];
				Carta cardConfirmed=null;
				cardConfirmed= new Carta(this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNome(play), this.sueca.getPlayer1().getPlayerHand().getMao().get(0).convertStringToNaipe(play));
				if(validOrNot.equals("VALID-PLAY")) {
					cardConfirmed= new Carta(cardConfirmed.convertStringToNome(play), cardConfirmed.convertStringToNaipe(play));
					((SmartBotAGENT)this.myAgent).insertLogicPlays(new Pair<String,Carta>(agentStrategy,cardConfirmed));
				}
			}
			else {
				System.out.println("No Strategy Bots Available");
				this.block(); 		//<... do something else like block() ...>
			}
	
		}
	}
	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
