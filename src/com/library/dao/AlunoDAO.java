package com.library.dao;

import com.library.conexao.ConnectionFactory;
import com.library.entities.Aluno;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public void insereAluno(Aluno aluno) throws SQLException{
        String sql = "INSERT INTO aluno (matricula, nome_completo, curso) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, aluno.getMatricula());
        stmt.setString(2, aluno.getNomeCompleto());
        stmt.setString(3, aluno.getCurso());
        stmt.execute();
        stmt.close();
        System.out.println("aluno " + aluno.getNomeCompleto() + " Inserido com sucesso!");
    }

    public Aluno buscaId(Long id) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id = '" + id + "'";
        return getAluno(sql);
    }

    public Aluno buscaMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE matricula = '" + matricula + "'";
        return getAluno(sql);
    }

    public List<Aluno> all() throws SQLException {
        String sql = "SELECT * FROM aluno";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Aluno> alunos = new ArrayList<>();
        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setId(rs.getLong("id"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNomeCompleto(rs.getString("nome_completo"));
            aluno.setCurso(rs.getString("curso"));
            alunos.add(aluno);
        }
        return alunos;
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
        System.out.println("Aluno deletado com sucesso!");
    }

    public void update(Aluno newAluno, Long idToUpdate) throws SQLException {
        String sql = "UPDATE aluno SET matricula = ?, nome_completo = ?, curso = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, newAluno.getMatricula());
        stmt.setString(2, newAluno.getNomeCompleto());
        stmt.setString(3, newAluno.getCurso());
        stmt.setLong(4, idToUpdate);
        stmt.execute();
        stmt.close();
        System.out.println("Aluno " + newAluno.getNomeCompleto() + " atualizado com sucesso");
    }

    private Aluno getAluno(String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Aluno aluno = new Aluno();
        while (rs.next()) {
            aluno.setId(rs.getLong("id"));
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setNomeCompleto(rs.getString("nome_completo"));
            aluno.setCurso(rs.getString("curso"));
        }
        return aluno;
    }
}