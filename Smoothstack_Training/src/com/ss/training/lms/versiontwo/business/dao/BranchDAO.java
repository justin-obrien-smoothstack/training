package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Branch;

/**
 * @author Justin O'Brien
 */
public class BranchDAO extends LMSDAO<Branch> {

	/**
	 * @param connection A connection to the SQL server
	 */
	public BranchDAO(Connection connection) {
		super(connection);
		this.nativeTable = tblBranch;
	}

	@Override
	public ArrayList<Branch> extractData(ResultSet branchResultSet) throws SQLException {
		Branch branch;
		ArrayList<Branch> branches = new ArrayList<Branch>();
		while (branchResultSet.next()) {
			branch = new Branch();
			branch.setId(branchResultSet.getInt(branchId));
			branch.setName(branchResultSet.getString(branchName));
		}
		return branches;
	}
}
