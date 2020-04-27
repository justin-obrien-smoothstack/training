package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for DAO objects for library management system
 * 
 * @author Justin O'Brien
 */
public abstract class LMSDAO<T> {

	protected String nativeTable;
	public Connection connection = null;

	/**
	 * @param connnection A connection to the SQL server
	 */
	public LMSDAO(Connection connnection) {
		this.connection = connnection;
	}

	protected String tblBranch = "tbl_library_branch", tblCopies = "tbl_book_copies", tblLoans = "tbl_book_loans",
			tblBook = "tbl_book", tblAuthor = "tbl_author", tblBookAuthor = "tbl_book_authors", tblBookGenre = "tbl_book_genres";
	protected String bookId = "bookId", branchId = "branchId", authorId = "authorId", genreId = "genre_id", pubId = "pubId", branchName = "branchName",
			title = "title", authorName = "authorName", branchAddress = "branchAddress", noOfCopies = "noOfCopies",
			cardNo = "cardNo", dateOut = "dateOut", dueDate = "dueDate", dateIn = "dateIn";

	protected ArrayList<Integer> getRelations(String table, String selfColumn, String otherColumn, Object selfId)
			throws SQLException {
		ArrayList<Integer> relations = new ArrayList<Integer>();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM " + table + " WHERE " + selfColumn + " = ?");
		ResultSet resultSet;
		preparedStatement.setObject(1, selfId);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next())
			relations.add(resultSet.getInt(otherColumn));
		return relations;
	}

	public void save(String sqlQuery, Object[] queryArgs) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				preparedStatement.setObject(i + 1, queryArgs[i]);
			}
		}
		preparedStatement.executeUpdate();
	}

	public Integer saveWithPk(String sqlQuery, Object[] queryArgs) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
				PreparedStatement.RETURN_GENERATED_KEYS);
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				preparedStatement.setObject(i + 1, queryArgs[i]);
			}
		}
		preparedStatement.executeUpdate();
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next())
			return resultSet.getInt(1);
		return null;
	}

	public ArrayList<T> read(String sqlQuery, Object[] queryArgs) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				preparedStatement.setObject(i + 1, queryArgs[i]);
			}
		}
		return extractData(preparedStatement.executeQuery());
	}

	public ArrayList<T> readAll() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM " + nativeTable + ";", null);
	}

	public abstract ArrayList<T> extractData(ResultSet resultSet) throws SQLException;
}
