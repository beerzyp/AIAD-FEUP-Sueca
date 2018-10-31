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

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class CreateDataSetBehaviour extends OneShotBehaviour {
	private Jogo sueca;
	private ArrayList<Round> rondas;
	public CreateDataSetBehaviour(Jogo suecaGame) {
		this.sueca=suecaGame;
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
