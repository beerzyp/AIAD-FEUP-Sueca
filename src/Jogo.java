import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.util.leap.Serializable;
import javafx.util.Pair;
public class Jogo {
	private Mao player1Mao,player2Mao,player3Mao,player4Mao;
	private Jogador player1,player2,player3,player4;
/*
		 * (non-Javadoc)
		 * @see jade.core.Agent#setup()
		 * The setup() method is intended to include agent initializations. 
		 * Behaviours can be added at any time: when an agent starts (in the setup()
method) or from within other behaviours. 
		 */
	private ArrayList<Round> matchRounds;
	private logic gameLogic;
	public logic getGameLogic() {
		return gameLogic;
	}
	public void setGameLogic(logic gameLogic) {
		this.gameLogic = gameLogic;
	}
	public void insertRound(Round round) {
		this.matchRounds.add(round);
	}
	public ArrayList<Round> getMatchRounds() {
		return matchRounds;
	}
	public void setMatchRounds(ArrayList<Round> matchRounds) {
		this.matchRounds = matchRounds;
	}
	
	public Jogador getPlayer(int n) {
		switch(n) {
		case 1:
			return this.getPlayer1();
		case 2:
			return this.getPlayer2();
		case 3:
			return this.getPlayer3();
		case 4:
			return this.getPlayer4();
		
		default:
			return null;
		}
	}
	
		
	public Jogo() {
		//System.out.println(this.getAID().getName());
		int jogadores = 4;
		List<List> maos = new ArrayList<List>(4);
		gameLogic= new logic();
		Baralho baralho = new Baralho();
		baralho.shuffle();
		setTrunfo(baralho.getBaralho().get(0));
		List<Carta> cartas1 = baralho.dar(4);
		List<Carta> cartas2 = baralho.dar(3);
		List<Carta> cartas3 = baralho.dar(2);
		List<Carta> cartas4 = baralho.dar(1);
		player1 = new Jogador(cartas1);
		player2 = new Jogador(cartas2);
		player3 = new Jogador(cartas3);
		player4 = new Jogador(cartas4);
	
		System.out.println("\nJogador 1:" + player1.getPlayerHand().getMao().size());
		for(int i=0;i<cartas1.size();i++) {
			System.out.println(cartas1.get(i).toString());
			
		}
		System.out.println("\nJogador 2:" +  player2.getPlayerHand().getMao().size());
		for(int i=0;i<cartas2.size();i++) {
			System.out.println(cartas2.get(i).toString());
			
		}
		
		System.out.println("\nJogador 3:" +  player3.getPlayerHand().getMao().size());
		for(int i=0;i<cartas3.size();i++) {
			System.out.println(cartas3.get(i).toString());
			
		}
		System.out.println("\nJogador 4:" +  player4.getPlayerHand().getMao().size());
		for(int i=0;i<cartas4.size();i++) {
			System.out.println(cartas4.get(i).toString());
			
		}
	
		
		matchRounds= new ArrayList<Round>();

		
	}
//	 if(	this.sueca.getGameLogic().validPlay(attempt, this.sueca.getPlayer1(), round)){
//			this.sueca.getMao1().jogaCarta(attempt);
//			//System.out.println(this.sueca.getPlayer1().getJogNum());
//			round.insertPlay(new Pair<Carta, Integer>(attempt,this.sueca.getPlayer1().getJogNum()));
//			System.out.println("size hand player1: " +this.sueca.getMao1().getMao().size());
//			incrementCounter();
//		}
//	
	public boolean makeMove(Carta attempt,int player,Round round) {
		if(this.gameLogic.validPlay(attempt, this.getPlayer(player), round)) {
			this.getPlayer(this.getPlayer(player).getJogNum()).fazJogada(attempt);
			round.insertPlay(new Pair<Carta, Integer>(attempt,this.getPlayer(player).getJogNum()));
			return true;
		}
		return false;
	}
	public int callGameLogicRound(Round round,Mao mao1,Mao mao2,Mao mao3,Mao mao4) {
	
//			for(int i=0;i<2;i++) {
//			
//			ArrayList<Pair<Carta,Integer>> cardsOfRoundByOrder = new ArrayList<Pair<Carta,Integer>>();
//			Round n = new Round(this,cardsOfRoundByOrder);
//			if(i==0) {
//				Carta attemptPlayer1 = mao1.getCartaAt(0);
//				Carta attemptPlayer2 = mao2.getCartaAt(0);
//				Carta attemptPlayer3 = mao3.getCartaAt(0);
//				Carta attemptPlayer4 = mao4.getCartaAt(0);
//				
//				//TODO: 
//				/*
//				 * INSTEAD OF IF->while, must force player to player a card, iterating through it's deck
//				 */
//				
//				if(gameLogic.validPlay(attemptPlayer1, player1,n )) {
//					mao1.jogaCarta(attemptPlayer1);
//					n.insertPlay(new Pair<Carta, Integer>(attemptPlayer1,player1.getJogNum()));//inserts play in round and passes to next player
//				}
//				if(gameLogic.validPlay(attemptPlayer2, player2,n )) {
//					mao1.jogaCarta(attemptPlayer2);
//					n.insertPlay(new Pair<Carta, Integer>(attemptPlayer2,player2.getJogNum()));//inserts play in round and passes to next player
//				}
//				if(gameLogic.validPlay(attemptPlayer3, player3,n )) {
//					mao1.jogaCarta(attemptPlayer3);
//					n.insertPlay(new Pair<Carta, Integer>(attemptPlayer3,player3.getJogNum()));//inserts play in round and passes to next player
//				}
//				if(gameLogic.validPlay(attemptPlayer4, player4,n )) {
//					mao1.jogaCarta(attemptPlayer4);
//					n.insertPlay(new Pair<Carta, Integer>(attemptPlayer4,player4.getJogNum()));//inserts play in round and passes to next player
//				}
//			}
//			else {
//			//TODO: 
//			int winnerRound = gameLogic.winner(matchRounds.get(i-1), this);
//			n.setJogAtual(winnerRound);
//			System.out.println(winnerRound + "<- winner , jogAtual ->" + n.jogAtual);
////				
//				
//	//			String startingPlayer= "player" + Integer.toString(winnerRound);
//			}
//			n.printRonda();
//			matchRounds.add(n);
//
//		}~
		return 0;
	}
	private Carta trunfo;

	public Carta getTrunfo() {
		return trunfo;
	}

	public void setTrunfo(Carta trunfo) {
		this.trunfo = trunfo;
	}

	
	
	public Jogador getPlayer1() {
		return player1;
	}
	public Jogador getPlayer2() {
		return player2;
	}
	public Jogador getPlayer3() {
		return player3;
	}
	public Jogador getPlayer4() {
		return player4;
	}



	

}
