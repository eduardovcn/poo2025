package contas;

public class ContaInvestimento extends Conta {
    private static int proximoNumeroContaInvestimento = 2000;

    private int prazo;
    private double taxa;

    public ContaInvestimento(String nome) {
        // Pega o construtor protegido da classe 'Conta',
        super(nome, proximoNumeroContaInvestimento++);
        this.prazo = 0;
        this.taxa = 0;
    }


    //Sobrecarga para ajustar o método, de acordo com as necessidades da Conta Investimento
    public void depositar(double quantidade, int prazo) {

        this.prazo = prazo;

        if (prazo <= 30) {
            this.taxa = 0.002; // 0.2%
        } else if (prazo <= 60) {
            this.taxa = 0.005; // 0.5%
        } else if (prazo <= 90) {
            this.taxa = 0.008; // 0.8%
        } else {
            this.taxa = 0.01;  // 1.0%
        }

        super.depositar(quantidade);

    }

    public void aplicarRendimento() {
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * this.taxa;

        if (rendimento > 0) {
            System.out.println("Parabéns! Sua conta ja começou a render. Agora é só esperar o prazo de " + this.prazo + " dias para poder sacar.");
            creditar(rendimento);
        }
    }

    @Override
    public void sacar(double quantidade) {
        System.out.println("Processando saque de Conta Investimento...\n");
        super.sacar(quantidade);
    }
}