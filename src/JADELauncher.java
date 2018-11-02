

import java.util.ArrayList;
import java.util.List;

import Agents.GameAGENT;
import Agents.RandomBotAGENT;
import Agents.SmartBotAGENT;
import GameLogic.Jogo;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class JADELauncher {

	public static void main(String[] args) {
		Runtime rt = Runtime.instance();

		Profile p1 = new ProfileImpl();
		//p1.setParameter(...);
		ContainerController mainContainer = rt.createMainContainer(p1);
		
		Profile p2 = new ProfileImpl();
		//p2.setParameter(...);
		ContainerController container = rt.createAgentContainer(p2);

		Jogo sueca = new Jogo();
//		Agent randomBotAgent1 = new RandomBotAGENT(sueca,sueca.getPlayer1());
//		Agent randomBotAgent2 = new RandomBotAGENT(sueca,sueca.getPlayer2());
//		Agent randomBotAgent3 = new RandomBotAGENT(sueca,sueca.getPlayer3());
//		Agent randomBotAgent4 = new RandomBotAGENT(sueca,sueca.getPlayer4());
		Agent SmartBotAGENT1 = new SmartBotAGENT(sueca,sueca.getPlayer1());
		Agent SmartBotAGENT2 = new SmartBotAGENT(sueca,sueca.getPlayer2());
		Agent SmartBotAGENT3 = new SmartBotAGENT(sueca,sueca.getPlayer3());
		Agent SmartBotAGENT4 = new SmartBotAGENT(sueca,sueca.getPlayer4());
		//Agent NeuralNetworkAGENT = new NeuralNetworkAGENT();

		Agent gameAgent = new GameAGENT(sueca,"SmartBotAgent");
		AgentController ac1;

		AgentController ac3;
		try {
			ac3 = mainContainer.acceptNewAgent("myRMA", new jade.tools.rma.rma());
			ac3.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			ac1 = mainContainer.acceptNewAgent("SmartBotAgent1", SmartBotAGENT1);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		
		try {
			ac1 = mainContainer.acceptNewAgent("SmartBotAgent2", SmartBotAGENT2);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		try {
			ac1 = mainContainer.acceptNewAgent("SmartBotAgent3", SmartBotAGENT3);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		
		try {
			ac1 = mainContainer.acceptNewAgent("SmartBotAgent4", SmartBotAGENT4);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

		AgentController ac2;

//		try {
//			ac1 = mainContainer.acceptNewAgent("SmartBotAgent1", SmartBotAGENT);
//			ac1.start();
//		} catch (StaleProxyException e) {
//			e.printStackTrace();
//		}
		try {
			ac2 = container.acceptNewAgent("gameAgent", gameAgent);
			ac2.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		

		/*
		try {
			ac1 = mainContainer.acceptNewAgent("NeuralNetworkAGENT", NeuralNetworkAGENT);
			ac1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		*/
	}

}
