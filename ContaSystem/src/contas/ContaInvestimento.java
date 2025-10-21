package contas;

public class ContaInvestimento extends Conta {
    private static int proximoNumeroContaInvestimento = 2000;

    public int prazo;
    public double taxa;

    public ContaInvestimento(String nome) {
        // Pega o construtor protegido da classe 'Conta',

        super(nome, proximoNumeroContaInvestimento++);
        this.prazo = 0; // Inicia sem prazo
        this.taxa = 0;  // Inicia sem taxa
    }


    //Sobrecarga. novo método, que só existe na ContaInvestimento.
    //É o único jeito de depositar nesta conta.
    public boolean depositar(double quantidade, int prazo) {

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


        // Chama o 'depositar' da CLASSE PAI (super) para realmente guardar o dinheiro
        // Isso executa a lógica de 'this.saldo += quantidade' da classe Conta. Como preciso retornar um boolean, salvo o resultado em uma variável do tipo boolean.
        boolean sucesso = super.depositar(quantidade);

        return sucesso;
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
    public boolean sacar(double quantidade) {
        // No futuro: if (prazoExpirou) { ... }
        System.out.println("Processando saque de Conta Investimento...");
        return super.sacar(quantidade);
    }
}