package com.matrix;

public class MatrixMultiplication {

    /**
     * Multiply two matrices
     * @param matrixA First matrix (m x n)
     * @param matrixB Second matrix (n x p)
     * @return Result matrix (m x p)
     */
    public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Matrices cannot be null");
        }

        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException(
                "Cannot multiply: columns of A (" + colsA + ") != rows of B (" + rowsB + ")");
        }

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Print a matrix
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example: Multiply two 2x2 matrices
        int[][] matrixA = {
            {1, 2},
            {3, 4}
        };

        int[][] matrixB = {
            {5, 6},
            {7, 8}
        };

        System.out.println("Matrix A:");
        printMatrix(matrixA);

        System.out.println("Matrix B:");
        printMatrix(matrixB);

        int[][] result = multiply(matrixA, matrixB);

        System.out.println("Result (A * B):");
        printMatrix(result);
    }
}
