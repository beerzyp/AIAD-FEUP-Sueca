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
public class GetCortaPlay extends CyclicBehaviour {

	private Jogo sueca;
	private Jogador player;
	public GetCortaPlay(Jogo sueca) {
		// TODO Auto-generated constructor stub
		this.sueca = sueca;
	}


	@Override
	public void action() {


		ACLMessage msg = this.myAgent.blockingReceive(); //RECEIVES SmartBOT REQUEST
		this.player=this.sueca.getPlayer(getPlayer(msg.getSender().getName()));
		Carta carta = retornaTrunfo();

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

	private Carta retornaTrunfo() {

		int naipeOfCurrRound = ((CortaAGENT)this.myAgent).returnNaipeOfCurrRound();
		ArrayList<Carta> trunfos= new 	ArrayList<Carta>();
		boolean flag=false;
		for(int i=0;i<this.player.getPlayerHand().getMao().size();i++) {
			if(naipeOfCurrRound==this.player.getPlayerHand().getMao().get(i).getNaipe()) {
				flag=true;
			}
			if(this.player.getPlayerHand().getMao().get(i).getNaipe()==this.sueca.getTrunfo().getNaipe()) {
				trunfos.add(this.player.getPlayerHand().getMao().get(i));
			}
		}


		if(!trunfos.isEmpty()) {
			Random r = new Random();
			int Low = 0;
			int High =trunfos.size();
			int Result = r.nextInt(High-Low) + Low;
			return trunfos.get(Result);
		}

		Random r = new Random();
		int Low = 0;
		int High =this.player.getPlayerHand().getMao().size();
		int Result = r.nextInt(High-Low) + Low;
		return this.player.getPlayerHand().getMao().get(Result);

	}

}
