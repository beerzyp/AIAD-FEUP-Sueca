package Behaviours;

import GameLogic.Carta;
import GameLogic.Jogador;
import GameLogic.Jogo;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Random;

import Agents.GreedyAGENT;
import Agents.SmartBotAGENT;

public class GetGreedyPlayBehaviour extends CyclicBehaviour {
	private Jogo sueca;
	private Jogador player;
	public GetGreedyPlayBehaviour(Jogo sueca) {
		this.sueca=sueca;

	}

	@Override
	public void action() {
		
		
		ACLMessage msg = this.myAgent.blockingReceive(); //RECEIVES SmartBOT REQUEST
		this.player=this.sueca.getPlayer(getPlayer(msg.getSender().getName()));
		Carta carta = retornaGreedyPlay();

		//SEND REQUEST
		ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
		inform.addReceiver(msg.getSender());
		//inform.addReceiver(new AID("SmartBotAgent",AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Ronda");
		inform.setContent(carta.toString());
		this.myAgent.send(inform);

	}

	private int getPlayer(String name) {
		if(name.contains("SmartBotAgent1")) {
			return 1;
		}
		else if(name.contains("SmartBotAgent2")) {
			return 2;
		}
		else if(name.contains("SmartBotAgent3")) {
			return 3;
		}
		else if(name.contains("SmartBotAgent4")) {
			return 4;
		}
		return 0;
	}

	private Carta retornaGreedyPlay() {
		int naipeOfCurrRound = ((GreedyAGENT)this.myAgent).returnNaipeOfCurrRound();
		ArrayList<Carta> allCardsOfNaipe = new 	ArrayList<Carta>();
		for(int i=0;i<this.player.getPlayerHand().getMao().size();i++) {
			if(naipeOfCurrRound==this.player.getPlayerHand().getMao().get(i).getNaipe()) {
				allCardsOfNaipe.add(this.player.getPlayerHand().getMao().get(i));
			}
		}
		int biggestValue=0;
		Carta biggest=null;
		for(int j=0;j<allCardsOfNaipe.size();j++) {
			if(allCardsOfNaipe.get(j).getPonto()>biggestValue) {
				biggest=allCardsOfNaipe.get(j);
				biggestValue=allCardsOfNaipe.get(j).getPonto();
			}
		}
		Random r = new Random();
		int Low = 0;
		int High =this.player.getPlayerHand().getMao().size();
		int Result = r.nextInt(High-Low) + Low;
		if(biggest==null)
			return this.player.getPlayerHand().getCartaAt(Result);
		return biggest;
	}
	
	



}
