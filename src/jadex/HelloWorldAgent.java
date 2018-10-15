package jadex;

import jade.core.Agent;

public class HelloWorldAgent extends Agent {

	
	/*
	 * (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 * The setup() method is intended to include agent initializations. 
	 */
	public void setup() {
		System.out.println("Hello world!");
	}
	
}
