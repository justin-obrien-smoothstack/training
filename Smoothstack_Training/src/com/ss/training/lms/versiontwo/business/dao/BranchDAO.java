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

	public void update(Branch branch) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = {branch.getName(), branch.getAddress(), branch.getId()};
		save("UPDATE tbl_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?", queryArgs);
	}

	@Override
	public ArrayList<Branch> extractData(ResultSet resultSet) throws SQLException {
		Branch branch;
		ArrayList<Branch> branches = new ArrayList<Branch>();
		while (resultSet.next()) {
			branch = new Branch();
			branch.setId(resultSet.getInt(branchId));
			branch.setName(resultSet.getString(branchName));
			branches.add(branch);
		}
		return branches;
	}
}
