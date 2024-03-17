package com.library.dao;

import com.library.conexao.ConnectionFactory;
import com.library.entities.Reserva;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private AlunoDAO alunoDAO = new AlunoDAO();
    private LivroDAO livroDAO = new LivroDAO();

    public ReservaDAO() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void createTableQuery() throws SQLException {
        String createTableQuery = "CREATE TABLE if not exists reserva (id INTEGER, mat_aluno TEXT NOT NULL, isbn_livro TEXT NOT NULL, data_reserva TEXT, data_devolucao TEXT, PRIMARY KEY(id), FOREIGN KEY(mat_aluno) REFERENCES aluno(matricula), FOREIGN KEY(isbn_livro) REFERENCES livro(isbn))";
        Statement statement = this.connection.createStatement();
        statement.execute(createTableQuery);
        statement.close();
    }

    public void insereReserva(String mat_aluno, String isbn_livro, LocalDate data_reserva, LocalDate data_devolucao) {
        try {
            String sql = "INSERT INTO reserva (mat_aluno, isbn_livro, data_reserva, data_devolucao) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mat_aluno);
            stmt.setString(2, isbn_livro);
            stmt.setString(3, data_reserva.format(formatter));
            stmt.setString(4, data_devolucao.format(formatter));
            stmt.execute();
            stmt.close();
            System.out.println("Reserva Inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insereReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (mat_aluno, isbn_livro, data_reserva, data_devolucao) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, reserva.getAluno().getMatricula());
            stmt.setString(2, reserva.getLivro().getIsbn());
            stmt.setString(3, reserva.getDataReserva().format(formatter));
            stmt.setString(4, reserva.getDataDevolucao().format(formatter));
            stmt.execute();
            stmt.close();
            System.out.println("Reserva Inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reserva byId(Long id) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE id = '" + id + "'";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Reserva reserva = new Reserva();
        while (rs.next()) {
            constroiObj(rs, reserva);
        }
        return reserva;
    }

    public List<Reserva> all() throws SQLException {
        String sql = "SELECT * FROM reserva";
        PreparedStatement stmt = connection.prepareStatement(sql);
        return getReservas(stmt);
    }

    public List<Reserva> byIsbn(String isbn) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE isbn_livro = '" + isbn + "'";
        PreparedStatement stmt = connection.prepareStatement(sql);
        return getReservas(stmt);
    }

    public List<Reserva> byMatricula(String matricula) throws SQLException {
        String sql = "SELECT * FROM reserva WHERE mat_aluno = '" + matricula + "'";
        PreparedStatement stmt = connection.prepareStatement(sql);
        return getReservas(stmt);
    }

    public List<Reserva> byDataDevolucaoPassada() throws SQLException {
        String sql = "SELECT * FROM reserva WHERE date(data_devolucao) < date(?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, LocalDate.now().format(formatter));
        return getReservas(stmt);
    }

    public List<Reserva> byDataDevolucaoFutura() throws SQLException {
        String sql = "SELECT * FROM reserva WHERE date(data_devolucao) > date(?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, LocalDate.now().format(formatter));
        return getReservas(stmt);
    }

    public void update(Reserva reserva, Long idToUpdate) throws SQLException {
        String sql = "UPDATE reserva SET mat_aluno = ?, isbn_livro = ?, data_reserva = ?, data_devolucao = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, reserva.getAluno().getMatricula());
        stmt.setString(2, reserva.getLivro().getIsbn());
        stmt.setString(3, reserva.getDataReserva().format(formatter));
        stmt.setString(4, reserva.getDataDevolucao().format(formatter));
        stmt.setLong(5, idToUpdate);
        stmt.execute();
        stmt.close();
        System.out.println("Reserva Atualizada com sucesso!");
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM reserva WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.execute();
        stmt.close();
        System.out.println("Reserva deletada com sucesso!");
    }

    private List<Reserva> getReservas(PreparedStatement stmt) throws SQLException {
    
        ResultSet rs = stmt.executeQuery();
        List<Reserva> reservas = new ArrayList<>();
        while (rs.next()) {
            Reserva reserva = new Reserva();
            constroiObj(rs, reserva);
            reservas.add(reserva);
        }
        return reservas;
    }

    private void constroiObj(ResultSet rs, Reserva reserva) throws SQLException {
        String mat_aluno = rs.getString("mat_aluno");
        String isbn_livro = rs.getString("isbn_livro");
        reserva.setAluno(alunoDAO.buscaMatricula(mat_aluno));
        reserva.setLivro(livroDAO.buscaIsbn(isbn_livro));
        reserva.setDataReserva(LocalDate.parse(rs.getString("data_reserva"), formatter));
        reserva.setDataDevolucao(LocalDate.parse(rs.getString("data_devolucao"), formatter));
    }
}
