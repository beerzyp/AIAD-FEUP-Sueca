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
	
	private static int roundNumber=0;
	private static int counter;
	
	public  static void incrementCounter() {
		if(counter>=4) {
			counter=1;
		}
		else counter++;
	}
	public static void setCounter(int i) 
	{
		counter=i;
	}
	
	@Override 
	public void action() {
		counter=1;
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(sueca, jogadas);
		this.sueca.insertRound(round);
		while(roundNumber==0){
			if(counter>4)
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
					incrementCounter();
				}
				break;
			case 2:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer2(), round)){
					this.sueca.getMao2().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer2().getJogNum()));
					System.out.println("size hand player2:  " +this.sueca.getMao2().getMao().size());
					incrementCounter();
				}
				break;
			case 3:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer3(), round)){
					this.sueca.getMao3().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer3().getJogNum()));
					System.out.println("size hand player3:  " +this.sueca.getMao3().getMao().size());
					incrementCounter();
				}
				break;
			case 4:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer4(), round)){
					this.sueca.getMao4().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer4().getJogNum()));
					System.out.println("size hand player4:  " +this.sueca.getMao4().getMao().size());
					incrementCounter();
					roundNumber++;
				}
				break;
			default:
				{
					System.out.println("CRITICAL ERROR: counter >4");
					return;
				}
			}	
		}
		
		Round round1To9=new Round(sueca, jogadas);
		this.sueca.insertRound(round1To9);
		int lastRoundWinner=this.sueca.getGameLogic().winner(this.sueca.getMatchRounds().get(roundNumber-1), this.sueca);
		System.out.println("\nLast Round Winner: "+ (lastRoundWinner) + "    ");
		this.sueca.getMatchRounds().get(roundNumber-1).printRonda();
		System.out.println("----------NEW ROUND------------\n");
		setCounter(lastRoundWinner);
		String botToPlay = "randomBotAgent"+Integer.toString(lastRoundWinner);
		while(roundNumber<2) {
			
			//SEND REQUEST
			if(round1To9.getNumPlays() >= 1)
				botToPlay = "randomBotAgent"+Integer.toString(counter);
		
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
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer1(), round1To9)){
					this.sueca.getMao1().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer1().getJogNum());
					round1To9.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer1().getJogNum()));
					System.out.println("size hand player1: " +this.sueca.getPlayer1().getPlayerHand().getMao().size());
					incrementCounter();
				}
				break;
			case 2:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer2(), round1To9)){
					this.sueca.getMao2().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round1To9.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer2().getJogNum()));
					System.out.println("size hand player2:  " +this.sueca.getPlayer2().getPlayerHand().getMao().size());
					incrementCounter();
				}
				break;
			case 3:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer3(), round1To9)){
					this.sueca.getMao3().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round1To9.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer3().getJogNum()));
					System.out.println("size hand player3:  " +this.sueca.getPlayer3().getPlayerHand().getMao().size());
					incrementCounter();
				}
				break;
			case 4:
				if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer4(), round1To9)){
					this.sueca.getMao4().jogaCarta(attempt);
					//System.out.println(this.sueca.getPlayer2().getJogNum());
					round1To9.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer4().getJogNum()));
					System.out.println("size hand player4:  " +this.sueca.getPlayer4().getPlayerHand().getMao().size());
					incrementCounter();
					roundNumber++;
				}
				break;
			default:
				{
					System.out.println("CRITICAL ERROR: counter >4");
					return;
				}
			}	
			

		}
		
		this.done();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
