package com.library.dao;

import com.library.conexao.ConnectionFactory;
import com.library.entities.Livro;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Livro> all() throws SQLException {
        String sql = "SELECT * FROM livro";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Livro> livros = new ArrayList<>();
        while (rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getLong("id"));
            livro.setIsbn(rs.getString("isbn"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
            livros.add(livro);
        }
        return livros;
    }

    public void update(Livro livro, Long idToUpdate) throws SQLException {
        String sql = "UPDATE livro SET isbn = ?, titulo = ?, autor = ?, editora = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, livro.getIsbn());
        stmt.setString(2, livro.getTitulo());
        stmt.setString(3, livro.getAutor());
        stmt.setString(4, livro.getEditora());
        stmt.setLong(5, idToUpdate);
        stmt.execute();
        stmt.close();
        System.out.println("Livro Atualizado com sucesso!");
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
        System.out.println("Livro deletado com sucesso!");
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
