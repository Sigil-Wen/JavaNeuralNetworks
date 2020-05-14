/**Java Neural Networks Matrix
 * @author Sigil Wen
 * @author www.sigilwen.ca
 * @author sigil.w3n@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class Matrix {
    private final double[][] matrix;

    public Matrix(final int x, final int y) {
        matrix = new double[x][y];
    }

    public Matrix(final int x) {
        matrix = new double[x][1];
    }

    public double get(final int x, final int y) {
        return matrix[x][y];
    }

    public void randomize() { // sets all values to random double between -1 and 1
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public void randomize(int lower, int upper) { // inclusive
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; i++) {
                matrix[i][j] = Math.random() * (upper - lower) + lower;
            }
        }
    }

    public int x() {
        return matrix.length;
    }

    public int y() {
        return matrix[0].length;
    }
}