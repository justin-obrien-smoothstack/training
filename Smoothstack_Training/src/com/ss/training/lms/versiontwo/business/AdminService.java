package com.ss.training.lms.versiontwo.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.BookDAO;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;
import com.ss.training.lms.versiontwo.object.Publisher;

/**
 * Provides business logic for LMS functions available to users who are
 * administrators
 * 
 * @author Justin O'Brien
 */
public class AdminService extends LMSService {

	public String create(LMSObject newObject) {
		return null; // placeholder
	}

	public String update(LMSObject newObject) {
		return null; // placeholder
	}

	public String delete(LMSObject newObject) {
		return null; // placeholder
	}

	public LMSObject getBlankObject(String objectType) {
		switch (objectType) {
		case LMS.book:
			return new Book();
		case LMS.author:
			return new Author();
		case LMS.publisher:
			return new Publisher();
		case LMS.genre:
			return new Genre();
		case LMS.borrower:
			return new Borrower();
		case LMS.branch:
			return new Branch();
		case LMS.loan:
			return new Loan();
		case LMS.copies:
			return new Copies();
		}
		return null;
	}

	public void addNewPublisherToBooks(Publisher publisher, BookDAO bookDao) {
		ArrayList<Book> books = (ArrayList<Book>) getAllObjects(LMS.book);
		books = books.stream().filter(book -> publisher.getBookIds().contains(book.getId()))
				.collect(Collectors.toCollection(ArrayList::new));
		books.forEach(book -> book.setPubId(publisher.getId()));
		try {
			for (Book book : books)
				bookDao.update(book);
		} catch (Exception e) {
			System.out.println("There was an error while updating the publisher's books.");
			e.printStackTrace();
		}
	}

	public void updatePublisherBooks(Publisher oldPublisher, Publisher newPublisher, BookDAO bookDao) {
		ArrayList<Integer> bookIdsToAdd = new ArrayList<Integer>(), bookIdsToRemove = new ArrayList<Integer>();
		ArrayList<Book> booksToAdd, booksToRemove;
		oldPublisher.getBookIds().stream().filter(oldBookId -> !newPublisher.getBookIds().contains(oldBookId))
				.forEach(oldBookId -> bookIdsToRemove.add(oldBookId));
		newPublisher.getBookIds().stream().filter(newBookId -> !oldPublisher.getBookIds().contains(newBookId))
		.forEach(newBookId -> bookIdsToAdd.add(newBookId));
		booksToAdd = (ArrayList<Book>) getObjectsById(LMS.book, bookIdsToAdd);
		booksToRemove = (ArrayList<Book>) getObjectsById(LMS.book, bookIdsToRemove);
		booksToAdd.stream().forEach(book -> book.setPubId(newPublisher.getId()));
		booksToRemove.stream().forEach(book -> book.setPubId(null));
		try {
		for (Book book : booksToAdd)
			bookDao.update(book);
		for (Book book : booksToRemove)
			bookDao.update(book);
		} catch (Exception e) {
			System.out.println("An error occurred while updating the publisher's books.");
		}
		
	}

	/**
	 * Gets objects and descriptions of all loans for which the date in is null or
	 * later than the due date
	 * 
	 * @return Loan objects (row 0) and strings describing them (row 1)
	 */
	public Object[][] getOverridableLoansAndDescriptions() {
		return null; // placeholder
	}

	public String overrideDueDate(Loan loanToOveride) {
		return ""; // placeholder
	}

}
