package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
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

	public void create(Author author) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { author.getName() };
		int thisAuthorId = saveWithPk("INSERT INTO" + nativeTable + "(" + authorName + ") VALUES (?)", queryArgs);
		createRelations(author.getBookIds(), tblBookAuthor, authorId, bookId, thisAuthorId);
	}

	public void update(Author author) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { author.getName(), author.getId() };
		ArrayList<Integer> bookIds = getRelations(tblBookAuthor, authorId, bookId, author.getId());
		save("UPDATE" + nativeTable + " SET " + authorName + " = ? WHERE " + authorId + " = ?", queryArgs);
		for (int thisBookId : bookIds) {
			queryArgs = new Object[] { thisBookId, author.getId() };
			save("INSERT INTO" + tblBookAuthor + "(" + bookId + ", " + authorId + ") VALUES (?, ?)", queryArgs);
		}
	}

	public void delete(Author author) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { author.getId() };
		save("DELETE FROM " + tblAuthor + " WHERE " + authorId + " = ?", queryArgs);
	}

	@Override
	public ArrayList<Author> extractData(ResultSet resultSet) throws SQLException {
		Author author;
		ArrayList<Author> authors = new ArrayList<Author>();
		while (resultSet.next()) {
			author = new Author();
			author.setId(resultSet.getInt(authorId));
			author.setName(resultSet.getString(authorName));
			author.setBookIds(getRelations(tblBookAuthor, authorId, bookId, author.getId()));
			authors.add(author);
		}
		return authors;
	}
}
