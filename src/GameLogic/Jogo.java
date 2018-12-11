package GameLogic;
import java.util.ArrayList;
import java.util.List;
import jade.core.Agent;
import jade.util.leap.Serializable;
import javafx.util.Pair;
public class Jogo {
	private Mao player1Mao,player2Mao,player3Mao,player4Mao;
	private Jogador player1,player2,player3,player4;
	private ArrayList<Carta> initialDeck;
	private ArrayList<Carta> player3InitialDeck;
	public ArrayList<Carta> getInitialDeck() {
		return initialDeck;
	}
	public void setInitialDeck(ArrayList<Carta> initialDeck) {
		this.initialDeck = initialDeck;
	}
	/*
		 * (non-Javadoc)
		 * @see jade.core.Agent#setup()
		 * The setup() method is intended to include agent initializations. 
		 * Behaviours can be added at any time: when an agent starts (in the setup()
method) or from within other behaviours. 
		 */
	private ArrayList<Round> matchRounds;
	private logic gameLogic;
	private ArrayList<Carta> player1InitialDeck;
	public ArrayList<Carta> getPlayer1InitialDeck() {
		return player1InitialDeck;
	}
	public void setPlayer1InitialDeck(ArrayList<Carta> player1InitialDeck) {
		this.player1InitialDeck = player1InitialDeck;
	}
	public ArrayList<Carta> getPlayer2InitialDeck() {
		return player2InitialDeck;
	}
	public void setPlayer2InitialDeck(ArrayList<Carta> player2InitialDeck) {
		this.player2InitialDeck = player2InitialDeck;
	}
	public ArrayList<Carta> getPlayer4InitialDeck() {
		return player4InitialDeck;
	}
	public void setPlayer4InitialDeck(ArrayList<Carta> player4InitialDeck) {
		this.player4InitialDeck = player4InitialDeck;
	}
	private ArrayList<Carta> player2InitialDeck;
	private ArrayList<Carta> player4InitialDeck;
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
		this.initialDeck=new ArrayList<Carta>(baralho.getBaralho());
		baralho.shuffle();
		setTrunfo(baralho.getBaralho().get(0));
		List<Carta> cartas1 = baralho.dar(4);
		List<Carta> cartas2 = baralho.dar(3);
		List<Carta> cartas3  = baralho.dar(2);
		List<Carta> cartas4 = baralho.dar(1);
		player1 = new Jogador(cartas1);
		player2 = new Jogador(cartas2);
		player3 = new Jogador(cartas3);
		player4 = new Jogador(cartas4);
		player1InitialDeck = new ArrayList<Carta>(cartas1);
		player2InitialDeck = new ArrayList<Carta>(cartas2);
		player3InitialDeck=  new ArrayList<Carta>(cartas3);
		player4InitialDeck= new ArrayList<Carta>(cartas4);
		matchRounds= new ArrayList<Round>();
		this.stratsUsedByPlayer3= new ArrayList<String>();

		
	}
public ArrayList<Carta> getPlayer3InitialDeck() {
		return player3InitialDeck;
	}
	public void setPlayer3InitialDeck(ArrayList<Carta> player3InitialDeck) {
		this.player3InitialDeck = player3InitialDeck;
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
		return 0;
	}
	private Carta trunfo;
	private ArrayList<String> stratsUsedByPlayer3;

	public ArrayList<String> getStratsUsedByPlayer3() {
		return stratsUsedByPlayer3;
	}
	public void insertStratsUsedByPlayer3(String strat) {
		this.stratsUsedByPlayer3.add(strat);
	}
	public void setStratsUsedByPlayer3(ArrayList<String> stratsUsedByPlayer3) {
		this.stratsUsedByPlayer3 = stratsUsedByPlayer3;
	}
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
	public void addStrategy(String strategyUsed) {
		this.stratsUsedByPlayer3.add(strategyUsed);
		
	}



	

}
