package com.ss.training.lms.versiontwo.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.BranchDAO;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.business.dao.LMSDAO;
import com.ss.training.lms.versiontwo.business.dao.LoansDAO;
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

	public Branch completeBranchInfo(Branch branch, ArrayList<Copies> copieses, ArrayList<Loan> loans) {
		HashMap<Integer, Integer> branchCopies = branch.getCopies();
		copieses.stream().filter(copies -> copies.getBranchId() == branch.getId())
				.forEach(copies -> branchCopies.put(copies.getBookId(), copies.getCopies()));

	}

	public ArrayList<Branch> getAllBranches() {
		ArrayList<Branch> branches;
		ArrayList<Copies> copieses;
		ArrayList<Loan> loans;
		HashMap<String, Integer> copies;
		HashMap<String, Object> loan;
		ArrayList<HashMap<String, Integer>> copiesesMap;
		ArrayList<HashMap<String, Object>> loansMap;
		try (Connection connection = getConnection()) {
			BranchDAO branchDao = new BranchDAO(connection);
			CopiesDAO copiesDao = new CopiesDAO(connection);
			LoansDAO loansDao = new LoansDAO(connection);
			branches = branchDao.readAll();
			copieses = copiesDao.readAll();
			loans = loansDao.readAll();
			copieses.stream().filter(copies -> copies.getBranchId())
			//
			/*
			 * copieses = new ArrayList<HashMap<String, Integer>>(); loans = new
			 * ArrayList<HashMap<String, Object>>(); while (copiesResultSet.next()) { copies
			 * = new HashMap<String, Integer>(); copies.put(bookId,
			 * copiesResultSet.getInt(bookId)); copies.put(noOfCopies,
			 * copiesResultSet.getInt(noOfCopies)); copieses.add(copies); } while
			 * (loanResultSet.next()) { loan = new HashMap<String, Object>();
			 * loan.put(bookId, loanResultSet.getInt(bookId)); loan.put(cardNo,
			 * loanResultSet.getInt(cardNo)); loan.put(dateOut,
			 * loanResultSet.getTimestamp(dateOut).toLocalDateTime()); loan.put(dueDate,
			 * loanResultSet.getTimestamp(dueDate).toLocalDateTime()); loan.put(dateIn,
			 * loanResultSet.getTimestamp(dateIn).toLocalDateTime()); loans.add(loan); }
			 * branch.setCopies(copieses); branch.setLoans(loans); branches.add(branch);
			 */
			//
		} catch (Exception e) {
			printRetrievalErrorMessage(LMS.branches);
			e.printStackTrace();
		}
	}

	public ArrayList<LMSObject> getAllObjects(String objectType) {
		LMSDAO<?> dao = null;
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
			return (ArrayList<LMSObject>) dao.readAll();
		} catch (Exception e) {
			System.out.println("There was an error while attempting to retrieve objects from database.");
			e.printStackTrace();
		}
		return new ArrayList<LMSObject>();
	}
}