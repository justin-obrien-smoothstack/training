package com.ss.training.lms.versiontwo.presentation;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;

/**
 * @author Justin O'Brien
 */
public class PresCrud {

	AdminService adminService = new AdminService();

	protected String deleteAuthor() {

	}

	protected String deleteBook() {

	}

	protected String deleteBranch() {

	}

	protected String delete() {

	}

	protected String delete() {

	}

	protected String delete() {

	}

	protected String crudRouter(String operation, String objectType) {
		switch (operation) {
		case Presentation.create:
			switch (objectType) {
			case LMS.author:
				return createAuthor();
			case LMS.book:
				return createBook();
			case LMS.borrower:
				return createBorrower();
			case LMS.branch:
				return createBranch();
			case LMS.genre:
				return createGenre();
			case LMS.publisher:
				return createPublisher();
			}
		case Presentation.read:
			switch (objectType) {
			case LMS.author:
				return adminService.readAuthors();
			case LMS.book:
				return adminService.readBooks();
			case LMS.borrower:
				return adminService.readBorrowers();
			case LMS.branch:
				return adminService.readBranches();
			case LMS.genre:
				return adminService.readGenres();
			case LMS.publisher:
				return adminService.readPublishers();
			}
		case Presentation.update:
			switch (objectType) {
			case LMS.author:
				return updateAuthor();
			case LMS.book:
				return updateBook();
			case LMS.borrower:
				return updateBorrower();
			case LMS.branch:
				return updateBranch();
			case LMS.genre:
				return updateGenre();
			case LMS.publisher:
				return updatePublisher();
			}
		case Presentation.delete:
			switch (objectType) {
			case LMS.author:
				return deleteAuthor();
			case LMS.book:
				return deleteBook();
			case LMS.borrower:
				return deleteBorrower();
			case LMS.branch:
				return deleteBranch();
			case LMS.genre:
				return deleteGenre();
			case LMS.publisher:
				return deletePublisher();
			}
		}
		return "No action was taken.";
	}

}
