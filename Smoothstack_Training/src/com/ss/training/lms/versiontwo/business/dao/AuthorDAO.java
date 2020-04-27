package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Author;

/**
 * @author Justin O'Brien
 */
public class AuthorDAO extends LMSDAO<Author> {

	/**
	 * @param connnection
	 */
	public AuthorDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblAuthor;
	}

	@Override
	public ArrayList<Author> extractData(ResultSet resultSet) throws SQLException {
		 Author author;
		 ArrayList<Author> authors = new ArrayList<Author>();
		 ResultSet authorBooksResultSet;
		 PreparedStatement authorBooksQuery = connection.prepareStatement("SELECT * from tbl_book_authors where authorId = ?");
		 while(resultSet.next()) {
			 author = new Author();
			 author.setId(resultSet.getInt(authorId));
			 author.setName(resultSet.getString(authorName));
			 authorBooksQuery.setInt(1, author.getId());
			 authorBooksResultSet = authorBooksQuery.executeQuery(); 
			 while(authorBooksResultSet.next()) {
				 author.getBookIds().add(authorBooksResultSet.getInt(bookId));
			 }
		 }
		 return authors;
	}
}
