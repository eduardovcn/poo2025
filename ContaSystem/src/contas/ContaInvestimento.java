package contas;

public class ContaInvestimento extends Conta {
    public int prazo = 0;
    public double taxa = 0;


    public ContaInvestimento(String nome, double taxa, int prazo) {
        super(nome);
        this.taxa = taxa;
        this.prazo = prazo;
    }

    @Override
    public boolean depositar(double quantidade) {
        System.out.println("Depósito da Conta Investimento:");
        return super.depositar(quantidade);
    }

    @Override
    public boolean sacar(double quantidade) {
        System.out.println("Saque da Conta Investimento:");
        return super.sacar(quantidade);
        }


    }

