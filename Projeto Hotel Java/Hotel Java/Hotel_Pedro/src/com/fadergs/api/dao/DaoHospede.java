package com.fadergs.api.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fadergs.api.connection.ConnectionMySql;
import com.fadergs.api.model.Hospede;










public final class DaoHospede implements DaoBase<Hospede> {

	public Hospede insert(Hospede object){
		
		String query = " insert into hospede (nome, idade, telefone, email, idpagamento)" + " values (?,?,?,?,?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getIdade());
			preparedStmt.setString(3, object.getTelefone());
			preparedStmt.setString(4, object.getEmail());
			preparedStmt.setObject(5, object.getMetPagamento().getId());
			preparedStmt.execute();
			object.setId(DaoUtils.getIdentity("hospede", "id"));
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;		
	}

	public Hospede update(Hospede object) {
		String query = " update hospede set nome = ?, idade = ?, telefone = ?, email = ?, idpagamento = ? where id = ?";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getIdade());
			preparedStmt.setString(3,  object.getTelefone());
			preparedStmt.setString(4, object.getEmail());
			preparedStmt.setObject(5, object.getMetPagamento().getId());
			preparedStmt.setInt(6, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
		
	}

	@Override
	public void delete(int id) throws Exception {
		String query = "DELETE FROM hospede WHERE id = ?";
		PreparedStatement preparedStmt = null;
		preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.execute();
		preparedStmt.close();

	}

	@Override
	public Hospede findById(int id) {
		String query = " select * from hospede where id = ?";
		Hospede hospede = null;
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySql.getConn().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs =  preparedStmt.executeQuery();
			
			while (rs.next()) {
				hospede = new Hospede();
				hospede.setId(rs.getInt("id"));
				hospede.setNome(rs.getString("nome"));
				hospede.setIdade(rs.getInt("idade"));
				hospede.setTelefone(rs.getString("telefone"));
				hospede.setEmail(rs.getString("email"));
				hospede.setMetPagamento(DaoSupplier.getDaoMetodoPagamento().findById(rs.getInt("idpagamento")));
				

				
				break;
			}			
			
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return hospede;
	}

	@Override
	public List<Hospede> findAll() {
		String query = " select * from hospede";
		List<Hospede> hospedes = new ArrayList<Hospede>();
		
		Statement statment = null;
		try {
			statment = ConnectionMySql.getConn().createStatement();
			ResultSet rs =  statment.executeQuery(query);
			
			while (rs.next()) {
				
				Hospede hospede = findById(rs.getInt("id"));
				
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
