package com.ss.training.lms.versiontwo;

import java.util.ArrayList;
import java.util.Arrays;

import com.ss.training.lms.versiontwo.presentation.Presentation;

/**
 * Top level of the Library Management System application
 * 
 * @author Justin O'Brien
 */
public class LMS {

	public static final String book = "book", author = "author", publisher = "publisher", genre = "genre",
			branch = "branch", borrower = "borrower", loan = "loan", copies = "number of copies";
	public static final String books = "books", authors = "authors", publishers = "publishers", genres = "genres",
			branches = "branches", borrowers = "borrowers", loans = "loans", copieses = "numbers of copies";
	public static final String independent = "independent", relational = "relational", mono = "mono", multi = "Multi",
			optional = "optional", required = "required", composite = "composite";

	public static void main(String[] args) {
		ArrayList<String> rootMenuOptions = new ArrayList<String>();
		ArrayList<Object> rootMenuParameters = new ArrayList<Object>();
		Presentation presentation = Presentation.getInstance();
		rootMenuOptions.addAll(Arrays.asList(
				new String[] { presentation.exit, presentation.librarian, presentation.borrower, presentation.admin }));
		presentation.presentMenu(presentation.rootMenuPrompt, rootMenuOptions, rootMenuParameters);
	}
}
