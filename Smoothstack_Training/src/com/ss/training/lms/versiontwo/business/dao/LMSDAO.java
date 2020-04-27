package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

/**
 * Abstract class for DAO objects for library management system
 * 
 * @author Justin O'Brien
 */
public abstract class LMSDAO<T> {

	public Connection conn = null;

	public LMSDAO(Connection conn) {
		this.conn = conn;
	}

	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int index = 1;
			for (Object o : vals) {
				pstmt.setObject(index, o);
				index++;
			}
		}
		pstmt.executeUpdate();
	}

	public Integer saveWithPK(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.);
		if (vals != null) {
			int index = 1;
			for (Object o : vals) {
				pstmt.setObject(index, o);
				index++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}

	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int index = 1;
			for (Object o : vals) {
				pstmt.setObject(index, o);
				index++;
			}
		}
		return extractData(pstmt.executeQuery());
	}

	public abstract List<T> extractData(ResultSet rs) throws SQLException;
}
