package contas;

public abstract class Conta {
    private final int numeroConta;
    private final String nome;
    private double saldo;

    // Construtor para NOVAS contas (chamado pelas classes filhas)
    protected Conta(String nome, int numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = 0;
        this.nome = nome;
        System.out.println("\nConta criada com sucesso!");
        System.out.println("Número da Conta: " + this.numeroConta);
        System.out.println("Nome do Titular: " + this.nome + "\n");
    }

    // Construtor para CARREGAR contas do banco (chamado pelo Banco.getConta)
    public Conta(int numConta, String nomeCliente, double saldo) {
        this.numeroConta = numConta;
        this.nome = nomeCliente;
        this.saldo = saldo;
    }

    public void depositar(double quantidade) {
        if (quantidade > 0) {
            double novoSaldo = this.saldo + quantidade;
            // Persiste a mudança no banco de dados
            Banco.atualizarSaldo(this.numeroConta, novoSaldo);
            // Atualiza o estado do objeto em memória
            this.saldo = novoSaldo;

            System.out.println("Depósito de R$ " + quantidade + " realizado com sucesso! Seu novo saldo é: " + this.saldo);
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    // Também persiste a mudança no banco
    protected void creditar(double valor) {
        if (valor > 0) {
            double novoSaldo = this.saldo + valor;

            Banco.atualizarSaldo(this.numeroConta, novoSaldo);
            this.saldo = novoSaldo;
        }
    }

    // Banco.atualizarSaldo após sacar
    public void sacar(double quantidade) {
        if (quantidade > 0 && quantidade <= this.saldo) {
            double novoSaldo = this.saldo - quantidade;
            // Persiste
            Banco.atualizarSaldo(this.numeroConta, novoSaldo);
            // Atualiza objeto
            this.saldo = novoSaldo;

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
        return "\n--- Detalhes da Conta ---" + "\n" +
                "Número: " + numeroConta + "\n" +
                "Titular: " + nome + "\n" +
                "Saldo: R$ " + String.format("%.2f", saldo);
    }
}