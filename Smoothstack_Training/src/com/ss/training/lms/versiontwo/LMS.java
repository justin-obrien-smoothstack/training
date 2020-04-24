package com.ss.training.lms.versiontwo;

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
		String[] rootMenuOptions = { Presentation.exit, Presentation.librarian, Presentation.borrower,
				Presentation.admin };
		Object[] rootMenuParameters = null;
		Presentation.presentMenu(rootMenuPrompt, rootMenuOptions, rootMenuParameters);
	}
}
