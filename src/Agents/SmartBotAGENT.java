package Agents;

import java.util.*;


import Behaviours.MakeMoveBehaviour;
import Behaviours.MakeSmartMoveBehaviour;
import Behaviours.GetDifferentStrategiesBehaviour;
import GameLogic.Carta;
import GameLogic.Jogador;
import GameLogic.Jogo;
import GameLogic.Round;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import javafx.util.Pair;

public class SmartBotAGENT extends Agent{
	private Jogo sueca;
	private Jogador player;
	private Behaviour cardDatabase;
	private Behaviour makeSmartMove;
	private Behaviour getStrats;
	private ArrayList<Round> rondas;
	public ArrayList<Carta> cartasRestantes;
	public ArrayList<Carta> cartasJogador;
	public ArrayList<Carta> cartasJogadasNasRondas;
	public ArrayList<String> stratsBotHas;
	private ArrayList<Pair<String,Carta>> validLogicPlays;
	public ArrayList<Pair<String, Carta>> getValidLogicPlays() {
		return validLogicPlays;
	}

	public void setValidLogicPlays(ArrayList<Pair<String, Carta>> validLogicPlays) {
		this.validLogicPlays = validLogicPlays;
	}
	public void insertLogicPlays(Pair<String, Carta> validLogicPlays) {
		this.validLogicPlays.add(validLogicPlays);
	}

	public boolean flag;
	public SmartBotAGENT(Jogo sueca, Jogador player,ArrayList<String> stratsBot) {
		this.flag=true;
		this.sueca=sueca;
		this.player=player;
		rondas=new ArrayList<Round>();
		cartasJogador = new ArrayList<Carta>(this.player.getPlayerHand().getMao());
		cartasRestantes=new ArrayList<Carta>(this.sueca.getInitialDeck());
		stratsBotHas = new ArrayList<String>(stratsBot);
		validLogicPlays = new ArrayList<Pair<String,Carta>>();
		getCartasRestantes();
		this.cartasJogadasNasRondas=new ArrayList<Carta>();
	};
	
	public SmartBotAGENT(Jogo sueca, Jogador player,boolean flag) {
		this.flag=flag;
		this.sueca=sueca;
		this.player=player;
		rondas=new ArrayList<Round>();
		cartasJogador = new ArrayList<Carta>(this.player.getPlayerHand().getMao());
		cartasRestantes=new ArrayList<Carta>(this.sueca.getInitialDeck());
		getCartasRestantes();
		this.cartasJogadasNasRondas=new ArrayList<Carta>();
	};
	public void insertRonda(Round r1) {
		this.rondas.add(r1);
	}


	@Override
	public void setup() {
		//this.addBehaviour(makeSmartMove= new MakeMoveBehaviour(this.sueca,"SmartBotAgent"));
		if(!this.flag) {
			makeSmartMove=new MakeSmartMoveBehaviour(this.sueca,"SmartBotAgent");
			this.addBehaviour(makeSmartMove);
		}
		else{
			getStrats =new GetDifferentStrategiesBehaviour(stratsBotHas,this.sueca);
			this.addBehaviour(getStrats);
			
			//this.addBehaviour(new MakeSmartMoveBehaviour(this.sueca,"SmartBotAgent"));
		}
		//((SequentialBehaviour) TenRounds).addSubBehaviour(new CardDatabaseBehaviour(this.sueca,this.player.getPlayerHand()));

	}

	public void removeCartasJogadasDaRonda() {

		for(int j=0;j<this.cartasJogadasNasRondas.size();j++) {
			for(int i=0;i<cartasRestantes.size();i++) {
				if(cartasJogadasNasRondas.get(j).equals(cartasRestantes.get(i))) {
					this.cartasRestantes.remove(i);
					i--;
					break;
				}
			}
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

	public void printOddOfNaipe() {
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

	public  ArrayList<Carta> getCartasDaRondas() {
		Carta c1=null;
		Round r1 = new Round(this.sueca);
		 ArrayList<Carta> vect= new ArrayList<Carta>();
		 for(int i=0;i<this.sueca.getMatchRounds().size();i++) {
			 if(this.sueca.getMatchRounds().get(i).getNumPlays()==0) {
				 break;
			 }
			 for(int j=0;j<this.sueca.getMatchRounds().get(i).returnTableHand().size();j++) {
				if(this.sueca.getMatchRounds().get(i).returnTableHand().get(j).getValue()!=this.player.jogNum) {
					this.cartasJogadasNasRondas.add(this.sueca.getMatchRounds().get(i).returnTableHand().get(j).getKey());
				}
			 }
		 }
		 
		return this.cartasJogadasNasRondas;
	}


	@Override
	protected void takeDown() {

	}
	public Carta returnPLay() {
		/*
		 * FOR (todas as cartas possiveis)
		 *  EV($) = pontosGanhos * prob(ganhar) - pontosPerdidos*prob(perder)
		 *  
		 *  
		 *  where pontosGanhos->pontos(cartaJogada)+pontos(boardAtual)+pontos(jogadasRestantes)
		 *  where pontosPerdidos->pontos(cartaJogada)
		 */
		List<Double> list = new ArrayList<>();
		for(int i=0;i<this.player.getPlayerHand().getMao().size();i++) {
			//getExpectedValueOfPlay(this.player.getPlayerHand().getMao().get(i),)
		}
		Random r = new Random();
		int Low = 0;
		int High =this.player.getPlayerHand().getMao().size();
		int Result = r.nextInt(High-Low) + Low;
		return this.player.getPlayerHand().getCartaAt(Result);
	}
	
	public int returnJogadaNaRonda() {
		
		for(int i = 0; i < sueca.getMatchRounds().size(); i++) {
			if(sueca.getMatchRounds().get(i).getNumPlays()!=0 && sueca.getMatchRounds().get(i).getNumPlays() != 4)
				return sueca.getMatchRounds().get(i).getNumPlays();
		}
		
		return 0;
		
	}
}
