package contas;

public class ContaCorrente extends Conta {
    // Gerador de ID simples para novas contas
    private static int proximoNumeroContaCorrente = 1000;

    // Construtor para NOVAS contas
    public ContaCorrente(String nome) {
        super(nome, proximoNumeroContaCorrente++);
    }

    // Construtor para CARREGAR contas do banco
    public ContaCorrente(int numConta, String nomeCliente, double saldo) {
        super(numConta, nomeCliente, saldo);
    }


}