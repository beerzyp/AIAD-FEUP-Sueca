import java.util.HashMap;
import java.util.Objects;


public class Carta {

	HashMap<Integer, String> hmap = new HashMap<Integer, String>();

	public enum Naipe{ESPADAS,PAUS,OUROS,COPAS};
	public enum Nome{DOIS,TRES,QUATRO,CINCO,SEIS,DAMA,VALETE,REI,SETE,AS};

	private Naipe naipe;
	private Nome nome;
	private int valor;

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
