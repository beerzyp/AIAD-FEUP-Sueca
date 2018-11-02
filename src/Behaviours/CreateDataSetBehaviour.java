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
	int teamBPoints=0;
	public CreateDataSetBehaviour(Jogo suecaGame,boolean flag) {
		this.sueca=suecaGame;
		percepton=flag;
		calculateInitialScore ();
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
	        for(int i=0;i<4;i++) {
		        sb.append("playerNum"); //jogNum
		        sb.append(',');
		        sb.append("card"); //jog.cardValue
		        sb.append(',');
		        sb.append("suit");//jog.Suit
		        sb.append(',');
	        }
	        sb.append("roundWinningPlayer");//jog.Suit
	        sb.append(',');
	        sb.append("handNumber");//jog.Suit
	        sb.append(',');
	        sb.append("trunfo");//jog.Suit
	        sb.append("\n");//jog.Suit
			for(int i=0;i<this.rondas.size();i++) {
				
				for(int j=0;j<this.rondas.get(i).returnTableHand().size();j++) {
					
					Carta c1 =this.rondas.get(i).returnTableHand().get(j).getKey();
					int player = this.rondas.get(i).returnTableHand().get(j).getValue();
			        sb.append(player); //jogNum
			        sb.append(',');
			        sb.append(c1.getDataSetCardValue()); //jog.cardValue
			        sb.append(',');
			        sb.append(c1.getNaipe());//jog.Suit
			        sb.append(',');
				}
				int lastRoundWinner=this.sueca.getGameLogic().winner(this.rondas.get(i), sueca);
				int realWinner=this.rondas.get(i).returnTableHand().get(lastRoundWinner-1).getValue();
		        sb.append(realWinner);//WinningHandPlayer
		        sb.append(',');
		        sb.append(i+1);//Hand number
		        sb.append(',');
				sb.append(this.sueca.getTrunfo().getNaipe()); //trunfo jogo
			    sb.append('\n');
			}
		}
        pw.write(sb.toString());
        pw.close();
	
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
