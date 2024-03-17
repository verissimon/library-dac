package com.library.dao;

import com.library.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class ReservaDAO {
    
    private Connection connection;

    public ReservaDAO() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void createTableQuery() throws SQLException{
        String createTableQuery = "CREATE TABLE if not exists reserva (id INTEGER, id_aluno INTEGER NOT NULL, id_livro INTEGER NOT NULL, data_reserva TEXT, data_devolucao TEXT, PRIMARY KEY(id), FOREIGN KEY(id_aluno) REFERENCES aluno(id), FOREIGN KEY(id_livro) REFERENCES livro(id))";
        Statement statement = this.connection.createStatement();
        statement.execute(createTableQuery);
        statement.close();
    }
}
