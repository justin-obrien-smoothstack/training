package com.ss.training.assignmenttwotaskthree;

public class Rectangle implements Shape {
	private double length, width;

	public Rectangle(double length, double width) {
		if (length < 0 || width < 0) {
			System.err.println(
					"Error: There exists no rectangle with a side of negative length. All side lengths will be set to 0.");
			return;
		}
		this.length = length;
		this.width = width;
	}

	public double calculateArea() {
		return length * width;
	}

	public void display() {
		System.out.println(calculateArea());
	}
}
