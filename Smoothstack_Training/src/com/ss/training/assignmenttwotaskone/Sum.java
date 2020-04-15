package com.ss.training.assignmenttwotaskone;

public class Sum {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Error: At least one argument must be given.");
			return;
		}
		double sum = 0;
		for (String arg : args) {
			try {
				sum += Double.parseDouble(arg);
			} catch (NumberFormatException error) {
				System.err.println("Error: All arguments must be numeric.");
				return;
			}
		}
		System.out.println(sum);
	}

}
