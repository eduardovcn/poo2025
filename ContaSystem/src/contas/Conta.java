package contas;


public class Conta {
    /// O modificador static faz com que essa variável pertença à classe Conta como um todo, e não a instâncias individuais.
    /// O modificador private restringe o acesso a essa variável apenas dentro da própria classe Conta.
    private static int proximoNumeroConta = 1000;
    private final int numeroConta;
    private final String nome;
    private double saldo;

    // Construtor
    public Conta(String nome) {
        this.numeroConta = proximoNumeroConta++;
        this.saldo = 0;
        this.nome = nome;
        System.out.println("\nConta criada com sucesso! \n");
        System.out.println("Número da Conta: " + this.numeroConta);
        System.out.println("\nNome do Titular: " + this.nome + "\n");

    }

    // Getters
    public int getNumeroConta() {
        return this.numeroConta;
    }
    public double getSaldo() {
        return this.saldo;
    }
    public String getNome() {
        return this.nome;
    }


    // Métodos
    public boolean sacar(double quantidade) {

        if (quantidade <= this.saldo) {
            double novoSaldo = this.saldo - quantidade;
            this.saldo = novoSaldo;
            System.out.println("O valor de " + quantidade + " foi sacado com sucesso!");

            return true;
        } else {
            System.out.println("O valor que você solicitou excede o saldo disponivel");

            return false;

        }

    }

    public boolean depositar(double quantidade) {
        if (quantidade > 0) {
            double novoSaldo = this.saldo + quantidade;
            this.saldo = novoSaldo;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Conta\n" +
                "Numero da Conta: " + numeroConta + "\n" +
                "Nome: " + nome + "\n" +
                "Saldo: " + saldo + "\n";

    }
}





