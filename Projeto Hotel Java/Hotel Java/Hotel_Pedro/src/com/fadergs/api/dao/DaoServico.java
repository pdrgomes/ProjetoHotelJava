package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.Servicos;


public final class DaoServico implements DaoBase<Servicos> {

	@Override
	public Servicos insert(Servicos object) {
		
		String query = " insert into servico (nome, valor)" + " values (?,?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNomeServico());
			preparedStmt.setDouble(2, object.getPrecoServico());
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("servico", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return object;
	}

	@Override
	public Servicos update(Servicos object) {
		String query = " update servico set nome = ?, valor = ? where id = ?";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNomeServico() );
			preparedStmt.setDouble(2, object.getPrecoServico());
			preparedStmt.setInt(3, object.getId());
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
		String query = "DELETE FROM servico WHERE id = ?";
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
	public Servicos findById(int id) {
		String query = " select * from servico where id = ?";
		Servicos servicos = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
				servicos = new Servicos();
				servicos.setId(rs.getInt("id"));
				servicos.setNomeServico(rs.getString("nome"));
				servicos.setPrecoServico(rs.getDouble("valor"));
				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return servicos;
	}

	@Override
	public List<Servicos> findAll() {
		String query = " select * from servico";
		List<Servicos> servicos = new ArrayList<Servicos>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				Servicos servico = new Servicos();
				servico.setId(rs.getInt("id"));
				servico.setNomeServico(rs.getString("nome"));
				servico.setPrecoServico(rs.getDouble("valor"));
				servicos.add(servico);
			}			
			
			statment.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return servicos;
	}

}
