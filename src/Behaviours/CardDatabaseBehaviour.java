package Behaviours;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import Agents.SmartBotAGENT;
import GameLogic.Carta;
import GameLogic.Jogo;
import GameLogic.Mao;
import GameLogic.Round;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.util.Pair;

public class CardDatabaseBehaviour extends OneShotBehaviour {
	public ArrayList<Carta> cartasRestantes;
	public ArrayList<Carta> cartasJogador;
	ArrayList<Carta> cartasJogadasNasRondas;
	public ArrayList<Round> rounds;
	Jogo sueca;
	Mao playerHand;
	public CardDatabaseBehaviour(Jogo sueca,Mao playerHand) {
		this.sueca=sueca;
		this.playerHand=playerHand;
		cartasJogador = new ArrayList<Carta>(playerHand.getMao());
		cartasRestantes=new ArrayList<Carta>(this.sueca.getInitialDeck());
		getCartasRestantes();
		this.cartasJogadasNasRondas=new ArrayList<Carta>();
		
	}
	
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		byte[] cardsByComma = request.getByteSequenceContent();
		String s1 =  new String(cardsByComma,StandardCharsets.UTF_8);
		getCartasDaRonda(s1);
		removeCartasJogadasDaRonda();
		//printOddOfNaipe();
		 //this.action();
	}
	
	private void removeCartasJogadasDaRonda() {

			for(int j=0;j<this.cartasJogadasNasRondas.size();j++) {
				for(int i=0;i<cartasRestantes.size();i++) {
					if(cartasJogadasNasRondas.get(j).equals(cartasRestantes.get(i))) {
						this.cartasRestantes.remove(i);
						i--;
						break;
					}
				}
				this.cartasJogadasNasRondas.remove(j);
				j--;
			}
		
	}

	private void getCartasRestantes() {
		for(int i=0;i<10;i++) {
			for(int j=0;i<cartasRestantes.size();j++) {
				if(cartasJogador.get(i).equals(cartasRestantes.get(j))) {
					cartasRestantes.remove(j);
					j--;
					break;
				}
			}
		}
	}
	/*
	 * 	public int getNaipe() { 
		switch (naipe) {
		case ESPADAS: return 0; 
		case COPAS: return 1; 
		case PAUS: return 2; 
		case OUROS: return 3; 
	 */
	private void printOddOfNaipe() {
		System.out.println("odd de sair espadas:"  + this.calculaOdd(0) + "\n"+
				"odd de sair copas:"  + this.calculaOdd(1) + "\n"+
				"odd de sair paus:"  + this.calculaOdd(2) + "\n"+
				"odd de sair ouros:"  + this.calculaOdd(3) + "\n");
	}
	
	private float calculaOdd(int naipe) {
		float odd=0;
		int counter=0;
		for(int i=0;i<cartasRestantes.size()-1;i++) {
			if(cartasRestantes.get(i).getNaipe()==naipe) {
				counter++;
			}
		}
		odd=((float)counter)/((float)cartasRestantes.size());
		return odd;
	}

	private ArrayList<Carta> getCartasDaRonda(String s1) {
		Carta c1=null;
		String[] parts = s1.split(",");
		Round r1 = new Round(this.sueca);
		for(int i=0;i<parts.length;i++) {
			this.cartasJogadasNasRondas.add(c1=new Carta(this.cartasJogador.get(0).convertStringToNome(parts[i]),this.cartasJogador.get(0).convertStringToNaipe(parts[i])));
			r1.insertPlay(new Pair<Carta,Integer>(c1,i));
		}
		((SmartBotAGENT)this.myAgent).insertRonda(r1);
		return this.cartasJogadasNasRondas;
	}


}
