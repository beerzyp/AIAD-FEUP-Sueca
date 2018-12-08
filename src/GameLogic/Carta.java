package GameLogic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javafx.util.Pair;


public class Carta {

	HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	public enum Naipe{ESPADAS,PAUS,OUROS,COPAS};
	public enum Nome{DOIS,TRES,QUATRO,CINCO,SEIS,DAMA,VALETE,REI,SETE,AS};

	private Naipe naipe;
	private Nome nome;
	private int valor;
	public Carta() {};
	public Carta(Nome nome, Naipe naipe){
		this.nome = nome;
		this.naipe = naipe;
		switch (nome) {

		case DOIS: case TRES: case QUATRO: case CINCO: case SEIS: 
			this.valor = 0;
			break;
		case DAMA: 
			this.valor = 2;
			break;
		case VALETE:
			this.valor = 3;
			break;
		case REI:
			this.valor = 4;
			break;
		case AS:
			this.valor = 11;
			break;
		case SETE:
			this.valor = 10;
			break;
		default:
			break;	
		}
	}

	public Nome getNome() { return nome; }
	public int getNaipe() { 
		switch (naipe) {
		case ESPADAS: return 0; 
		case COPAS: return 1; 
		case PAUS: return 2; 
		case OUROS: return 3; 
		default: break;
		}
		return 0;
	}
	public ArrayList<Pair<Integer,Integer>> convertToDataSetArray(ArrayList<Carta> cartas){
		ArrayList<Pair<Integer,Integer>> DataSet= new ArrayList<Pair<Integer,Integer>>();
		for(int i=0;i<cartas.size();i++) {
			DataSet.add(new Pair(cartas.get(i).getDataSetCardValue(),cartas.get(i).getNaipe()));
		}
		return DataSet;
		
	}
	// 2-6   11-Q 12-J 13-K 14-7 15-A
	public int getDataSetCardValue() {
		switch (nome) {
			case DOIS: 
				return 2;
			case TRES: 
				return 3;
			case QUATRO: 
				return 4;
			case CINCO: 
				return 5;
			case SEIS: 
				return 6;
			case DAMA: 
				return 11;
			case VALETE:
				return 12;
			case REI:
				return 13;
			case SETE:
				return 14;
			case AS:
				return 15;
			default:
				return -1;
		}
	}
	public int getPonto() { return valor; }
	public String toString() { return nome + " de " + naipe; }		
	
	public Naipe convertStringToNaipe(String carta) {
		String[] parts = carta.split(" ");
		String aux = parts[2];
		switch(aux) {
		case "ESPADAS":
			return naipe.ESPADAS;
		case "COPAS":
			return naipe.COPAS;
		case "PAUS":
			return naipe.PAUS;
		case "OUROS":
			return naipe.OUROS;
		default:
			return null;
		}
		
	}
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof Carta) {
			Carta p = (Carta) o;
			return  ((Objects.equals(((Carta) o).getNaipe(),this.getNaipe())) && (Objects.equals(((Carta) o).getNome(), this.getNome())));
		}
		return false;
		
	}
	
	public Nome convertStringToNome(String carta) {
		String[] parts = carta.split(" ");
		String aux = parts[0];
		switch(aux) {
		case "DOIS":
			return nome.DOIS;
		case "TRES":
			return nome.TRES;
		case "QUATRO":
			return nome.QUATRO;
		case "CINCO":
			return nome.CINCO;
		case "SEIS":
			return nome.SEIS;
		case "SETE":
			return nome.SETE;
		case "DAMA":
			return nome.DAMA;
		case "VALETE":
			return nome.VALETE;
		case "REI":
			return nome.REI;
		case "AS":
			return nome.AS;
			default:
				return null;
		}

	}
}
