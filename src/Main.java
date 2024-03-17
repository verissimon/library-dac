import java.sql.SQLException;

import com.library.dao.AlunoDAO;
import com.library.dao.LivroDAO;
import com.library.dao.ReservaDAO;
import com.library.entities.Aluno;
import com.library.entities.Livro;
import com.library.entities.Reserva;

import java.time.LocalDate;

public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AlunoDAO alunoDAO = new AlunoDAO();
        LivroDAO livroDAO = new LivroDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        // Criar tabelas
        inicializaTabelas(alunoDAO, livroDAO, reservaDAO);

    //     // Inserir alunos
    //    Aluno aluno1 = new Aluno("5654321", "cuao da Silva", "civil");
    //    alunoDAO.insereAluno(aluno1);

    //     // Inserir livro
    //    Livro livro1 = new Livro("02020202", "Titulo Ex2", "Autor Exemplo2", "Editora Exemplo");
    //    livroDAO.insereLivro(livro1);
        
    //     // Inserir reserva
    //    LocalDate hoje = LocalDate.now();
    //    Reserva reserva = new Reserva(livro1, aluno1, hoje, hoje.plusDays(7));
    //     reservaDAO.insereReserva("5645489", "4562132", hoje, hoje.plusDays(7));

        System.out.println(livroDAO.buscaIsbn("02020202"));
        System.out.println(alunoDAO.buscaMatricula("5654321"));
        System.out.println(reservaDAO.buscaId(1L));

    }

    public static void inicializaTabelas(AlunoDAO alunoDAO, LivroDAO livroDAO, ReservaDAO reservaDAO) {
        try {
            alunoDAO.createTableQuery();
            livroDAO.createTableQuery();
            reservaDAO.createTableQuery();
            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
