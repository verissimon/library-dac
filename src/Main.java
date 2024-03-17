import java.sql.SQLException;

import com.library.dao.AlunoDAO;
import com.library.dao.LivroDAO;
import com.library.dao.ReservaDAO;

public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //  Criar tabelas
        inicializaTabelas();
    }

    public static void inicializaTabelas() {
        try {
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.createTableQuery();
            LivroDAO livroDAO = new LivroDAO();
            livroDAO.createTableQuery();
            ReservaDAO reservaDAO = new ReservaDAO();
            reservaDAO.createTableQuery();
            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
