package com.ss.training.lms.versiontwo.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.AuthorDAO;
import com.ss.training.lms.versiontwo.business.dao.BookDAO;
import com.ss.training.lms.versiontwo.business.dao.BorrowerDAO;
import com.ss.training.lms.versiontwo.business.dao.BranchDAO;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.business.dao.GenreDAO;
import com.ss.training.lms.versiontwo.business.dao.LoanDAO;
import com.ss.training.lms.versiontwo.business.dao.PublisherDAO;
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

	public String createAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).create(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String createBook(Book book) {
		int bookId;
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			bookId = new BookDAO(connection).create(book);
			book.getCopies().stream().forEach(copies -> copies.setBookId(bookId));
			book.getLoans().stream().forEach(loan -> loan.setBookId(bookId));
			for (Copies copies : book.getCopies())
				copiesDAO.create(copies);
			for (Loan loan : book.getLoans())
				loanDAO.create(loan);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String createBorrower(Borrower borrower) {
		int cardNo;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			loanDAO = new LoanDAO(connection);
			cardNo = new BorrowerDAO(connection).create(borrower);
			borrower.getLoans().stream().forEach(loan -> loan.setCardNo(cardNo));
			for (Loan loan : borrower.getLoans())
				loanDAO.create(loan);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String createBranch(Branch branch) {
		int branchId;
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			branchId = new BranchDAO(connection).create(branch);
			branch.getCopies().stream().forEach(copies -> copies.setBranchId(branchId));
			branch.getLoans().stream().forEach(loan -> loan.setBranchId(branchId));
			for (Copies copies : branch.getCopies())
				copiesDAO.create(copies);
			for (Loan loan : branch.getLoans())
				loanDAO.create(loan);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String createGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).create(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String createPublisher(Publisher publisher) {
		BookDAO bookDAO;
		try (Connection connection = getConnection()) {
			bookDAO = new BookDAO(connection);
			publisher.setId(new PublisherDAO(connection).create(publisher));
			addNewPublisherToBooks(publisher, bookDAO);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updateAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).update(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updateBook(Book book) {
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			new BookDAO(connection).update(book);
			completeBookUpdate(book, copiesDAO, loanDAO);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updateBorrower(Borrower borrower) {
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			loanDAO = new LoanDAO(connection);
			new BorrowerDAO(connection).update(borrower);
			completeBorrowerUpdate(borrower, loanDAO);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updateBranch(Branch branch) {
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			new BranchDAO(connection).update(branch);
			completeBranchUpdate(branch, copiesDAO, loanDAO);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updateGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).update(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String updatePublisher(Publisher publisher) {
		try (Connection connection = getConnection()) {
			updatePublisherBooks(publisher, new BookDAO(connection));
			new PublisherDAO(connection).update(publisher);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deleteAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).delete(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deleteBook(Book book) {
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			new BookDAO(connection).delete(book);
			for (Copies copies : book.getCopies())
				copiesDAO.delete(copies);
			for (Loan loan : book.getLoans())
				loanDAO.delete(loan);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deleteBorrower(Borrower borrower) {
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			loanDAO = new LoanDAO(connection);
			new BorrowerDAO(connection).delete(borrower);
			for (Loan loan : borrower.getLoans())
				loanDAO.delete(loan);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deleteBranch(Branch branch) {
		CopiesDAO copiesDAO;
		LoanDAO loanDAO;
		try (Connection connection = getConnection()) {
			copiesDAO = new CopiesDAO(connection);
			loanDAO = new LoanDAO(connection);
			for (Loan loan : branch.getLoans())
				loanDAO.delete(loan);
			for (Copies copies : branch.getCopies())
				copiesDAO.delete(copies);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deleteGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).delete(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	public String deletePublisher(Publisher publisher) {
		try (Connection connection = getConnection()) {
			new PublisherDAO(connection).delete(publisher);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return presentation.operationFailed;
		}
		return presentation.operationSucceeded;
	}

	private void completeBookUpdate(Book book, CopiesDAO copiesDAO, LoanDAO loanDAO)
			throws ClassNotFoundException, SQLException {
		ArrayList<Copies> oldCopies = copiesDAO.readAll().stream().filter(copies -> copies.getBookId() == book.getId())
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Loan> oldLoans = loanDAO.readAll().stream().filter(loan -> loan.getBookId() == book.getId())
				.collect(Collectors.toCollection(ArrayList::new));
		for (Copies copies : book.getCopies()) {
			if (oldCopies.contains(copies))
				copiesDAO.update(copies);
			else
				copiesDAO.create(copies);
		}
		for (Copies copies : oldCopies)
			if (!book.getCopies().contains(copies))
				copiesDAO.delete(copies);
		for (Loan loan : book.getLoans()) {
			if (oldLoans.contains(loan))
				loanDAO.update(loan);
			else
				loanDAO.create(loan);
		}
		for (Loan loan : oldLoans)
			if (!book.getLoans().contains(loan))
				loanDAO.delete(loan);
	}

	private void completeBorrowerUpdate(Borrower borrower, LoanDAO loanDAO)
			throws ClassNotFoundException, SQLException {
		ArrayList<Loan> oldLoans = loanDAO.readAll().stream().filter(loan -> loan.getCardNo() == borrower.getCardNo())
				.collect(Collectors.toCollection(ArrayList::new));
		for (Loan loan : borrower.getLoans()) {
			if (oldLoans.contains(loan))
				loanDAO.update(loan);
			else
				loanDAO.create(loan);
		}
		for (Loan loan : oldLoans)
			if (!borrower.getLoans().contains(loan))
				loanDAO.delete(loan);
	}

	private void completeBranchUpdate(Branch branch, CopiesDAO copiesDAO, LoanDAO loanDAO)
			throws ClassNotFoundException, SQLException {
		ArrayList<Copies> oldCopies = copiesDAO.readAll().stream()
				.filter(copies -> copies.getBranchId() == branch.getId())
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Loan> oldLoans = loanDAO.readAll().stream().filter(loan -> loan.getBranchId() == branch.getId())
				.collect(Collectors.toCollection(ArrayList::new));
		for (Copies copies : branch.getCopies()) {
			if (oldCopies.contains(copies))
				copiesDAO.update(copies);
			else
				copiesDAO.create(copies);
		}
		for (Copies copies : oldCopies)
			if (!branch.getCopies().contains(copies))
				copiesDAO.delete(copies);
		for (Loan loan : branch.getLoans()) {
			if (oldLoans.contains(loan))
				loanDAO.update(loan);
			else
				loanDAO.create(loan);
		}
		for (Loan loan : oldLoans)
			if (!branch.getLoans().contains(loan))
				loanDAO.delete(loan);
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

	public void addNewPublisherToBooks(Publisher publisher, BookDAO bookDAO)
			throws ClassNotFoundException, SQLException {
		ArrayList<Book> books = (ArrayList<Book>) getAllObjects(LMS.book);
		books = books.stream().filter(book -> publisher.getBookIds().contains(book.getId()))
				.collect(Collectors.toCollection(ArrayList::new));
		books.forEach(book -> book.setPubId(publisher.getId()));
		for (Book book : books)
			bookDAO.update(book);
	}

	public void updatePublisherBooks(Publisher newPublisher, BookDAO bookDAO)
			throws ClassNotFoundException, SQLException {
		Publisher oldPublisher = (Publisher) getObjectById(LMS.publisher, newPublisher.getId());
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
		for (Book book : booksToAdd)
			bookDAO.update(book);
		for (Book book : booksToRemove)
			bookDAO.update(book);
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
