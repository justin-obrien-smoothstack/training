package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.HasIntegerId;

/**
 * @author Justin O'Brien
 */
public class GenreDAO extends LMSDAO<Genre> implements HasIntegerId {

	/**
	 * @param connnection
	 */
	public GenreDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblGenre;
	}

	public void create(Genre genre) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { genre.getName() };
		int thisGenreId = saveWithPk("INSERT INTO " + nativeTable + " (" + genreName + ") VALUES (?)", queryArgs);
		createRelations(genre.getBookIds(), tblBookGenre, genreId, bookId, thisGenreId);
	}

	public void update(Genre genre) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { genre.getName(), genre.getId() };
		save("UPDATE " + nativeTable + " SET " + genreName + " = ? WHERE " + genreId + " = ?", queryArgs);
		updateRelations(genre.getBookIds(), tblBookGenre, genreId, bookId, genre.getId());
	}

	public void delete(Genre genre) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { genre.getId() };
		save("DELETE FROM " + nativeTable + " WHERE " + genreId + " = ?", queryArgs);
	}

	@Override
	public ArrayList<Genre> extractData(ResultSet resultSet) throws SQLException {
		Genre genre;
		ArrayList<Genre> genres = new ArrayList<Genre>();
		while (resultSet.next()) {
			genre = new Genre();
			genre.setId(resultSet.getInt(genreId));
			genre.setName(resultSet.getString(genreName));
			genres.add(genre);
		}
		return genres;
	}
}
