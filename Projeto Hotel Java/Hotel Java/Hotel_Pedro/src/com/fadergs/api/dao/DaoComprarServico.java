package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.ComprarServico;



public final class DaoComprarServico implements DaoBase<ComprarServico> {

	@Override
	public ComprarServico insert(ComprarServico object) {
		
		String query = " insert into comprarservico (ID_COMPRA, ID_SERVICO)" + " values (?,?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setObject(1, object.getCompra().getId());
			preparedStmt.setObject(2, object.getServico().getId());	
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("comprarservico", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public ComprarServico update(ComprarServico object) {
		String query = " update comprarservico set nome = ? where id = ?";
		
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
		String query = "DELETE FROM comprarservico WHERE id = ?";
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
	public ComprarServico findById(int id) {
		String query = " select * from comprarservico where id = ?";
		ComprarServico quartos = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
	
				quartos = new ComprarServico();
				quartos.setId(rs.getInt("id"));
				quartos.setCompra(DaoSupplier.getDaoRegistrarQuarto().findById(rs.getInt("ID_COMPRA")));
				quartos.setServico(DaoSupplier.getDaoServico().findById(rs.getInt("ID_SERVICO")));

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
	public List<ComprarServico> findAll() {
		String query = " select * from comprarservico";
		List<ComprarServico> quartos = new ArrayList<ComprarServico>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				ComprarServico quarto = new ComprarServico();
				quarto.setId(rs.getInt("id"));
				quarto.setCompra(DaoSupplier.getDaoRegistrarQuarto().findById(rs.getInt("ID_COMPRA")));
				quarto.setServico(DaoSupplier.getDaoServico().findById(rs.getInt("ID_SERVICO")));

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
