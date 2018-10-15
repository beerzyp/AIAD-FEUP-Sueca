import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class RandomBotAgent extends Agent {
	
	Mao botHand;
	
	public RandomBotAgent() {
	};
	/*
	 * (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	
	public void setup() {
		addBehaviour(new MakeMoveBehaviour(null, null));
		
	}
	protected void takeDown() {
		
	}
	public Mao getBotHand() {
		return botHand;
	}

	public void setBotHand(Mao botHand) {
		this.botHand = botHand;
	}

	
	/*
	 * The performative can be REQUEST, if the sender wants the receiver
to perform an action, INFORM, if the sender wants the receiver to be aware a fact, QUERY_IF, if the
sender wants to know whether or not a given condition holds, CFP (call for proposal), PROPOSE,
ACCEPT_PROPOSAL, REJECT_PROPOSAL, if the sender and receiver are engaged in a negotiation,
and more.
	 */
}
