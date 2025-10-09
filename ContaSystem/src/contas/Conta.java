package contas;

public class Conta {
    private int numero;
    String nome;
    private double saldo;
    double limite;

    public Conta() {
        this.saldo = 0.0; //inicia toda conta com saldo 0.0
        System.out.println("Uma nova conta foi criada com sucesso");
    }
    void sacar(double quantidade) {
        double novoSaldo = this.saldo - quantidade;
        this.saldo = novoSaldo;
    }

    void depositar(double quantidade) {
        double novoSaldo = this.saldo + quantidade;
        this.saldo = novoSaldo;
    }

    @Override
    public String toString() {
            return "Conta {\n" +
                    "Número: " + numero + "\n" +
                    "Nome: " + nome + "\n" +
                    "Saldo: " + saldo + "\n" +
                    "Limite: " + limite + "\n";
    }
}


