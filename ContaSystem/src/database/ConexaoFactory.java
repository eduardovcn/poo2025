package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    //Criando o alias para o nome do banco de dados.
    private static final String URL = "jdbc:sqlite:banco.db";

    public static Connection getConexao() {
        try {
            //Registrando o driver que foi baixado
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(URL);

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do SQLite não encontrado.");
            e.printStackTrace();
            return null;

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
            return null; // ou lançar uma exceção
        }
    }
}
