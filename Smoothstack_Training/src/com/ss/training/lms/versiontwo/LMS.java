package com.ss.training.lms.versiontwo;

import java.util.ArrayList;
import java.util.Collections;

import com.ss.training.lms.versiontwo.presentation.Presentation;

/**
 * Top level of the Library Management System application
 * 
 * @author Justin O'Brien
 */
public class LMS {

	public static final String branchTable = "tbl_library_branch";
	public static final String branchPkColumn = "branchId";

	public static void main(String[] args) {
		String rootMenuPrompt = "Welcome to the library management system. Please indicate what type of user you are.";
		ArrayList<String> rootMenuOptions = new ArrayList<String>();
		Presentation presentation = Presentation.getInstance();
		Collections.addAll(rootMenuOptions,
				new String[] { presentation.exit, presentation.librarian, presentation.borrower, presentation.admin });
		ArrayList<Object> rootMenuParameters = new ArrayList<Object>();
		presentation.presentMenu(rootMenuPrompt, rootMenuOptions, rootMenuParameters);
	}
}
