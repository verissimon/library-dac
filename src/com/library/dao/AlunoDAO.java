package com.library.dao;

import com.library.conexao.ConnectionFactory;
import com.library.entities.Aluno;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class AlunoDAO {
    
    private Connection connection;

    public AlunoDAO() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void createTableQuery() throws SQLException{
        String createTableQuery = "CREATE TABLE IF NOT EXISTS aluno (id INTEGER PRIMARY KEY, matricula TEXT not null unique, nome_completo TEXT not null, curso TEXT not null)";
        Statement statement = this.connection.createStatement();
        statement.execute(createTableQuery);
        statement.close();
    }

}