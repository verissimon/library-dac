package com.library.dao;

import com.library.conexao.ConnectionFactory;
import com.library.entities.Livro;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    public void insereLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (isbn, titulo, autor, editora) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, livro.getIsbn());
        stmt.setString(2, livro.getTitulo());
        stmt.setString(3, livro.getAutor());
        stmt.setString(4, livro.getEditora());
        stmt.execute();
        stmt.close();
        System.out.println("Livro " + livro.getTitulo() + " Inserido com sucesso!");
    }

    public Livro buscaId(Long id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = '" + id + "'";
        return getLivro(sql);
    }

    public Livro buscaIsbn(String isbn) throws SQLException {
        String sql = "SELECT * FROM livro WHERE isbn = '" + isbn + "'";
        return getLivro(sql);
    }

    private Livro getLivro(String sql) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Livro livro = new Livro();
        while (rs.next()) {
            livro.setId(rs.getLong("id"));
            livro.setIsbn(rs.getString("isbn"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
        }
        return livro;
    }


}
