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
                "    numero_conta INTEGER PRIMARY KEY," +
                "    nome_cliente TEXT NOT NULL," +
                "    saldo REAL DEFAULT 0.0,"  +
                "    tipo_conta TEXT NOT NULL CHECK(tipo_conta IN ('CORRENTE', 'INVESTIMENTO'))" +
                ");";

        String sqlCreateTableInvestimento = "CREATE TABLE IF NOT EXISTS conta_investimento (" +
                "    numero_conta INTEGER PRIMARY KEY," +
                "    taxa_rendimento REAL NOT NULL," +
                "    prazo_dias INTEGER NOT NULL," +
                "    FOREIGN KEY (numero_conta) REFERENCES conta (numero_conta) ON DELETE CASCADE" +
                ");";

        try (Connection conn = ConexaoFactory.getConexao();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCreateTable);
            stmt.execute(sqlCreateTableInvestimento);

        } catch (SQLException e) {
            System.err.println("Erro ao criar o banco de dados.");
            e.printStackTrace();
        }
    }

    public static void adicionarConta(Conta conta) {
        String sqlInsertConta = "INSERT INTO conta(numero_conta, nome_cliente, saldo, tipo_conta) VALUES(?, ?, ?, ?)";
        String sqlInsertInvestimento = "INSERT INTO conta_investimento(numero_conta, taxa_rendimento, prazo_dias) VALUES(?, ?, ?)";

        Connection conn = null;

        try {
            conn = ConexaoFactory.getConexao();
            conn.setAutoCommit(false); // Inicia transação

           // Insere na tabela 'conta'
            try (PreparedStatement pstmtConta = conn.prepareStatement(sqlInsertConta)) {
                pstmtConta.setInt(1, conta.getNumeroConta());
                pstmtConta.setString(2, conta.getNome());
                pstmtConta.setDouble(3, conta.getSaldo());

                if (conta instanceof ContaInvestimento) {
                    pstmtConta.setString(4, "INVESTIMENTO");
                } else {
                    pstmtConta.setString(4, "CORRENTE");
                }
                pstmtConta.executeUpdate();
            }

            // Se for ContaInvestimento, insere também na tabela 'conta_investimento'
            if (conta instanceof ContaInvestimento contaInvest) {
                try (PreparedStatement pstmtInvest = conn.prepareStatement(sqlInsertInvestimento)) {
                    pstmtInvest.setInt(1, contaInvest.getNumeroConta());
                    pstmtInvest.setDouble(2, contaInvest.getTaxa());
                    pstmtInvest.setInt(3, contaInvest.getPrazo());
                    pstmtInvest.executeUpdate();
                }
            }

            conn.commit();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir conta. Realizando rollback.");
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.err.println("Erro ao realizar rollback.");
                e2.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaura o auto-commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Conta getConta(int numeroConta) {
        String sqlSelect = "SELECT c.numero_conta, c.nome_cliente, c.saldo, c.tipo_conta, " +
                "ci.taxa_rendimento, ci.prazo_dias " +
                "FROM conta c " +
                "LEFT JOIN conta_investimento ci ON c.numero_conta = ci.numero_conta " +
                "WHERE c.numero_conta = ?";

        Conta conta = null;

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

            pstmt.setInt(1, numeroConta);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int numConta = rs.getInt("numero_conta");
                    String nomeCliente = rs.getString("nome_cliente");
                    double saldo = rs.getDouble("saldo");
                    String tipoConta = rs.getString("tipo_conta");

                    if ("INVESTIMENTO".equalsIgnoreCase(tipoConta)) {
                        double taxa = rs.getDouble("taxa_rendimento");
                        int prazo = rs.getInt("prazo_dias");

                        conta = new ContaInvestimento(numConta, nomeCliente, saldo, taxa, prazo);
                    } else {

                        conta = new ContaCorrente(numConta, nomeCliente, saldo);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta.");
            e.printStackTrace();
        }

        return conta;
    }


    public static void atualizarSaldo(int numeroConta, double novoSaldo) {
        String sqlUpdate = "UPDATE conta SET saldo = ? WHERE numero_conta = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setDouble(1, novoSaldo);
            pstmt.setInt(2, numeroConta);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo da conta " + numeroConta);
            e.printStackTrace();
        }
    }

    // Atualiza taxa e prazo da conta investimento
    public static void atualizarInvestimento(int numeroConta, double taxa, int prazo) {
        String sqlUpdate = "UPDATE conta_investimento SET taxa_rendimento = ?, prazo_dias = ? WHERE numero_conta = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

            pstmt.setDouble(1, taxa);
            pstmt.setInt(2, prazo);
            pstmt.setInt(3, numeroConta);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados de investimento da conta " + numeroConta);
            e.printStackTrace();
        }
    }



    public static void deletarConta(int numeroConta) {
        // O "ON DELETE CASCADE" na tabela conta_investimento garante que
        // ao deletar da tabela 'conta', a entrada correspondente em 'conta_investimento'
        // também seja removida.
        String sqlDelete = "DELETE FROM conta WHERE numero_conta = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {

            pstmt.setInt(1, numeroConta);
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Conta " + numeroConta + " encerrada com sucesso.");
            } else {
                System.out.println("Conta " + numeroConta + " não encontrada.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar conta.");
            e.printStackTrace();
        }
    }
}