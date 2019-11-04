package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.TipoQuarto;


public final class DaoTipoQuarto implements DaoBase<TipoQuarto> {

	@Override
	public TipoQuarto insert(TipoQuarto object) {
		
		String query = " insert into tipoquarto (nome)" + " values (?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public TipoQuarto update(TipoQuarto object) {
		String query = " update tipoquarto set nome = ? where id = ?";
		
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
		String query = "DELETE FROM tipoquarto WHERE id = ?";
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
	public TipoQuarto findById(int id) {
		String query = " select * from tipoquarto where id = ?";
		TipoQuarto tipoQuarto = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
				tipoQuarto = new TipoQuarto();
				tipoQuarto.setId(rs.getInt("id"));
				tipoQuarto.setNome(rs.getString("nome"));
				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return tipoQuarto;
	}

	@Override
	public List<TipoQuarto> findAll() {
		String query = " select * from tipoquarto";
		List<TipoQuarto> quartos = new ArrayList<TipoQuarto>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				TipoQuarto quarto = new TipoQuarto();
				quarto.setId(rs.getInt("id"));
				quarto.setNome(rs.getString("nome"));
				quartos.add(quarto);
			}			
			
			statment.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return quartos;
	}

}
