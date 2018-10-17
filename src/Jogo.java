import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.util.leap.Serializable;

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
		player1Mao= new Mao(cartas1);
		player1 = new Jogador(player1Mao);

		player2Mao= new Mao(cartas2);
		player2 = new Jogador(player2Mao);

		player3Mao = new Mao(cartas3);
		player3 = new Jogador(player3Mao);
		//System.out.println("\nJogador 3:" + cartas3.size());

		player4Mao= new Mao(cartas4);
		player4 = new Jogador(player4Mao);
	
		System.out.println("\nJogador 1:" + player1Mao.getMao().size());
		for(int i=0;i<cartas1.size();i++) {
			System.out.println(cartas1.get(i).toString());
			
		}
		System.out.println("\nJogador 2:" + player2Mao.getMao().size());
		for(int i=0;i<cartas2.size();i++) {
			System.out.println(cartas2.get(i).toString());
			
		}
		
		System.out.println("\nJogador 3:" + player3Mao.getMao().size());
		for(int i=0;i<cartas3.size();i++) {
			System.out.println(cartas3.get(i).toString());
			
		}
		System.out.println("\nJogador 4:" + player4Mao.getMao().size());
		for(int i=0;i<cartas4.size();i++) {
			System.out.println(cartas4.get(i).toString());
			
		}
	
		
		matchRounds= new ArrayList<Round>();

		
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
		return player1Mao;
	}

	public Mao getMao2() {
		return player2Mao;
	}

	public Mao getMao3() {
		return player3Mao;
	}

	public Mao getMao4() {
		return player4Mao;
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
