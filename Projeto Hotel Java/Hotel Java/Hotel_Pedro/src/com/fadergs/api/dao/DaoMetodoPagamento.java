package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.dao.DaoMetodoPagamento;
import com.fadergs.api.model.MetodoPagamento;


public final class DaoMetodoPagamento implements DaoBase<MetodoPagamento> {

	@Override
	public MetodoPagamento insert(MetodoPagamento object) {
		
		String query = " insert into metodopagamento (nome)" + " values (?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("metodopagamento", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public MetodoPagamento update(MetodoPagamento object) {
		String query = " update metodopagamento set nome = ? where id = ?";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM metodopagamento WHERE id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.getStackTrace();
		}

	}

	@Override
	public MetodoPagamento findById(int id) {
		String query = " select * from metodopagamento where id = ?";
		MetodoPagamento metodPagamento = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
				metodPagamento = new MetodoPagamento();
				metodPagamento.setId(rs.getInt("id"));
				metodPagamento.setNome(rs.getString("nome"));
				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return metodPagamento;
	}

	@Override
	public List<MetodoPagamento> findAll() {
		String query = " select * from metodopagamento";
		List<MetodoPagamento> hospedes = new ArrayList<MetodoPagamento>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				MetodoPagamento hospede = new MetodoPagamento();
				hospede.setId(rs.getInt("id"));
				hospede.setNome(rs.getString("nome"));
				hospedes.add(hospede);
			}			
			
			statment.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return hospedes;
	}



}
