import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mao {
	private List<Carta> maoJogador;
	public Mao(List<Carta> cartas1) {
		maoJogador= new ArrayList<Carta>(cartas1);

	}
	
	public boolean jogaCarta(Carta carta){
		int i=0;
		Iterator<Carta> it=maoJogador.iterator();
		while(it.hasNext()) {
			if(it.next().equals(carta)) {
				maoJogador.remove(i);
				//System.out.println("entrou\n" +  maoJogador.size()+ "\n");
				//Ronda.addCarta(Jogador,Carta)
				return true;
			}
			i++;
		
		}
		//System.out.println(maoJogador.size());
		return false;

	}
	public List<Carta> getMao(){
		return maoJogador;
	}
	public Carta getCartaAt(int i) {
		return maoJogador.get(i);
	}
	public boolean checkIfHasNaipe(int naipe) {
	    Iterator<Carta> it=maoJogador.iterator();
		while(it.hasNext()) {
			if(it.next().getNaipe()==naipe) {
				return true;
			}
		}
		return false;
	}
}
