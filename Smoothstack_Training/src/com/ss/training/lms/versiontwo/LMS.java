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

	public static final String branchTable = "tbl_library_branch";
	public static final String branchPkColumn = "branchId";

	public static void main(String[] args) {
		ArrayList<String> rootMenuOptions = new ArrayList<String>();
		ArrayList<Object> rootMenuParameters = new ArrayList<Object>();
		Presentation presentation = Presentation.getInstance();
		rootMenuOptions.addAll(Arrays.asList(
				new String[] { presentation.exit, presentation.librarian, presentation.borrower, presentation.admin }));
		presentation.presentMenu(presentation.rootMenuPrompt, rootMenuOptions, rootMenuParameters);
	}
}
