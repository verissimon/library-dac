import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS aluno (id INTEGER PRIMARY KEY, nome TEXT, idade INTEGER)";
        Statement statement = connection.createStatement();
        statement.execute(createTableQuery);
        String insertQuery = "INSERT INTO aluno (nome, idade) VALUES ('João', 20)";
        statement.executeUpdate(insertQuery);

        connection.close();
        System.out.println("Conectou");
    }
}
