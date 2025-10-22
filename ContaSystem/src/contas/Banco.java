package contas;

// Imports necessários
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConexaoFactory;

public class Banco {


    public static void inicializarBanco() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS conta (" +
                "    numeroConta INTEGER PRIMARY KEY," +
                "    nome TEXT NOT NULL," +
                "    saldo REAL DEFAULT 0.0" +
                ");";

        //Medida de segurança. Garante que não haja vazamento de recursos.
        try (Connection conn = ConexaoFactory.getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCreateTable);

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela 'conta'.");
            e.printStackTrace();
        }
    }


    /// Adiciona conta ao banco de dados.

    public static void adicionarConta(Conta conta) {
        // O "caminho das pedras" para segurança: PreparedStatement
        // NUNCA concatenar strings para montar um SQL (ex: "INSERT... VALUES (" + conta.getNumero() + ...)")
        // previne SQL Injection.
        String sqlInsert = "INSERT INTO conta(numeroConta, nome, saldo) VALUES(?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {

            pstmt.setInt(1, conta.getNumeroConta());
            pstmt.setString(2, conta.getNome());
            pstmt.setDouble(3, conta.getSaldo());

            // Executa o comando de inserção
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta.");
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma conta do banco de dados.
     */
    public static void deletarConta(Conta conta) {
        String sqlDelete = "DELETE FROM conta WHERE numeroConta = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setInt(1, conta.getNumeroConta());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar conta.");
            e.printStackTrace();
        }
    }

    /**
     * Busca e retorna uma conta pelo número.
     * Retorna null se não encontrar.
     */
    public static Conta getConta(int numeroConta) {
        String sqlSelect = "SELECT * FROM conta WHERE numerConta = ?";
        Conta conta = null;

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

            pstmt.setInt(1, numeroConta);

            // ResultSet é o objeto que contém os resultados do meu SELECT
            try (ResultSet rs = pstmt.executeQuery()) {

                // rs.next() move o "cursor" para a primeira linha de resultado
                // Se retornar true, é porque encontrou uma conta.
                if (rs.next()) {
                    // Extrai os dados da linha
                    int numConta = rs.getInt("numeroConta");
                    String nomeCliente = rs.getString("nome");
                    double saldo = rs.getDouble("saldo");

                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta.");
            e.printStackTrace();
        }

        return conta; // Retorna a conta encontrada ou null
    }


    public static void atualizarSaldo(Conta conta) {
        String sqlUpdate = "UPDATE conta SET saldo = ? WHERE numeroConta = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setDouble(1, conta.getSaldo());
            pstmt.setInt(2, conta.getNumeroConta());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo da conta.");
            e.printStackTrace();
        }
    }
}