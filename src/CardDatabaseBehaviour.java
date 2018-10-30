import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class CardDatabaseBehaviour extends Behaviour {
	public ArrayList<Carta> cartasRestantes;
	public ArrayList<Carta> cartasJogador;
	public ArrayList<Carta> roundCards;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void action() {
		//OUVIR MENSAGEM DE FIM DE RONDAW
		//RECEIVE INFORMWW
		final ACLMessage inform = this.myAgent.blockingReceive();
		byte[] cardsByComma = inform.getByteSequenceContent();
		String s1 =  new String(cardsByComma,StandardCharsets.UTF_8);
		ArrayList<Carta> a1= new ArrayList<Carta>(getCartasDaRonda(s1));
		
		System.out.println("Odd de sair espadas" +this.getOddOfNaipe(0)+"\n"+
				"Odd de sair copas" +this.getOddOfNaipe(1)+"\n"+
				"Odd de sair paus" +this.getOddOfNaipe(2)+"\n"+
				"Odd de sair ouros" +this.getOddOfNaipe(3)+"\n");
		
	}
	private ArrayList<Carta> getCartasDaRonda(String s1) {
		
		return null;
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
	
	private void updateCartasRestantes(ArrayList<Carta> cartasRonda) {
		for(int j=0;j<cartasRonda.size();j++) {
			for(int i=0;i<cartasRestantes.size();i++) {
				if(cartasRestantes.get(i).equals(cartasRonda.get(j))) {
					cartasRestantes.remove(i);
					i--;
					break;
				}
			}
		}
	}
	
	/*
	 * 		case ESPADAS: return 0; 
		case COPAS: return 1; 
		case PAUS: return 2; 
		case OUROS: return 3; 
	 */
	public float getOddOfTrunfo() {
		return this.getOddOfNaipe(this.sueca.getTrunfo().getNaipe());
	}
	public float getOddOfNaipe(int naipe){
		float odd=0;
		int numOfCards=0;
		for(int i=0;i<this.cartasRestantes.size();i++) {
			if(this.cartasRestantes.get(i).getNaipe()==naipe)
				numOfCards++;
		}
		odd=((float)numOfCards)/((float)this.cartasRestantes.size());
		return odd;
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
	



}
