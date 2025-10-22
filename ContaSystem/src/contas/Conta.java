package contas;

public class Conta {
    private final int numeroConta;
    private final String nome;
    private double saldo;

    // Construtor PROTEGIDO. Só pode ser chamado pelas classes filhas (ContaCorrente, etc.)
    protected Conta(String nome, int numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = 0;
        this.nome = nome;
        System.out.println("\nConta criada com sucesso!");
        System.out.println("Número da Conta: " + this.numeroConta);
        System.out.println("Nome do Titular: " + this.nome + "\n");
    }

    /// Construtor usado para carregar dados do banco de dados.
    public Conta(int numConta, String nomeCliente, double saldo) {
        this.numeroConta = numConta;
        this.nome = nomeCliente;
        this.saldo = saldo;
    }

    // Usado pela ContaCorrente e internamente pela ContaInvestimento.
    public void depositar(double quantidade) {
        if (quantidade > 0) {
            double novoSaldo = (this.saldo + quantidade);
            Banco.atualizarSaldo(this.numeroConta, novoSaldo);

            System.out.println("Depósito de R$ " + quantidade + " realizado com sucesso! Seu novo saldo é: " + novoSaldo);

        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }
    protected void creditar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }

    public void sacar(double quantidade) {
        if (quantidade > 0 && quantidade <= this.saldo) {
            this.saldo -= quantidade;
            System.out.println("Saque de R$ " + quantidade + " realizado. Novo saldo: " + this.saldo);

        } else {
            System.out.println("Saque inválido. Verifique o valor ou saldo insuficiente.");

        }
    }

    // Getters
    public int getNumeroConta() { return this.numeroConta; }
    public double getSaldo() { return this.saldo; }
    public String getNome() { return this.nome; }

    @Override
    public String toString() {
        return "\nNúmero da Conta: " + numeroConta + "\n" +
                "Titular: " + nome + "\n" +
                "Saldo: " + saldo + "\n";
    }
}