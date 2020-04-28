package com.ss.training.lms.versiontwo.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.AuthorDAO;
import com.ss.training.lms.versiontwo.business.dao.BookDAO;
import com.ss.training.lms.versiontwo.business.dao.BorrowerDAO;
import com.ss.training.lms.versiontwo.business.dao.BranchDAO;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.business.dao.GenreDAO;
import com.ss.training.lms.versiontwo.business.dao.LMSDAO;
import com.ss.training.lms.versiontwo.business.dao.LoanDAO;
import com.ss.training.lms.versiontwo.business.dao.PublisherDAO;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.HasIntegerId;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;
import com.ss.training.lms.versiontwo.object.Publisher;
import com.ss.training.lms.versiontwo.presentation.Presentation;

/**
 * @author Justin O'Brien
 */
public class LMSService {

	protected Presentation presentation = Presentation.getInstance();

	protected void printRetrievalErrorMessage(String objectType) {
		System.out.println("There was an error while attempting to retrieve " + objectType + " from the database.");
	}

	protected Connection getConnection() {
		String propertiesPath = "resources/config/LMS.properties";
		Connection connection = null;
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(propertiesPath)) {
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println("Error: Unable to load properties from " + propertiesPath);
			e.printStackTrace();
		}
		try {
			Class.forName((String) properties.get("driver"));
			connection = DriverManager.getConnection((String) properties.get("url"), (String) properties.get("user"),
					(String) properties.get("password"));
			connection.setAutoCommit(Boolean.FALSE);
		} catch (Exception e) {
			System.out.println("Error: Unable to connect to database.");
			e.printStackTrace();
		}
		return connection;
	}

	protected LMSDAO<?> getDAO(Connection connection, String objectType) throws SQLException {
		switch (objectType) {
		case LMS.author:
		case LMS.authors:
			return new AuthorDAO(connection);
		case LMS.book:
		case LMS.books:
			return new BookDAO(connection);
		case LMS.borrower:
		case LMS.borrowers:
			return new BorrowerDAO(connection);
		case LMS.branch:
		case LMS.branches:
			return new BranchDAO(connection);
		case LMS.copies:
		case LMS.copieses:
			return new CopiesDAO(connection);
		case LMS.genre:
		case LMS.genres:
			return new GenreDAO(connection);
		case LMS.loan:
		case LMS.loans:
			return new LoanDAO(connection);
		case LMS.publisher:
		case LMS.publishers:
			return new PublisherDAO(connection);
		}
		return null;
	}

	private ArrayList<Book> completeBookInfo(ArrayList<Book> books) {
		ArrayList<Copies> copieses = new ArrayList<Copies>();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		try (Connection connection = getConnection()) {
			copieses.addAll(new CopiesDAO(connection).readAll());
			loans.addAll(new LoanDAO(connection).readAll());
		} catch (Exception e) {
			printRetrievalErrorMessage(LMS.loans + " and " + LMS.copieses + " for a " + LMS.branch);
			e.printStackTrace();
		}
		books.stream().forEach(book -> {
			copieses.stream().filter(copies -> copies.getBookId() == book.getId())
					.forEach(copies -> book.getCopies().add(copies));
			loans.stream().filter(loan -> loan.getBookId() == book.getId()).forEach(loan -> book.getLoans().add(loan));
		});
		return books;
	}

	private ArrayList<Branch> completeBranchInfo(ArrayList<Branch> branches) {
		ArrayList<Copies> copieses = new ArrayList<Copies>();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		try (Connection connection = getConnection()) {
			copieses.addAll(new CopiesDAO(connection).readAll());
			loans.addAll(new LoanDAO(connection).readAll());
		} catch (Exception e) {
			printRetrievalErrorMessage(LMS.loans + " and " + LMS.copieses + " for a " + LMS.branch);
			e.printStackTrace();
		}
		branches.stream().forEach(branch -> {
			copieses.stream().filter(copies -> copies.getBookId() == branch.getId())
					.forEach(copies -> branch.getCopies().add(copies));
			loans.stream().filter(loan -> loan.getBranchId() == branch.getId())
					.forEach(loan -> branch.getLoans().add(loan));
		});
		return branches;
	}

	private ArrayList<Borrower> addLoansToBorrowers(ArrayList<Borrower> borrowers) {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		try (Connection connection = getConnection()) {
			loans.addAll(new LoanDAO(connection).readAll());
		} catch (Exception e) {
			printRetrievalErrorMessage(LMS.loans + " for a " + LMS.borrower);
			e.printStackTrace();
		}
		borrowers.stream().forEach(borrower -> {
			loans.stream().filter(loan -> loan.getCardNo() == borrower.getCardNo())
					.forEach(loan -> borrower.getLoans().add(loan));
		});
		return borrowers;
	}

	private ArrayList<Publisher> addBooksToPublishers(ArrayList<Publisher> publishers) {
		ArrayList<Book> books = (ArrayList<Book>) getAllObjects(LMS.book);
		publishers.stream().forEach(publisher -> {
			books.stream().filter(book -> book.getPubId() == publisher.getId())
					.forEach(book -> publisher.getBookIds().add(book.getId()));
		});
		return publishers;
	}

	public ArrayList<?> getAllObjects(String objectType) {
		LMSDAO<?> dao = null;
		ArrayList allObjects = new ArrayList();
		try (Connection connection = getConnection()) {
			dao = getDAO(connection, objectType);
			allObjects = (ArrayList<LMSObject>) dao.readAll();
			switch (objectType) {
			case LMS.borrower:
			case LMS.borrowers:
				allObjects = addLoansToBorrowers(allObjects);
				break;
			case LMS.book:
			case LMS.books:
				allObjects = completeBookInfo(allObjects);
			case LMS.branch:
			case LMS.branches:
				allObjects = completeBranchInfo(allObjects);
				break;
			case LMS.publisher:
			case LMS.publishers:
				allObjects = addBooksToPublishers(allObjects);
				break;
			}
		} catch (Exception e) {
			printRetrievalErrorMessage(objectType);
			e.printStackTrace();
		}
		return allObjects;
	}

	public HasIntegerId getObjectById(String objectType, int id) {
		ArrayList<HasIntegerId> allObjects = (ArrayList<HasIntegerId>) getAllObjects(objectType);
		for (HasIntegerId object : allObjects)
			if (object.getId() == id)
				return object;
		return null;
	}

	public ArrayList<?> getObjectsById(String objectType, List<Integer> ids) {
		ArrayList<HasIntegerId> allObjects = (ArrayList<HasIntegerId>) getAllObjects(objectType);
		return allObjects.stream().filter(object -> ids.contains(object.getId()))
				.collect(Collectors.toCollection(ArrayList::new));
	}
}