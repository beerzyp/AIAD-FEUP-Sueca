import jade.core.behaviours.CyclicBehaviour;

public class RoundGameBehaviour extends CyclicBehaviour{
	private int step;
	
	public RoundGameBehaviour(int playerToMove) {
		step=playerToMove;
		
	}
	public void setPlayerToMove(int player) {
		step=player;
	}
	/*
	 * “Cyclic” behaviours that never complete and whose action() method executes the same operations
each time it is called. The jade.core.behaviours.CyclicBehaviour already implements the done()
method by returning false(non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		switch (step) {
		 case 0:
		 
			 break;
		 case 1:
		 //Agent 2 MakeMoveBehaviour
			 break;
		 case 2:
	     //Agent 3 MakeMoveBehaviour
			 break;
		 case 3:
		 //Agent 4 Make Move
			 break;
		 default:
			 break;
		}
		
	}

}
