package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Book;

/**
 * @author Justin O'Brien
 */
public class BookDAO extends LMSDAO<Book> {

	/**
	 * @param connnection
	 */
	public BookDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblBook;
	}

	@Override
	public ArrayList<Book> extractData(ResultSet resultSet) throws SQLException {
		Book book;
		ArrayList<Book> books = new ArrayList<Book>();
		while (resultSet.next()) {
			book = new Book();
			book.setId(resultSet.getInt(bookId));
			book.setTitle(resultSet.getString(title));
			book.setPubId(resultSet.getInt(pubId));
			book.setAuthorIds(getRelations(tblBookAuthor, bookId, authorId, book.getId()));
			book.setGenreIds(getRelations(tblBookGenre, bookId, genreId, book.getId()));
			books.add(book);
		}
		return books;
	}
}
