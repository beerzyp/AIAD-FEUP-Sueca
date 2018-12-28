package GameLogic;

public class logic {
//renuncia
	//reaproveitar logica
	public logic() {
		
	};
	public int winner(Round ronda,Jogo game) {
		//Jogadores 1 2 3 4
		Carta biggest=ronda.RetornaCartasNaMesa(game.getPlayer1().getJogNum()+1);
		int winnerPlayer=1;
		if (ronda.temTrunfoNaMesa()){		
			if (ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1).getNaipe() == game.getTrunfo().getNaipe()){//JOGADOR 2 JOGA TRUNFO
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){//JOGADOR 1 JOGOU TRUNFO
					if( ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue() ){
						biggest = ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1);
						winnerPlayer = 2;
					}
				}
				else {
					biggest=ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1);
					winnerPlayer = 2;
				}
				
			}
			if (ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1).getNaipe() == game.getTrunfo().getNaipe()){
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){
					if( ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue()){
						biggest = ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1);
						winnerPlayer = 3;
					}
				}
				else {
					biggest = ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1);
					winnerPlayer = 3;
				}
				
			}
			if (ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1).getNaipe() == game.getTrunfo().getNaipe()){
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){
					if( ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue()){
						biggest = ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1);
						winnerPlayer = 4;
					}
				}
				else {
					biggest = ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1);
					winnerPlayer = 4;
				}
				
			}
			return winnerPlayer;
		}
		else {
			if ((ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue()) && (ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(game.getPlayer2().getJogNum()+1);
				winnerPlayer = 2;
		    	}
			if ((ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue()) && (ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(game.getPlayer3().getJogNum()+1);
				winnerPlayer = 3;
		    	}
			if ((ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1).getDataSetCardValue() > biggest.getDataSetCardValue()) && (ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(game.getPlayer4().getJogNum()+1);
				winnerPlayer = 4;
		    	}
			return winnerPlayer;
		}
	}
	public boolean validPlay(Carta attempt,Jogador player, Round ronda) {
		if(ronda.getNumPlays()==0) // primeira jogada é sempre valida
			return true;
		else {
			if(player.getPlayerHand().checkIfHasNaipe(ronda.RetornaCartasNaMesa(2).getNaipe())) {//se tiver cartas do mesmo naipe que a jogada
				if(attempt.getNaipe()==ronda.RetornaCartasNaMesa(2).getNaipe()) { //se joga do mesmo naipe
					return true;
				}
				else return false;
			}
			else return true;
		}
	}
}
