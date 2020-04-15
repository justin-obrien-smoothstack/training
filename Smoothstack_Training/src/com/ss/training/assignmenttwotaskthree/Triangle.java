package com.ss.training.assignmenttwotaskthree;

public class Triangle implements Shape {
	private double sideA, sideB, sideC;

	private static boolean triangleInequalitySatisfied(double sideA, double sideB, double sideC) {
		double longestSide = Math.max(Math.max(sideA, sideB), sideC);
		return sideA + sideB + sideC - longestSide >= longestSide;
	}

	public Triangle(double sideA, double sideB, double sideC) {
		if (sideA < 0 || sideB < 0 || sideC < 0) {
			System.err.println(
					"Error: There exists no triangle with a side of negative length. All side lengths will be set to 0.");
			return;
		}
		if (!triangleInequalitySatisfied(sideA, sideB, sideC)) {
			System.err.println("Error: There exists no triangle with sides of length " + sideA + ", " + sideB + ", and "
					+ sideC + ". All side lengths will be set to 0.");
			return;
		}
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
	}

	public double calculateArea() {
		if (sideA == 0 || sideB == 0 || sideC == 0)
			return 0;
		// Use the law of cosines and the Pythagorean identity to calculate the height.
		double base = sideA, cosC = (sideA * sideA + sideB * sideB - sideC * sideC) / (2 * sideA * sideB),
				sinC = Math.sqrt(1 - cosC * cosC), height = sideB * sinC;
		return base * height / 2;
	}

	public void display() {
		System.out.println(calculateArea());
	}
}
