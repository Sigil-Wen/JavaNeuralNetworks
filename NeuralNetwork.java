/**Java Neural Networks
 * @author Sigil Wen
 * @author www.sigilwen.ca
 * @author sigil.w3n@gmail.com
 * @version 1.0
 * @since 1.0
 */
import java.util.*;

public class NeuralNetwork {
    private ArrayList<Matrix> nodes = new ArrayList<Matrix>();
    private int[] dim;
    private boolean softmax = false;

    public NeuralNetwork(int[] dimensions) {
        dim = dimensions;
        initNodes(dimensions);
        initWeights();
    }
    public NeuralNetwork(int[] dimensions, boolean sm) {
        dim = dimensions;
        initNodes(dimensions);
        initWeights();
        softmax = sm;
    }

    public int[] getDim() {
        return dim;
    }

    //displays
    public void displayNet() {
        int layer = 1;
        System.out.println("Neural net with dim takes in " + dim[0] + " doubles");
        for (Matrix node : nodes) {
            System.out.println("\nLayer " + layer + " (" + dim[layer] + " nodes)");
            for (int i = 0; i < dim[layer]; i++) {
                System.out.print("b" + i + " : " + node.get(i, 0) + " ,");
                for (int j = 1; j < node.y(); j++) {
                    System.out.print("w" + j + " : " + node.get(i, j) + " ,");
                }
                System.out.println();
            }
            layer++;
        }
        System.out.println("Softmax : " + softmax);
    }

    public void displayResults(double[][] input){
        double[][] results = forwardPass(input);
        for (int i =0; i<results.length; i++){
            System.out.print("Input: ");
            for (int k = 0; k<input[i].length; k++){
                System.out.print(input[i][k] + ", ");
            }
            System.out.print("Output: ");
            for (int j = 0; j<results[i].length; j++){
                System.out.print(results[i][j] + ", ");
            }
            System.out.println();
        }
    }
    public void initNodes(int[] dimensions) {
        int wab; // number of weights and biases
        for (int i = 1; i < dimensions.length; i++) {
            wab = dimensions[i - 1] + 1; // takes value greater
            Matrix matrix = new Matrix(dimensions[i], wab);
            nodes.add(matrix);
        }
    }

    public void initWeights() {
        for (Matrix node : nodes) {
            node.randomize();
        }
    }


    //Propagation
    public double[][] forwardPass(double[][] input) {
        double[][] results = new double[input.length][dim[dim.length-1]]; // creates x results with y output values
        for (int n = 0; n<input.length; n++){ //loops through every set of inputs
            double[] pLayer = input[n]; // values of previous layer
            int layer = 1;
            for (Matrix node : nodes) { //loops through each layer in the neural net
                double[] cLayer = new double[dim[layer]];// creates current layer with size of number of nodes
                for (int i = 0; i < dim[layer]; i++) {  //loops through each node
                    cLayer[i] += node.get(i, 0);//adds bias
                    for (int j = 1; j < dim[layer-1]; j++) {//loops through each weight
                        cLayer[i] += node.get(i, j) * pLayer[j - 1];
                    }
                    cLayer[i] = sigmoid(cLayer[i]);
                }
                pLayer = new double[cLayer.length];
                for (int x = 0; x<cLayer.length; x++){
                    pLayer[x]=cLayer[x];
                }

                layer++;
            }
            results[n] = pLayer;
        }
        if (softmax) {
            softmax(results);
        }
        return results;
    }
    public void backPropagation(){

    }



    public double sigmoid(double num) {
        double e = Math.E;
        double ans =1 / (1 + (1 / Math.pow(e, num)));
        return ans;
    }

    public double[][] softmax(double[][] input){
        for (int j = 0; j < input.length; j++) {
            int sum = 0;
            for( int i = 0;i < input[j].length;i++){
                sum+= Math.pow(Math.E, input[j][i]);
            }
            for( int i = 0;i < input[j].length;i++){
                input[j][i] = Math.pow(Math.E, input[j][i])/sum;
            }
        }
        return input;
    }

    //Error Functions
    public double meansSquareError(double[][] input, double[][] output){
        double error = 0;
        for (int i = 0; i<input.length; i++){
            double[][] result = forwardPass(input);
            for (int j = 0; j < output[i].length; j++ ){
                error += Math.pow((output[i][j]-result[i][j]), 2);
            }
        }
        error /= output.length*output[0].length;
        return error;
    }
    public double logisticError(double[][] input, double[][] output){
        double error = 0;
        for (int i = 0; i<input.length; i++){
            double result[][] = forwardPass(input);
            for (int j =0; j<output[i].length;j++){
                error += -(output[i][j]*Math.log(result[i][j])+(i-output[i][j])*Math.log(1-result[i][j]));
            }
        }
        error /= output.length*output[0].length;
        return error;
    }
}