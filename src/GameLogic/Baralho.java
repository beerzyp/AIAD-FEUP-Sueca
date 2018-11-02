package GameLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.*; 


public class Baralho {
	
	List<Carta> cartas;
	
	public Baralho(){
		cartas = new ArrayList<Carta>();
		
		for (Carta.Naipe naipe : Carta.Naipe.values())
            for (Carta.Nome nome : Carta.Nome.values())
                cartas.add(new Carta(nome, naipe));		
	}
	
	
	public List<Carta> getBaralho() { return cartas; }
	
	public void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle(cartas); 
		for(int i = 0; i < cartas.size(); i++) {
			//System.out.println(cartas.get(i).getNome() + " de " + cartas.get(i).getNaipe() + " " + cartas.get(i).getPonto());
		}
	}
	
	public List<Carta> dar(int jogadores){
		int sizeBaralho = cartas.size();
		int qtd = (sizeBaralho / jogadores);
		List<Carta> maoAux = cartas.subList(sizeBaralho-qtd, sizeBaralho); 	
		ArrayList<Carta> mao = new ArrayList<Carta>(maoAux);
		maoAux.clear();
				
		return mao;
	}
}
