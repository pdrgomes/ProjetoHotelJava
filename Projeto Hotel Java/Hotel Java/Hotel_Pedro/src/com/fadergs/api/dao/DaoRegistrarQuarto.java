package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.Compra;


public final class DaoRegistrarQuarto implements DaoBase<Compra> {

	@Override
	public Compra insert(Compra object) {
		
		String query = " insert into comprar (ID_HOSPEDE, ID_QUARTO)" + " values (?,?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setObject(1, object.getHospede().getId());
			preparedStmt.setObject(2, object.getQuarto().getId());	
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("comprar", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public Compra update(Compra object) {
		String query = " update comprar set nome = ? where id = ?";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, object.getId());
//			preparedStmt.setString(2, object.getNomeServico() );
//			preparedStmt.setDouble(3, object.getPrecoServico());
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
		String query = "DELETE FROM comprar WHERE id = ?";
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
	public Compra findById(int id) {
		String query = " select * from comprar where id = ?";
		Compra quartos = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
	
				quartos = new Compra();
				quartos.setId(rs.getInt("id"));
				quartos.setHospede(DaoSupplier.getDaoHospede().findById(rs.getInt("ID_HOSPEDE")));
				quartos.setIdQuarto(DaoSupplier.getDaoQuarto().findById(rs.getInt("ID_QUARTO")));

				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return quartos;
	}

	@Override
	public List<Compra> findAll() {
		String query = " select id from comprar";
		List<Compra> quartos = new ArrayList<Compra>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				Compra quarto = findById(rs.getInt("id"));
				quartos.add(quarto);
			}			
			
			statment.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return quartos;
	}
	
	public List<Compra> findComprasByIdQuarto(int idQuarto) {
		String query = " select id from comprar where id_quarto = ?";
		List<Compra> quartos = new ArrayList<Compra>();
		
		PreparedStatement statment = null;
		try {
			statment = ConnectionMySql.getConn().prepareStatement(query);
			statment.setInt(1, idQuarto);
			ResultSet rs =  statment.executeQuery();
			
			while (rs.next()) {
				Compra quarto = findById(rs.getInt("id"));
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
