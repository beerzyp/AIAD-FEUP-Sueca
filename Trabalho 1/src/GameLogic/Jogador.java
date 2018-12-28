package GameLogic;
import java.util.List;

public class Jogador {
	
	public static int counter=0;
	
	

	private Mao hand;
	public int jogNum;
	public Jogador() {
		// TODO Auto-generated constructor stub
	}
	
	public Jogador(List<Carta> cartas1) {
		jogNum=++counter;
		hand =new Mao(cartas1);
	}

	public int getJogNum() {
		return this.jogNum;
	}
	public Mao getPlayerHand() {
		return this.hand;
	}
	
	public boolean fazJogada(Carta carta) {
		if(this.hand.jogaCarta(carta))
			return true;
		return false;
	}

}
