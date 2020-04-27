package com.ss.training.lms.versiontwo.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.BranchDAO;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.business.dao.LMSDAO;
import com.ss.training.lms.versiontwo.business.dao.LoanDAO;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;

/**
 * @author Justin O'Brien
 */
public class LMSService {

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

	public void completeBranchInfo(ArrayList<Branch> branches) {
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
			copieses.stream().filter(copies -> copies.getBranchId() == branch.getId())
					.forEach(copies -> branch.getCopies().add(copies));
			loans.stream().filter(loan -> loan.getBranchId() == branch.getId())
					.forEach(loan -> branch.getLoans().add(loan));
		});
	}

	public ArrayList<?> getAllObjects(String objectType) {
		LMSDAO<?> dao = null;
		ArrayList allObjects = new ArrayList();
		try (Connection connection = getConnection()) {
			switch (objectType) {
			case LMS.author:
			case LMS.authors:
				break;
			case LMS.book:
			case LMS.books:
				break;
			case LMS.borrower:
			case LMS.borrowers:
				break;
			case LMS.branch:
			case LMS.branches:
				dao = new BranchDAO(connection);
				break;
			case LMS.copies:
			case LMS.copieses:
				break;
			case LMS.genre:
			case LMS.genres:
				break;
			case LMS.loan:
			case LMS.loans:
				break;
			case LMS.publisher:
			case LMS.publishers:
				break;
			}
			allObjects = (ArrayList<LMSObject>) dao.readAll();
		} catch (Exception e) {
			System.out.println("There was an error while attempting to retrieve objects from database.");
			e.printStackTrace();
		}
		switch (objectType) {
		case LMS.book:
		case LMS.books:
			break;
		case LMS.borrower:
		case LMS.borrowers:
			break;
		case LMS.branch:
		case LMS.branches:
			completeBranchInfo(allObjects);
			break;
		}
		return allObjects;
	}
}