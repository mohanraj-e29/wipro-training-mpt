public class constructortest {
    private int[][] matrix;
    
    public constructortest(int rows, int cols) {
        matrix = new int[rows][cols];
    }

    // Method to fill the matrix with values
    public void inputmatrix(int value) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = value;
            }
        }
    }

    // Method to print the matrix
    public void printmatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example usage
        constructortest matrix = new constructortest(4, 4);
        matrix.inputmatrix(1); // Fill the matrix with the value 5
        matrix.printmatrix();
    }
}
