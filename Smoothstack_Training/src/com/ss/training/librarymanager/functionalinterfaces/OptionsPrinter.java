package com.ss.training.librarymanager.functionalinterfaces;

/**
 * Used for printing option menus to the terminal.
 * 
 * @author Justin O'Brien
 */
@FunctionalInterface
public interface OptionsPrinter {
	/**
	 * @param opts array containing arrays of codes and descriptions for options to
	 *             be printed on the menu
	 */
	void print(String[]... opts);
}
