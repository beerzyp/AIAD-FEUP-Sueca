import java.util.ArrayList;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

public class CardDatabaseBehaviour extends CyclicBehaviour {
	public ArrayList<Carta> cartasRestantes;
	public ArrayList<Carta> cartasJogador;
	Jogo sueca;
	Mao playerHand;
	public CardDatabaseBehaviour(Jogo sueca,Mao playerHand) {
		this.sueca=sueca;
		this.playerHand=playerHand;
		cartasJogador = new ArrayList<Carta>(playerHand.getMao());
		Carta carta;
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
	
	public float getOddOfNaipe(int naipe){
		float odd=0;
		int numOfCards=0;
		for(int i=0;i<this.cartasRestantes.size();i++) {
			if(this.cartasRestantes.get(i).getNaipe()==naipe)
				numOfCards++;
		}
		odd=numOfCards/this.cartasRestantes.size();
		return odd;
		
	}
	@Override
	public void action() {
		

	}



}
