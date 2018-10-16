
public class logic {
//renuncia
	//reaproveitar logica
	public logic() {
		
	};
	public int winner(Round ronda,Jogo game) {
		Carta biggest=ronda.RetornaCartasNaMesa(1);
		int winnerPlayer=1;
		if (ronda.temTrunfoNaMesa()){		
			if (ronda.RetornaCartasNaMesa(2).getNaipe() == game.getTrunfo().getNaipe()){//JOGADOR 2 JOGA TRUNFO
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){//JOGADOR 1 JOGOU TRUNFO
					if( ronda.RetornaCartasNaMesa(2).getPonto() > biggest.getPonto() ){
						biggest = ronda.RetornaCartasNaMesa(2);
						winnerPlayer = 2;
					}
				}
				else {
					biggest=ronda.RetornaCartasNaMesa(2);
					winnerPlayer = 2;
				}
				
			}
			if (ronda.RetornaCartasNaMesa(3).getNaipe() == game.getTrunfo().getNaipe()){
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){
					if( ronda.RetornaCartasNaMesa(3).getPonto() > biggest.getPonto()){
						biggest = ronda.RetornaCartasNaMesa(3);
						winnerPlayer = 3;
					}
				}
				else {
					biggest = ronda.RetornaCartasNaMesa(3);
					winnerPlayer = 3;
				}
				
			}
			if (ronda.RetornaCartasNaMesa(4).getNaipe() == game.getTrunfo().getNaipe()){
				if (biggest.getNaipe() == game.getTrunfo().getNaipe()){
					if( ronda.RetornaCartasNaMesa(4).getPonto() > biggest.getPonto()){
						biggest = ronda.RetornaCartasNaMesa(4);
						winnerPlayer = 4;
					}
				}
				else {
					biggest = ronda.RetornaCartasNaMesa(4);
					winnerPlayer = 4;
				}
				
			}
			return winnerPlayer;
		}
		else {
			if ((ronda.RetornaCartasNaMesa(2).getPonto() > biggest.getPonto()) && (ronda.RetornaCartasNaMesa(2).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(2);
				winnerPlayer = 2;
		    	}
			if ((ronda.RetornaCartasNaMesa(3).getPonto() > biggest.getPonto()) && (ronda.RetornaCartasNaMesa(3).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(3);
				winnerPlayer = 3;
		    	}
			if ((ronda.RetornaCartasNaMesa(4).getPonto() > biggest.getPonto()) && (ronda.RetornaCartasNaMesa(4).getNaipe()==biggest.getNaipe())){
				biggest = ronda.RetornaCartasNaMesa(4);
				winnerPlayer = 4;
		    	}
			return winnerPlayer;
		}
	}
	public boolean validPlay(Carta attempt,Jogador player, Round ronda) {
		if(ronda.getNumPlays()==0) // primeira jogada é sempre valida
			return true;
		else {
			if(player.getPlayerHand().checkIfHasNaipe(ronda.RetornaCartasNaMesa(1).getNaipe())) {//se tiver cartas do mesmo naipe que a jogada
				if(attempt.getNaipe()==ronda.RetornaCartasNaMesa(1).getNaipe()) { //se joga do mesmo naipe
					return true;
				}
				else return false;
			}
			else return true;
		}
	}
}
