import java.util.ArrayList;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import javafx.util.Pair;

public class RoundBehaviour extends SimpleBehaviour {
	public boolean flag=false;
	Jogo suecaGame;
	Behaviour playerMove1,playerMove2,playerMove3,playerMove4,checkWinnerRound;
	int counter=0;
	public RoundBehaviour(Jogo sueca) {
		suecaGame=sueca;
		counter=0;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		while(counter<10) {
		Behaviour seqRoundBehaviour = new SequentialBehaviour();
		Behaviour checkWinnerRoundBehaviour = new SequentialBehaviour();
		ArrayList<Pair<Carta,Integer>> jogadas= new ArrayList<Pair<Carta,Integer>>();
		Round round=new Round(suecaGame, jogadas);
		suecaGame.insertRound(round);
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove1=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer1(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove2=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer2(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove3=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer3(),round));
		((SequentialBehaviour) seqRoundBehaviour).addSubBehaviour(playerMove4=new AskForPlayerMove(this.suecaGame,suecaGame.getPlayer4(),round));

		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(seqRoundBehaviour);
		checkWinnerRound=new CheckWinnerBehaviour(suecaGame,round);
		((SequentialBehaviour) checkWinnerRoundBehaviour).addSubBehaviour(checkWinnerRound);
		this.myAgent.addBehaviour(checkWinnerRoundBehaviour);
		}
	}

	@Override
	public boolean done() {
		if(flag)
			return true;
		return false;
	}

}
