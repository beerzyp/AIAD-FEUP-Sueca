package Behaviours;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import Agents.GameAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import GameLogic.Round;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import javafx.util.Pair;

public class CreateDataSetBehaviour extends OneShotBehaviour {
	private Jogo sueca;
	private ArrayList<Round> rondas;
	public boolean percepton;
	int teamAPoints=0;
	ArrayList<Carta> player1Hand,player2Hand,player3Hand,player4Hand;
	int teamBPoints=0;
	ArrayList<String> strats;
	public CreateDataSetBehaviour(Jogo suecaGame,boolean flag, ArrayList<String> stratBotThatPlayerPos3) {
		this.sueca=suecaGame;
		percepton=flag;
		calculateInitialScore ();
		player1Hand= new ArrayList<Carta>(this.sueca.getPlayer1InitialDeck());
		player2Hand= new ArrayList<Carta>(this.sueca.getPlayer2InitialDeck());
		player3Hand= new ArrayList<Carta>(this.sueca.getPlayer3InitialDeck());
		player4Hand= new ArrayList<Carta>(this.sueca.getPlayer4InitialDeck());
		this.strats=stratBotThatPlayerPos3;
	}

	public  void calculateInitialScore (){
		teamAPoints=0;
		teamBPoints=0;
		for(int i=0;i<this.sueca.getPlayer1().getPlayerHand().getMao().size();i++) {
			teamAPoints+=this.sueca.getPlayer1().getPlayerHand().getMao().get(i).getPonto();
		}
		for(int j=0;j<this.sueca.getPlayer3().getPlayerHand().getMao().size();j++) {
			teamAPoints+=this.sueca.getPlayer3().getPlayerHand().getMao().get(j).getPonto();
		}
		for(int i=0;i<this.sueca.getPlayer2().getPlayerHand().getMao().size();i++) {
			teamBPoints+=this.sueca.getPlayer2().getPlayerHand().getMao().get(i).getPonto();
		}
		for(int j=0;j<this.sueca.getPlayer4().getPlayerHand().getMao().size();j++) {
			teamBPoints+=this.sueca.getPlayer4().getPlayerHand().getMao().get(j).getPonto();
		}
		GameAGENT.setInitialteamPointsA(teamAPoints);
		GameAGENT.setInitialteamPointsB(teamBPoints);
		System.out.println("Team A initial Points:"+teamAPoints + "\nTeam B initial Points:"+teamBPoints+"\n");
	}
	public ArrayList<Carta> getPlayerN(int n){
		switch(n) {
		case 1:
			return player1Hand;
		case 2:
			return player2Hand;
		case 3:
			return player3Hand;
		case 4:
			return player4Hand;
		default:
			return null;

		}
	}
	@Override
	public void action() {
		rondas = new ArrayList<Round>(((GameAGENT)this.myAgent).getRondas());
		PrintWriter pw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("dataSet.csv",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw= new BufferedWriter(fw);
		pw = new PrintWriter(bw);
		StringBuilder sb = new StringBuilder();
		if(this.percepton) {
			sb.append("teamAiPoints"); //jogNum
			sb.append(',');
			sb.append("teamAfPoints"); //jog.cardValue
			sb.append(',');
			sb.append("teamBiPoints"); //jogNum
			sb.append(',');
			sb.append("teamBfPoints"); //jog.cardValue
			sb.append('\n');

			sb.append(this.teamAPoints); //jogNum
			sb.append(',');
			sb.append(Integer.toString(GameAGENT.getTeamPointsA())); //final Score PLay 1 and 3
			sb.append(',');
			sb.append(this.teamBPoints); //jogNum
			sb.append(',');
			sb.append(Integer.toString(GameAGENT.getTeamPointsB())); //ply 2 and 4 final score
			sb.append('\n');

		}
		else {
			sb.append("numOfSpadesinHand");
			sb.append(',');
			sb.append("numOfCopasInHand");
			sb.append(',');
			sb.append("numOfPausInHand");
			sb.append(',');
			sb.append("numOfOurosInHand");
			sb.append(',');
			for(int i=0;i<4;i++) {
				sb.append("playerNum" + (i+1)); //jogNum
				sb.append(',');
				sb.append("card" + (i+1)); //jog.cardValue
				sb.append(',');
				sb.append("suit" + (i+1));//jog.Suit
				sb.append(',');
			}
			sb.append("roundWinningPlayer");//jog.Suit
			sb.append(',');
			sb.append("handNumber");//jog.Suit
			sb.append(',');
			sb.append("trunfo");//jog.Suit
			sb.append(',');
			sb.append("valueOfSpades");
			sb.append(',');
			sb.append("valueOfCopas");
			sb.append(',');
			sb.append("valueOfPaus");
			sb.append(',');
			sb.append("valueOfOuros");
			sb.append(',');
			sb.append("strategy");
			sb.append("\n");//jog.Suit
			int playerId3rdPosition=3;
			for(int i=0;i<this.rondas.size();i++) {
				StringBuilder sp=writeVectorToCsv(this.sueca.getTrunfo().convertToDataSetArray(this.getPlayerN(playerId3rdPosition)));	    
				sb.append(sp);
				sb.append(',');
				for(int j=0;j<this.rondas.get(i).returnTableHand().size();j++) {//maos da ronda

					Carta c1 =this.rondas.get(i).returnTableHand().get(j).getKey();
					int player = this.rondas.get(i).returnTableHand().get(j).getValue();
					if(j==2) {//PLAYER 3 DATA SET EVALs
						playerId3rdPosition=player;
						
					}
					if(player==1) {
						player1Hand.remove(c1);
					}
					else if (player==2) {
						player2Hand.remove(c1);
					}
					else if (player==3) {
						player3Hand.remove(c1);		
					}
					else if (player==4) {
						player4Hand.remove(c1);
					}

					sb.append(player); //jogNum
					sb.append(',');
					sb.append(c1.getDataSetCardValue()); //jog.cardValue
					sb.append(',');
					sb.append(c1.getNaipe());//jog.Suit
					sb.append(',');
				}
				int lastRoundWinner=this.sueca.getGameLogic().winner(this.rondas.get(i), sueca);
				sb.append(lastRoundWinner);//WinningHandPlayer
				sb.append(',');
				sb.append(i+1);//Hand number
				sb.append(',');
				sb.append(this.sueca.getTrunfo().getNaipe()); //trunfo jogo
				sb.append(',');
				StringBuilder sr= writeNumOfNaipeToCsv(valueOfNaipe(this.getPlayerN(playerId3rdPosition)));	    
				sb.append(sr);
				sb.append(',');
				sb.append(this.sueca.getStratsUsedByPlayer3().get(i));
				sb.append('\n');
			}
		}
		pw.write(sb.toString());
		pw.close();

	}


	// Arrays.sort(arr); 
	private ArrayList<Pair<Integer,Integer>> valueOfNaipe(ArrayList<Carta> convertToDataSetArray) {
		ArrayList<Integer> spades = new ArrayList<Integer>(),copas = new ArrayList<Integer>(),paus = new ArrayList<Integer>(),ouros = new ArrayList<Integer>();
		for(int i=0;i<convertToDataSetArray.size();i++) {
			if(convertToDataSetArray.get(i).getNaipe()==0) {
				spades.add(convertToDataSetArray.get(i).getDataSetCardValue());
			}
			else if(convertToDataSetArray.get(i).getNaipe()==1) {
				copas.add(convertToDataSetArray.get(i).getDataSetCardValue());

			}
			else if(convertToDataSetArray.get(i).getNaipe()==2) {
				paus.add(convertToDataSetArray.get(i).getDataSetCardValue());
			}
			else if(convertToDataSetArray.get(i).getNaipe()==3) {
				ouros.add(convertToDataSetArray.get(i).getDataSetCardValue());
			}
			else return null;

		}
		ArrayList<Pair<Integer,Integer>> array = new ArrayList<Pair<Integer,Integer>> ();
		int maxSpade,maxCopas,maxPaus,maxOuros;
		if(!spades.isEmpty())
			maxSpade=Collections.max(spades);
		else maxSpade=0;
		if(!copas.isEmpty())
			maxCopas=Collections.max(copas);
		else maxCopas=0;
		if(!paus.isEmpty())
			maxPaus=Collections.max(paus);
		else maxPaus=0;
		if(!ouros.isEmpty())
			maxOuros=Collections.max(ouros);
		else maxOuros=0;
		array.add(new Pair<Integer,Integer>(0,maxSpade));
	
		array.add(new Pair<Integer,Integer>(1,maxCopas));
		array.add(new Pair<Integer,Integer>(2,maxPaus));
		array.add(new Pair<Integer,Integer>(3,maxOuros));
		return array;
	}
	private StringBuilder writeNumOfNaipeToCsv(ArrayList<Pair<Integer,Integer>> array) {
		StringBuilder sp=new StringBuilder();
		sp.append(array.get(0).getValue());
		sp.append(',');
		sp.append(array.get(1).getValue());
		sp.append(',');
		sp.append(array.get(2).getValue());
		sp.append(',');
		sp.append(array.get(3).getValue());
			
		
		return sp;
		
	}

	private StringBuilder writeVectorToCsv(ArrayList<Pair<Integer, Integer>> convertToDataSetArray) {
		StringBuilder sp = new StringBuilder();
		/*
		 * 		case ESPADAS: return 0; 
		case COPAS: return 1; 
		case PAUS: return 2; 
		case OUROS: return 3; 
		 */
		int numOfSpades = 0,Copas = 0,Paus = 0,Ouros = 0;
		for(int i=0;i<convertToDataSetArray.size();i++) {
			if(convertToDataSetArray.get(i).getValue()==0) {
				numOfSpades++;
			}
			if(convertToDataSetArray.get(i).getValue()==1) {
				Copas++;
			}
			if(convertToDataSetArray.get(i).getValue()==2) {
				Paus++;
			}
			if(convertToDataSetArray.get(i).getValue()==3) {
				Ouros++;
			}

		}
		sp.append(numOfSpades);
		sp.append(',');
		sp.append(Copas);
		sp.append(',');
		sp.append(Paus);
		sp.append(',');
		sp.append(Ouros);
		return sp;

	}

	/*
	 * 1) S1 "Suit of card #1" 
Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs} 

2) C1 "Rank of card #1" 
Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King) 

3) S2 "Suit of card #2" 
Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs} 

4) C2 "Rank of card #2" 
Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King) 

5) S3 "Suit of card #3" 
Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs} 

6) C3 "Rank of card #3" 
Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King) 

7) S4 "Suit of card #4" 
Ordinal (1-4) representing {Hearts, Spades, Diamonds, Clubs} 

8) C4 "Rank of card #4" 
Numerical (1-13) representing (Ace, 2, 3, ... , Queen, King) 
	 */




}