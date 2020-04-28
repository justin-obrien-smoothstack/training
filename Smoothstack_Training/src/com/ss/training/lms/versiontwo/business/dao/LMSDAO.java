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
			tblBook = "tbl_book", tblAuthor = "tbl_author", tblPublisher = "tbl_publisher", tblGenre = "tbl_genre",
			tblBorrower = "tbl_borrower", tblBookAuthor = "tbl_book_authors", tblBookGenre = "tbl_book_genres";
	protected String bookId = "bookId", branchId = "branchId", authorId = "authorId", genreId = "genre_id",
			publisherId = "publisherId", pubId = "pubId", branchName = "branchName", title = "title",
			authorName = "authorName", genreName = "genre_name", publisherName = "publisherName",
			publisherAddress = "publisherAddress", publisherPhone = "publisherPhone", branchAddress = "branchAddress",
			name = "name", address = "address", phone = "phone", noOfCopies = "noOfCopies", cardNo = "cardNo",
			dateOut = "dateOut", dueDate = "dueDate", dateIn = "dateIn";

	/**
	 * @param table
	 * @param selfColumn
	 * @param otherColumn
	 * @param selfId
	 * @return ID numbers of each object associated with otherColumn that are
	 *         related to the object associated with selfColumn with ID number
	 *         selfId
	 * @throws SQLException
	 */
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

	protected void createRelations(ArrayList<Integer> otherIds, String table, String selfColumn, String otherColumn,
			Object selfId) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { null, selfId };
		for (int otherId : otherIds) {
			queryArgs[0] = otherId;
			save("INSERT INTO" + table + "(" + otherColumn + ", " + selfColumn + ") VALUES (?, ?)", queryArgs);
		}
	}

	protected void updateRelations(ArrayList<Integer> newRelations, String table, String selfColumn, String otherColumn,
			Object selfId) throws SQLException, ClassNotFoundException {
		Object[] queryArgs = { null, selfId };
		ArrayList<Integer> oldRelations = getRelations(table, selfColumn, otherColumn, selfId);
		for (int oldOtherId : oldRelations)
			if (!newRelations.contains(oldOtherId)) {
				queryArgs[0] = oldOtherId;
				save("DELETE FROM " + table + " WHERE " + otherColumn + " = ? AND " + selfColumn + " = ?", queryArgs);
			}
		for(int newOtherId : newRelations)
			if(!oldRelations.contains(newOtherId)) {
				queryArgs[0] = newOtherId;
				save("INSERT INTO" + table + "(" + otherColumn + ", " + selfColumn + ") VALUES (?, ?)", queryArgs);
			}
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
		return read("SELECT * FROM " + nativeTable, null);
	}

	public abstract ArrayList<T> extractData(ResultSet resultSet) throws SQLException;
}
