package template.rest.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	private Connection connection = null;
	
	public Connection getConnection() {
		try {
			//String connectionURL = "jdbc:mysql://stanley.uepb.edu.br:3306/Restaurante";
			//String connectionURL = "jdbc:mysql://localhost:3306/restaurante";
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connection = DriverManager.getConnection(connectionURL, "root", "");
			String connectionURL = "jdbc:mysql://localhost:3306/controle_academico";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "root");
		} catch (Exception e) {
			e.getStackTrace();
		}	
		return connection;
	}
	
}
