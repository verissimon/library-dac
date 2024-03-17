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
        LocalDate hoje = LocalDate.now();
//        Criar tabelas
        inicializaTabelas(alunoDAO, livroDAO, reservaDAO);

        // Inserir alunos
        Aluno aluno1 = new Aluno("5654321", "jose da Silva", "ADS");
        alunoDAO.insereAluno(aluno1);

        // Inserir livro
        Livro livro1 = new Livro("123456789", "Titulo Ex2", "Autor Exemplo2", "Editora Exemplo");
        livroDAO.insereLivro(livro1);

        // Inserir reserva: Aluno e livro devem existir na tabela antes da insercao
//        reservaDAO.insereReserva("5654321", "123456789", hoje, hoje.plusDays(7)); // passando matricula_aluno e isbn_livro
        // outra forma de inserir reserva:
        Aluno aln1 = alunoDAO.buscaMatricula("5654321");
        Livro lvr1 = livroDAO.buscaIsbn("123456789");
        Reserva r1 = new Reserva(aln1, lvr1, hoje, hoje.plusDays(14));
        reservaDAO.insereReserva(r1);
//
//        // Leitura dos dados
        System.out.println(livroDAO.buscaIsbn("123456789"));
        System.out.println(alunoDAO.buscaMatricula("5654321"));
        System.out.println(reservaDAO.byId(1L));
        alunoDAO.all().forEach(System.out::println);
        livroDAO.all().forEach(System.out::println);
        reservaDAO.all().forEach(System.out::println);
        reservaDAO.byIsbn("123456789").forEach(System.out::println);
        reservaDAO.byMatricula("5654321").forEach(System.out::println);
//        // reservas que ja passaram da data de devolucao:
        reservaDAO.byDataDevolucaoPassada().forEach(System.out::println);
//        // reservas com data de devoluçao no futuro:
        reservaDAO.byDataDevolucaoFutura().forEach(System.out::println);

        // update Aluno
        alunoDAO.update(new Aluno("5654321", "aluno Atuliazado", "novo atualizado"), 1L);
        System.out.println(alunoDAO.buscaId(1L));

        // update Livro
        livroDAO.update(new Livro("123456789", "Livro Atualizado", "Autor Atualizado", "Editora Atualizada"), 1L);
        System.out.println(livroDAO.buscaId(1L));

        // update Reserva
        reservaDAO.update(new Reserva(alunoDAO.buscaMatricula("5654321"), livroDAO.buscaIsbn("123456789"), hoje, hoje.plusDays(14)), 1L);
        System.out.println(reservaDAO.byId(1L));

        // delete Aluno
        alunoDAO.delete(1L);

        // delete Livro
        livroDAO.delete(1L);

        // delete Reserva
        reservaDAO.delete(1L);

    }


    public static void inicializaTabelas(AlunoDAO alunoDAO, LivroDAO livroDAO, ReservaDAO reservaDAO) {
        try {
            alunoDAO.createTableQuery();
            livroDAO.createTableQuery();
            reservaDAO.createTableQuery();
//            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
