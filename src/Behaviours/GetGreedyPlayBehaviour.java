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

import Agents.GreedyAGENT;
import Agents.SmartBotAGENT;

public class GetGreedyPlayBehaviour extends OneShotBehaviour {
	private Jogo sueca;
	private Jogador player;
	public GetGreedyPlayBehaviour(Jogo sueca,Jogador player) {
		this.sueca=sueca;
		this.player=player;
	}

	@Override
	public void action() {
		
		
		ACLMessage msg = this.myAgent.blockingReceive(); //RECEIVES SmartBOT REQUEST
		
		Carta carta = retornaGreedyPlay();

		//SEND REQUEST
		ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
//		inform.addReceiver(msg.getSender());
		inform.addReceiver(new AID("SmartBotAgent1",AID.ISLOCALNAME));
		inform.setLanguage("Portugues");
		inform.setOntology("Sueca-Ronda");
		inform.setContent(carta.toString());
		this.myAgent.send(inform);

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
		return biggest;
	}
	
	



}
