import java.util.ArrayList;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CardDatabaseBehaviour extends CyclicBehaviour {
	public ArrayList<Carta> cartasRestantes;
	public ArrayList<Carta> cartasJogador;
	Jogo sueca;
	Mao playerHand;
	public CardDatabaseBehaviour(Jogo sueca,Mao playerHand) {
		this.sueca=sueca;
		this.playerHand=playerHand;
		cartasJogador = new ArrayList<Carta>(playerHand.getMao());
		cartasRestantes=new ArrayList<Carta>(this.sueca.getInitialDeck());
		getCartasRestantes();
		
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
	@Override
	public void action() {
		final ACLMessage request= this.myAgent.blockingReceive();
		 String message = request.getContent();
		 printOddOfNaipe();
	}



}
