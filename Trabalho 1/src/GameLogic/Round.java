package GameLogic;

import java.util.ArrayList;

import javafx.util.Pair;


public class Round {
	public static int jogAtual=1;
    
    private Pair<Carta, Integer> par1, par2, par3, par4;
    private int pontosA, pontosB;
    private Jogo game;
    //Array com par Carta-Jogador pela ordem que foi jogado na ronda
    private ArrayList<Pair<Carta,Integer>> cardsPlayerByOrder;
    public Round(Jogo game,ArrayList<Pair<Carta,Integer>> playerCardInOrder) {
    	this.game=game;
    	this.cardsPlayerByOrder=playerCardInOrder;
    	cardsPlayerByOrder=new ArrayList<Pair<Carta,Integer>>();
    }
    public Round(Jogo game) {
    	this.game=game;
		// TODO Auto-generated constructor stub
    	cardsPlayerByOrder=new ArrayList<Pair<Carta,Integer>>();
	};

	public static void setJogAtual(int i) {
    	jogAtual=i;
    }
    public boolean temTrunfoNaMesa() {
        for(int i=0;i<this.cardsPlayerByOrder.size();i++) {
        	if(this.cardsPlayerByOrder.get(i).getKey().getNaipe() == game.getTrunfo().getNaipe()) {
        		return true;
        	}
        }
        
        return false;
    }
    
    public int getNumPlays() {
    	return this.cardsPlayerByOrder.size();
    }
    
    
    public boolean insertPlay(Pair<Carta,Integer> newPlay) {
    	return this.cardsPlayerByOrder.add(newPlay);
    }
    private void passToNextPlayer() {
		if(jogAtual==4) {
			jogAtual=1;
		}
		else jogAtual++;
	}

	/**
     * Devolve true se a carta jogada for maior do que a anterior.
     *
     * @param carta
     * @param cartaAdversaria
     */
    public boolean compareCards(Carta carta, Carta cartaAdversaria) {

        if (!temTrunfoNaMesa()) {
            if (carta.getNaipe() == cartaAdversaria.getNaipe() && carta.getPonto() > cartaAdversaria.getPonto()) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Pair<Carta,Integer>> returnTableHand(){
    	return this.cardsPlayerByOrder;
    }
    
    public Carta RetornaCartasNaMesa(int jogador) {
        return returnTableHand().get(jogador-2).getKey();
    }
    
    
    public void resetRound() {

        int winner = 0;

        if (par1.getKey() != null && par2.getKey() != null
                && par3.getKey() != null && par4.getKey() != null) {

            System.out.println("Winner: player" + winner);

            if (winner == 1 || winner == 3) {

                pontosA += par1.getKey().getPonto() + par3.getKey().getPonto();

            } else if (winner == 2 || winner == 4) {
                pontosB += par1.getKey().getPonto() + par3.getKey().getPonto();

            }

            if (pontosA > pontosB) {
                System.out.println("Team A wins");
            } else {
                System.out.println("Team B wins");
            }

        }

    }

	public void printRonda() {
		for(int i=0;i<this.cardsPlayerByOrder.size();i++) {
			System.out.println("Jogador Nº" + this.cardsPlayerByOrder.get(i).getValue() + ":"+ this.cardsPlayerByOrder.get(i).getKey().toString());
		}
	}

    

 
}
