package com.ss.training.lms.versiontwo.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import com.ss.training.lms.versiontwo.presentation.Presentation;

/**
 * Provides business logic for LMS functions available to users who are
 * administrators
 * 
 * @author Justin O'Brien
 */
public class AdminService extends LMSService {

	public ArrayList<LMSObject> getBranchesWithOverridableLoans(Borrower borrower) {
		return ((ArrayList<LMSObject>) getAllObjects(LMS.branch)).stream().filter(branch -> {
			for (Loan loan : ((Branch) branch).getLoans())
				if (loan.getCardNo() == borrower.getCardNo() && loan.getDueDate() != null
						&& (loan.getDateIn() == null || loan.getDateIn().isAfter(loan.getDueDate())))
					return true;
			return false;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	public String createAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).create(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String createGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).create(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String readAuthors() {
		StringBuilder output = new StringBuilder("\n");
		ArrayList<Author> authors = (ArrayList<Author>) getAllObjects(LMS.author);
		authors.stream().forEach(author -> {
			output.append("\nID number: " + author.getId());
			output.append("\nName: " + author.getName());
			output.append("\nBooks: ");
			getObjectsById(LMS.book, author.getBookIds()).stream()
					.forEach(book -> output.append("\n\t" + ((Book) book).getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String readBooks() {
		StringBuilder output = new StringBuilder();
		ArrayList<Book> books = (ArrayList<Book>) getAllObjects(LMS.book);
		books.stream().forEach(book -> {
			output.append("\nID number: " + book.getId());
			output.append("\nTitle: " + book.getTitle());
			output.append("\nAuthors: ");
			getObjectsById(LMS.author, book.getAuthorIds()).stream()
					.forEach(author -> output.append("\n\t" + ((Author) author).getDisplayName()));
			output.append("\nGenres: ");
			getObjectsById(LMS.genre, book.getGenreIds()).stream()
					.forEach(genre -> output.append("\n\t" + ((Genre) genre).getDisplayName()));
			output.append("\nPublisher: ");
			if (book.getPubId() != null)
				output.append(((Publisher) getObjectById(LMS.publisher, book.getPubId())).getName());
			output.append("\nBranches with copies: ");
			book.getCopies().stream().forEach(copies -> output.append("\n\t" + copies.getDisplayName()));
			output.append("\nLoans: ");
			book.getLoans().stream().forEach(loan -> output.append("\n\t" + loan.getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String readBorrowers() {
		StringBuilder output = new StringBuilder();
		ArrayList<Borrower> borrowers = (ArrayList<Borrower>) getAllObjects(LMS.borrower);
		borrowers.stream().forEach(borrower -> {
			output.append("\nCard number: " + borrower.getCardNo());
			output.append("\nName: ");
			appendIfNotNull(output, borrower.getName());
			output.append("\nAddress: ");
			appendIfNotNull(output, borrower.getAddress());
			output.append("\nPhone: ");
			appendIfNotNull(output, borrower.getPhone());
			output.append("\nLoans: ");
			borrower.getLoans().stream().forEach(loan -> output.append("\n\t" + loan.getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String readBranches() {
		StringBuilder output = new StringBuilder();
		ArrayList<Branch> branches = (ArrayList<Branch>) getAllObjects(LMS.branch);
		branches.stream().forEach(branch -> {
			output.append("\nID number: " + branch.getId());
			output.append("\nName: ");
			appendIfNotNull(output, branch.getName());
			output.append("\nAddress: ");
			appendIfNotNull(output, branch.getAddress());
			output.append("\nBooks: ");
			branch.getCopies().stream().forEach(copies -> output.append("\n\t" + copies.getDisplayName()));
			output.append("\nLoans: ");
			branch.getLoans().stream().forEach(loan -> output.append("\n\t" + loan.getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String readGenres() {
		StringBuilder output = new StringBuilder("\n");
		ArrayList<Genre> genres = (ArrayList<Genre>) getAllObjects(LMS.genre);
		genres.stream().forEach(genre -> {
			output.append("\nID number: " + genre.getId());
			output.append("\nName: " + genre.getDisplayName());
			output.append("\nBooks: ");
			getObjectsById(LMS.book, genre.getBookIds()).stream()
					.forEach(book -> output.append("\n\t" + ((Book) book).getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String readPublishers() {
		StringBuilder output = new StringBuilder();
		ArrayList<Publisher> publishers = (ArrayList<Publisher>) getAllObjects(LMS.publisher);
		publishers.stream().forEach(publisher -> {
			output.append("\nID number: " + publisher.getId());
			output.append("\nName: " + publisher.getName());
			output.append("\nAddress: ");
			appendIfNotNull(output, publisher.getAddress());
			output.append("\nPhone: ");
			appendIfNotNull(output, publisher.getPhone());
			output.append("\nBooks: ");
			getObjectsById(LMS.book, publisher.getBookIds()).stream()
					.forEach(book -> output.append("\n\t" + ((Book) book).getDisplayName()));
			output.append("\n");
		});
		return output.toString();
	}

	public String updateAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).update(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
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
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String updateGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).update(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String updatePublisher(Publisher publisher) {
		try (Connection connection = getConnection()) {
			updatePublisherBooks(publisher, new BookDAO(connection));
			new PublisherDAO(connection).update(publisher);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deleteAuthor(Author author) {
		try (Connection connection = getConnection()) {
			new AuthorDAO(connection).delete(author);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deleteBook(Book book) {
		try (Connection connection = getConnection()) {
			new BookDAO(connection).delete(book);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deleteBorrower(Borrower borrower) {
		try (Connection connection = getConnection()) {
			new BorrowerDAO(connection).delete(borrower);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deleteBranch(Branch branch) {
		try (Connection connection = getConnection()) {
			new BranchDAO(connection).delete(branch);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deleteGenre(Genre genre) {
		try (Connection connection = getConnection()) {
			new GenreDAO(connection).delete(genre);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	public String deletePublisher(Publisher publisher) {
		try (Connection connection = getConnection()) {
			new PublisherDAO(connection).delete(publisher);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Presentation.operationFailed;
		}
		return Presentation.operationSucceeded;
	}

	private void appendIfNotNull(StringBuilder stringBuilder, String string) {
		stringBuilder.append(string == null ? "" : string);
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

	public String overrideDueDate(Borrower borrower, Loan loan) {
		loan.setDueDate(loan.getDueDate().plusDays(7));
		borrower.getLoans().stream().forEach(thisLoan -> {
			if (thisLoan.equals(loan))
				thisLoan.setDueDate(loan.getDueDate());
		});
		return updateBorrower(borrower);
	}

}
