package com.library.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection() throws ClassNotFoundException {
		try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:./database.db";
            return DriverManager.getConnection(dbURL);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
