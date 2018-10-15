import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.util.leap.Serializable;

public class Jogo implements Serializable{
	private Mao mao1,mao2,mao3,mao4;
		/*
		 * (non-Javadoc)
		 * @see jade.core.Agent#setup()
		 * The setup() method is intended to include agent initializations. 
		 * Behaviours can be added at any time: when an agent starts (in the setup()
method) or from within other behaviours. 
		 */
	public Jogo() {
		//System.out.println(this.getAID().getName());
		int jogadores = 4;
		List<List> maos = new ArrayList<List>(4);
		logic gameLogic= new logic();
		Baralho baralho = new Baralho();
		baralho.shuffle();
		setTrunfo(baralho.getBaralho().get(0));
		List<Carta> cartas1 = baralho.dar(4);
		List<Carta> cartas2 = baralho.dar(3);
		List<Carta> cartas3 = baralho.dar(2);
		List<Carta> cartas4 = baralho.dar(1);
		mao1= new Mao(cartas1);
		Jogador player1 = new Jogador(mao1);

		mao2= new Mao(cartas2);
		Jogador player2 = new Jogador(mao2);

		mao3= new Mao(cartas3);
		Jogador player3 = new Jogador(mao3);
		//System.out.println("\nJogador 3:" + cartas3.size());

		mao4= new Mao(cartas4);
		Jogador player4 = new Jogador(mao4);
		
		//PRINT HAND PLAYER 1
//		System.out.println("\nJogador 1:" + mao1.getMao().size());
//		for(int i=0;i<cartas1.size();i++) {
//			System.out.println(cartas1.get(i).toString());
//			
//		}
		
		ArrayList<Round> matchRounds= new ArrayList<Round>();

		
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

	
	public Mao getMao1() {
		return mao1;
	}
	public void setMao1(Mao mao1) {
		this.mao1 = mao1;
	}
	public Mao getMao2() {
		return mao2;
	}
	public void setMao2(Mao mao2) {
		this.mao2 = mao2;
	}
	public Mao getMao3() {
		return mao3;
	}
	public void setMao3(Mao mao3) {
		this.mao3 = mao3;
	}
	public Mao getMao4() {
		return mao4;
	}
	public void setMao4(Mao mao4) {
		this.mao4 = mao4;
	}

	

}
