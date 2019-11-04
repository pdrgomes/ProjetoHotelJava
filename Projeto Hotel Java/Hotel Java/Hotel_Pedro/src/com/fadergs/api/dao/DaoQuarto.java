package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.Quarto;



public final class DaoQuarto implements DaoBase<Quarto> {

	@Override
	public Quarto insert(Quarto object) {
		
		String query = " insert into quarto (numero, valor, idTipoQuarto)" + " values (?,?,?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			
			preparedStmt.setInt(1, object.getNumQuarto());
			preparedStmt.setDouble(2, object.getPrecoQuarto());
			preparedStmt.setInt(3, object.getTipoQuarto().getId());
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("quarto", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public Quarto update(Quarto object) {
		String query = " update quarto set STATUS_QUARTO = 1, numero = ?, valor = ?, idTipoQuarto = ? where id = ?";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, object.getNumQuarto());
			preparedStmt.setDouble(2, object.getPrecoQuarto());
			preparedStmt.setInt(3, object.getTipoQuarto().getId());
			preparedStmt.setInt(4, object.getId());
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
		String query = "DELETE FROM quarto WHERE id = ?";
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
	public Quarto findById(int id) {
		String query = " select * from quarto where id = ?";
		Quarto quarto = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
				quarto = new Quarto();
				quarto.setId(rs.getInt("id"));
				quarto.setNumQuarto(rs.getInt("numero"));
				quarto.setPrecoQuarto(rs.getDouble("VALOR"));
				quarto.setTipoQuarto(DaoSupplier.getDaoTipoQuarto().findById(rs.getInt("idTipoQuarto")));
				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return quarto;
	}

	@Override
	public List<Quarto> findAll() {
		String query = " select * from quarto";
		List<Quarto> quartos = new ArrayList<Quarto>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				Quarto quarto = findById(rs.getInt("id"));
				
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
