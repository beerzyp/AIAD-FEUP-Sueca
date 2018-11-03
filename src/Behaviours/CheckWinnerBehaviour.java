package Behaviours;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import Agents.GameAGENT;
import GameLogic.Jogo;
import GameLogic.Round;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CheckWinnerBehaviour extends OneShotBehaviour {
	Jogo sueca;
	Round ronda;
	public CheckWinnerBehaviour(Jogo sueca,Round ronda) {
		this.sueca=sueca;
		this.ronda=ronda;
	}
	private static int playerToMove=1;
	public  static void incrementCounter() {

		if(playerToMove>=4) {
			playerToMove=1;
		}
		else playerToMove++;
	}
	@Override
	public void action() {
		int lastRoundWinner=this.sueca.getGameLogic().winner(ronda, sueca);
		int realWinner=ronda.returnTableHand().get(lastRoundWinner-1).getValue();
		((GameAGENT)this.myAgent).setWinner(realWinner);
		((GameAGENT)this.myAgent).insertRonda(ronda);
		System.out.println("VENCEDOR RONDA: player"+ realWinner+"\n");
		String botToPlay="SmartBotAgent"+playerToMove;
		
//		//SEND REQUEST TO SMART BOT WITH BOARD STATE
//		ACLMessage request= new ACLMessage(ACLMessage.REQUEST);
//		request.addReceiver(new AID("SmartBotAgent", AID.ISLOCALNAME));
//		request.setLanguage("Portugues");
//		request.setOntology("Sueca-Ronda-Final");
//		String s1="";
//		for(int i=0;i<4;i++) {
//			s1+=ronda.returnTableHand().get(i).getKey().toString()+",";
//		}
//		byte[] cardsSequenceSplitByComma=s1.getBytes(StandardCharsets.UTF_8);
//		request.setByteSequenceContent(cardsSequenceSplitByComma);
//		this.myAgent.send(request);
		
		
		System.out.println("\nJogador 1:" + sueca.getPlayer1().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer1().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer1().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 2:" + sueca.getPlayer2().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer2().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer2().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 3:" + sueca.getPlayer3().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer3().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer3().getPlayerHand().getMao().get(i).toString());
			
		}
		System.out.println("\nJogador 4:" + sueca.getPlayer4().getPlayerHand().getMao().size());
		for(int i=0;i<sueca.getPlayer4().getPlayerHand().getMao().size();i++) {
			System.out.println(sueca.getPlayer4().getPlayerHand().getMao().get(i).toString());
			
		}
		this.incrementCounter();
	}

}
