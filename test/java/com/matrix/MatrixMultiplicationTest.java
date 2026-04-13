package com.matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixMultiplicationTest {

    @Test
    public void testBasicMultiplication() {
        int[][] matrixA = {
            {1, 2},
            {3, 4}
        };

        int[][] matrixB = {
            {5, 6},
            {7, 8}
        };

        int[][] expected = {
            {19, 22},
            {43, 50}
        };

        int[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testIdentityMatrix() {
        int[][] matrix = {
            {1, 0},
            {0, 1}
        };

        int[][] identity = {
            {1, 0},
            {0, 1}
        };

        int[][] expected = {
            {1, 0},
            {0, 1}
        };

        int[][] result = MatrixMultiplication.multiply(matrix, identity);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test3x3Multiplication() {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matrixB = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        int[][] expected = matrixA;
        int[][] result = MatrixMultiplication.multiply(matrixA, matrixB);
        assertArrayEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncompatibleDimensions() {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {1, 2},
            {3, 4}
        };

        MatrixMultiplication.multiply(matrixA, matrixB);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMatrix() {
        MatrixMultiplication.multiply(null, new int[][]{{1}});
    }
}
