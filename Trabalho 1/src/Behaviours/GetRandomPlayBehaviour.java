package Behaviours;

import java.util.ArrayList;
import java.util.Random;

import Agents.GreedyAGENT;
import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import jade.core.AID;
import GameLogic.Jogador;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import Agents.CortaAGENT;

public class GetRandomPlayBehaviour extends CyclicBehaviour {

	private Jogo sueca;
	private Jogador player;
	public GetRandomPlayBehaviour(Jogo sueca) {
		// TODO Auto-generated constructor stub
		this.sueca = sueca;
	}


	@Override
	public void action() {


		ACLMessage msg = this.myAgent.blockingReceive(); //RECEIVES SmartBOT REQUEST
		this.player=this.sueca.getPlayer(getPlayer(msg.getSender().getName()));
		Carta carta = retornaRandom();

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

	public Carta retornaRandom() {
		Random r = new Random();
		int Low = 0;
		int High =this.player.getPlayerHand().getMao().size();
		int Result = r.nextInt(High-Low) + Low;
		return this.player.getPlayerHand().getCartaAt(Result);

	}

}