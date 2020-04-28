package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Publisher;

/**
 * @author Justin O'Brien
 */
public class PublisherDAO extends LMSDAO<Publisher> {

	/**
	 * @param connnection
	 */
	public PublisherDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblPublisher;
	}

	public int create(Publisher publisher) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { publisher.getName(), publisher.getAddress(), publisher.getPhone() };
		return saveWithPk("INSERT INTO " + nativeTable + " (" + publisherName + ", " + publisherAddress + ", "
				+ publisherPhone + ") VALUES (?, ?, ?)", queryArgs);
	}

	public void update(Publisher publisher) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getId() };
		save("UPDATE " + nativeTable + " SET " + publisherName + " = ?, " + publisherAddress + " = ?, " + publisherPhone
				+ " = ? WHERE " + publisherId + " = ?", queryArgs);
	}

	public void delete(Publisher publisher) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { publisher.getId() };
		save("DELETE FROM " + nativeTable + " WHERE " + publisherId + " = ?", queryArgs);
	}

	@Override
	public ArrayList<Publisher> extractData(ResultSet resultSet) throws SQLException {
		Publisher publisher;
		ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		while (resultSet.next()) {
			publisher = new Publisher();
			publisher.setId(resultSet.getInt(publisherId));
			publisher.setName(resultSet.getString(publisherName));
			publisher.setAddress(resultSet.getString(publisherAddress));
			publisher.setPhone(resultSet.getString(publisherPhone));
			publishers.add(publisher);
		}
		return publishers;
	}

}
