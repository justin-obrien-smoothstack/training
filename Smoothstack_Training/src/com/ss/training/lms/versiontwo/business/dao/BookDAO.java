package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Copies;

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

	public void create(Book book) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { book.getTitle(), book.getPubId() };
		int thisBookId = saveWithPk("INSERT INTO " + nativeTable + " (" + title + ", " + pubId + ") VALUES (?,?)",
				queryArgs);
		createRelations(book.getAuthorIds(), tblBookAuthor, bookId, authorId, thisBookId);
		createRelations(book.getGenreIds(), tblBookGenre, bookId, genreId, thisBookId);
	}

	public void update(Book book) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { book.getTitle(), book.getPubId(), book.getId() };
		save("UPDATE" + nativeTable + " SET " + title + " = ?, " + pubId + " = ? WHERE " + bookId + " = ?", queryArgs);
		updateRelations(book.getAuthorIds(), tblBookAuthor, bookId, authorId, book.getId());
		updateRelations(book.getGenreIds(), tblBookGenre, bookId, genreId, book.getId());
	}

	public void delete(Book book) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { book.getId() };
		save("DELETE FROM " + tblBook + " WHERE " + bookId + " = ?", queryArgs);
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
