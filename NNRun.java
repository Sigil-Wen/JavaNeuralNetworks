/**Java Neural Networks Runner Class
 * @author Sigil Wen
 * @author www.sigilwen.ca
 * @author sigil.w3n@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class NNRun {
    public static void main(String[] args) {
        int[] dims = { 2, 5, 3, 2 };
        NeuralNetwork nn = new NeuralNetwork(dims, true);
        nn.displayNet();
        double[][] in = {{0.0, 0.3},{2.0, 8.9}};
        double[][] out = {{1.0, 0.0},{0.0,1.0}};
        double[][] result = nn.forwardPass(in);
        System.out.println(result[0][0]);
        System.out.println(result[0][1]);
        double error = nn.meansSquareError(in,out);
        System.out.println(error);
        error = nn.logisticError(in,out);
        System.out.println(error);
        nn.displayResults(in);


    }
}