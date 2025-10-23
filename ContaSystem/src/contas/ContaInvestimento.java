package contas;

public class ContaInvestimento extends Conta {
    private static int proximoNumeroContaInvestimento = 2000;

    private int prazo;
    private double taxa;

    // Construtor para NOVAS contas
    public ContaInvestimento(String nome) {
        super(nome, proximoNumeroContaInvestimento++);
        this.prazo = 0; // Valores iniciais
        this.taxa = 0;
    }

    // Construtor para CARREGAR contas do banco
    public ContaInvestimento(int numConta, String nomeCliente, double saldo, double taxa, int prazo) {
        super(numConta, nomeCliente, saldo);
        this.taxa = taxa;
        this.prazo = prazo;
    }


    // Sobrecarga para ajustar o método, de acordo com as necessidades da Conta Investimento
    public void depositar(double quantidade, int prazo) {
        this.prazo = prazo;

        // Lógica para definir a taxa baseada no prazo
        if (prazo <= 30) {
            this.taxa = 0.002; // 0.2%
        } else if (prazo <= 60) {
            this.taxa = 0.005; // 0.5%
        } else if (prazo <= 90) {
            this.taxa = 0.008; // 0.8%
        } else {
            this.taxa = 0.01;  // 1.0%
        }

        // Chama o depositar da classe-mãe (que atualiza o SALDO na tabela 'conta')
        super.depositar(quantidade);

        // Persiste a TAXA e o PRAZO na tabela 'conta_investimento'
        Banco.atualizarInvestimento(this.getNumeroConta(), this.taxa, this.prazo);
    }


    public void aplicarRendimento() {
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * this.taxa;

        if (rendimento > 0) {
            System.out.println("Rendimento de R$ " + String.format("%.2f", rendimento) + " aplicado.");
            System.out.println("O prazo para saque é de " + this.prazo + " dias.");
            creditar(rendimento);
        } else {
            System.out.println("Sem rendimento para aplicar no momento.");
        }
    }

    // getters
    public double getTaxa() {
        return this.taxa;
    }
    public int getPrazo() {
        return this.prazo;
    }


    @Override
    public void sacar(double quantidade) {

        System.out.println("Processando saque de Conta Investimento...");
        super.sacar(quantidade);
    }

    // Sobrescreve o toString() para adicionar mais informações
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Tipo: Investimento" + "\n" +
                "Taxa: " + (this.taxa * 100) + "%\n" +
                "Prazo: " + this.prazo + " dias";
    }
}