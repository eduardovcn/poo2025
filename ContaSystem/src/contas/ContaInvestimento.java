package contas;

public class ContaInvestimento extends Conta {
    private static int proximoNumeroContaInvestimento = 2000;
    public int prazo = 0;
    public double taxa = 0;


    public ContaInvestimento(String nome) {
        super(nome, proximoNumeroContaInvestimento++);

    }

    public void creditarRendimento(){
        if (prazo <= 30) {
            this.taxa = 0.002; // 0.2%
        } else if (prazo <= 60) {
            this.taxa = 0.005; // 0.5%
        } else if (prazo <= 90) {
            this.taxa = 0.008; // 0.8%
        } else {
            this.taxa = 0.01;  // 1.0%
        }
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * taxa;
        depositar(rendimento);
        System.out.println("Rendimento de " + rendimento + " creditado na Conta Investimento.");


    }


    @Override
    public boolean sacar(double quantidade) {
        System.out.println("Saque da Conta Investimento:");
        return super.sacar(quantidade);
        }


    }

