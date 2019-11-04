package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fadergs.api.connection.ConnectionMySql;

public class DaoUtils {
	public static int getIdentity(String table, String field) {
		String query = " select max("+field+") from " + table;
		int result = 0;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
