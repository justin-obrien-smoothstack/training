package com.ss.training.librarymanager.functionalinterfaces;

/**
 * Used for printing prompts to the terminal.
 * 
 * @author Justin O'Brien
 *
 */
@FunctionalInterface
public interface MessagePrinter {
	/**
	 * @param strings
	 */
	void print(String... strings);
}