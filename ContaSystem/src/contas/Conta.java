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

    // Método de depósito padrão.
    // Usado pela ContaCorrente e internamente pela ContaInvestimento.
    public boolean depositar(double quantidade) {
        if (quantidade > 0) {
            this.saldo += quantidade;
            System.out.println("Depósito de R$ " + quantidade + " realizado com sucesso! Seu novo saldo é: " + this.saldo);
            return true;
        } else {
            System.out.println("Valor inválido para depósito.");
            return false;
        }
    }

    public boolean sacar(double quantidade) {
        if (quantidade > 0 && quantidade <= this.saldo) {
            this.saldo -= quantidade;
            System.out.println("Saque de R$ " + quantidade + " realizado. Novo saldo: " + this.saldo);
            return true;
        } else {
            System.out.println("Saque inválido. Verifique o valor ou saldo insuficiente.");
            return false;
        }
    }

    // Getters
    public int getNumeroConta() { return this.numeroConta; }
    public double getSaldo() { return this.saldo; }
    public String getNome() { return this.nome; }

    @Override
    public String toString() {
        return "Conta [Numero: " + numeroConta + ", Titular: " + nome + ", Saldo: " + saldo + "]";
    }
}