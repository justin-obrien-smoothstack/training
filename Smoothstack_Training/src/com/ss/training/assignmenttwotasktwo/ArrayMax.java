package com.ss.training.assignmenttwotasktwo;

public class ArrayMax {

	static double scaledRandom(double lowerBound, double upperBound) {
		return Math.random() * (upperBound - lowerBound) + lowerBound;
	}

	public static void main(String[] args) {
		int rows = 10, columns = 10, maxRow = 0, maxCol = 0, i, j;
		double[][] theArray = new double[rows][columns];
		double lowerBound = -1, upperBound = 1, max = theArray[0][0] = scaledRandom(lowerBound, upperBound);
		for (i = 0; i < rows; i++) {
			for (j = (i == 0) ? 1 : 0; j < columns; j++) {
				theArray[i][j] = scaledRandom(lowerBound, upperBound);
				if (theArray[i][j] > max) {
					max = theArray[i][j];
					maxRow = i;
					maxCol = j;
				}
			}
		}
		System.out.println("Maximum value: " + max);
		System.out.println("Found at position: [" + maxRow + "][" + maxCol + "]");

	}

}
