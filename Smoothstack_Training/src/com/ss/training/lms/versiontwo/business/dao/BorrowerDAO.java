package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Justin O'Brien
 */
public class BorrowerDAO extends LMSDAO {

	/**
	 * @param connnection
	 */
	public BorrowerDAO(Connection connnection) {
		super(connnection);
		// TODO Auto-generated constructor stub
	}

	public void create() {

	}

	public void update() {

	}

	public void delete()
	{

	}
	
	@Override
	public ArrayList extractData(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
