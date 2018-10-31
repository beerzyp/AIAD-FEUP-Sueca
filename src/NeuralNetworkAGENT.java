import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import jade.core.Agent;

public class NeuralNetworkAGENT extends Agent {
	@Override
	public void setup() {
		readDataSet();
		neuralNetwork.learn(trainingSet);
		// save the trained network into file
		neuralNetwork.save("test.nnet");
	}
	// create new perceptron network
	private NeuralNetwork neuralNetwork;
	private DataSet trainingSet;
	// create training set

	public NeuralNetworkAGENT() {
		neuralNetwork = new Perceptron(4,1);
		this.readDataSet();
		//new DataSetRow (new double[]{0,0,0,0,0,0,0,0,0},new double[]{0});
		// add training data to training set (logical OR function)
//		trainingSet. addRow (new DataSetRow (new double[]{0, 0,0},new double[]{0}));
//		trainingSet. addRow (new DataSetRow (new double[]{0, 1},new double[]{1}));
//		trainingSet. addRow (new DataSetRow (new double[]{1, 0},new double[]{1}));
//		trainingSet. addRow (new DataSetRow (new double[]{1, 1},new double[]{1}));
		// learn the training se
		/*
		 * 
		 */
	}
	// add training data to training set (logical OR function)
	//neuralNetwork
	// save the trained network into file
	//neuralNetwork.save(“or_perceptron.nnet”);
	
	public DataSet readDataSet() {
		trainingSet = DataSet.createFromFile("C:\\Users\\up201505092\\eclipse-workspace\\aiad-feup\\dataSet.csv", 4,1,",");
		return trainingSet;
	}
}
