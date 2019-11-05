package com.fadergs.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
	private static Connection conn;
	
	private ConnectionMySql() {
		conn = getConexaoMySQL();
	}
	
	private static ConnectionMySql connMySQL = new ConnectionMySql();
	
	public static Connection getConn() {
		return conn;
	}
	
	private java.sql.Connection getConexaoMySQL() {
        Connection connection = null;
		try { 
			System.out.println("1------");
			String serverName = "localhost:3306"; 
			String mydatabase ="hotelaria"; 
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase+"?useTimezone=true&serverTimezone=UTC";
			String username = "root";       
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("2------");
 			if (connection != null) {
                System.out.println("STATUS--->Conectado com sucesso!");
            } else {
            	System.out.println("STATUS--->Não foi possivel realizar conexão");
            }
 			return connection;

		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados."+e.getMessage());
			return null;
		}
    }



}
