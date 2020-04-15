package com.ss.training.assignment1task2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int min = 1, max = 100, closeEnough = 10, chances = 5, range = max - min + 1, target, input;
		target = (int) Math.floor(range * Math.random()) + min;
		System.out.print("Guess a number between 1-100: ");
		try (Scanner scanner = new Scanner(System.in)) {
			for (int i = 1; i <= chances; i++) {
				input = scanner.nextInt();
				if (Math.abs(input - target) <= closeEnough) {
					System.out.println("The number is " + target + '.');
					break;
				}
				if (i < chances) {
					System.out.print("Try again: ");
					continue;
				}
				System.out.println("Sorry; the number is " + target + '.');
			}

		}
	}

}
