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

		}
	}

	@Override
	public boolean done() {
		if(flag)
			return true;
		return false;
	}

}
