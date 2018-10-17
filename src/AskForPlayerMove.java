import jade.core.behaviours.Behaviour;

import java.io.IOException;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import javafx.util.Pair;

public class AskForPlayerMove extends Behaviour {
	//AgentGame
	private Carta carta;
	private Jogo sueca;
	public AskForPlayerMove(Jogo Sueca) {
		sueca=Sueca;
		this.carta=sueca.getMao1().getCartaAt(0);
	}
	
	private int roundNumber=0;
	
	@Override 
	public void action() {
		int counter=1;
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(sueca, jogadas);
		this.sueca.insertRound(round);
		while(roundNumber==0){
			if(counter>2)
				break;
			String botToPlay = "randomBotAgent"+counter;
			//SEND REQUEST
			ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
			request.addReceiver(new AID(botToPlay, AID.ISLOCALNAME));
			request.setLanguage("Portugues");
			request.setOntology("Sueca-Ronda");
			request.setContent("Your turn");
			this.myAgent.send(request);
			
			//RECEIVE INFORM
			final ACLMessage inform = this.myAgent.blockingReceive();
			String carta= inform.getContent();
			System.out.println(carta + "\n");
			Carta attempt;
				
			attempt= new Carta(this.carta.convertStringToNome(carta), this.carta.convertStringToNaipe(carta));
	
			switch(counter) {
			case 1:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer1(), round)){
					this.sueca.getMao1().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer1().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer1().getJogNum()));
					System.out.println("size hand player1: " +this.sueca.getMao1().getMao().size());
					counter++;
				}
				break;
			case 2:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer2(), round)){
					this.sueca.getMao2().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer2().getJogNum()));
					System.out.println("size hand player2:  " +this.sueca.getMao2().getMao().size());
					counter++;
				}
				break;
			case 3:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer3(), round)){
					this.sueca.getMao3().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer3().getJogNum()));
					System.out.println("size hand player3:  " +this.sueca.getMao3().getMao().size());
					counter++;
				}
				break;
			case 4:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer4(), round)){
					this.sueca.getMao4().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer4().getJogNum()));
					System.out.println("size hand player4:  " +this.sueca.getMao4().getMao().size());
					counter++;
				}
				break;
			default:
				{
					System.out.println("CRITICAL ERROR: counter >4");
					return;
				}
			}	
		}
		while(roundNumber<=10) {
			break;
			
		}
		
		this.done();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
