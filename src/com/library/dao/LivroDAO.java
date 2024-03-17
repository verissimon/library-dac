package com.library.dao;

import com.library.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class LivroDAO {

    private Connection connection;

    public LivroDAO() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void createTableQuery() throws SQLException{
        String createTableQuery = "CREATE TABLE IF NOT EXISTS \"livro\" (\"id\" INTEGER, \"isbn\" TEXT NOT NULL UNIQUE, \"titulo\" TEXT, \"autor\" TEXT, \"editora\" TEXT, PRIMARY KEY(\"id\"))";
        Statement statement = this.connection.createStatement();
        statement.execute(createTableQuery);
        statement.close();
    }

}
