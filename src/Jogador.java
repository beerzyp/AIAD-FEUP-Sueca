
public class Jogador {
	
	public static int counter=0;
	
	

	private Mao hand;
	public int jogNum;
	public Jogador(Mao mao) {
		hand=mao;
		jogNum=counter++;
	}
	
	public int getJogNum() {
		return this.jogNum;
	}
	public Mao getPlayerHand() {
		// TODO Auto-generated method stub
		return this.hand;
	}
	
	public boolean fazJogada(Carta carta) {
		if(hand.jogaCarta(carta))
			return true;
		return false;
	}

}
