package com.ss.training.assignmenttwotaskthree;

public class Circle implements Shape {
	private double radius;

	public Circle(double radius) {
		if (radius < 0) {
			System.err.println("Error: There exists no circle with a negative radius. Radius will be set to 0.");
			return;
		}
		this.radius = radius;
	}

	public double calculateArea() {
		return Math.PI * radius * radius;
	}

	public void display() {
		System.out.println(calculateArea());
	}
}
