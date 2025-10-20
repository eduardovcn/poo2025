package contas;

public class ContaInvestimento extends Conta {
    private static int proximoNumeroContaInvestimento = 2000;

    public int prazo;
    public double taxa;

    public ContaInvestimento(String nome) {
        // Chama o construtor PROTEGIDO da classe 'Conta',
        // passando o nome e o próximo número da SUA sequência (2000, 2001...)
        super(nome, proximoNumeroContaInvestimento++);
        this.prazo = 0; // Inicia sem prazo
        this.taxa = 0;  // Inicia sem taxa
    }

    /**
     * MUDANÇA 1: SOBRESCRITA (Override)
     * Este método substitui o 'depositar(double)' da classe pai.
     * Ele BLOQUEIA depósitos normais.
     */
    @Override
    public boolean depositar(double quantidade) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Erro: Depósitos em Conta Investimento devem informar um prazo.");
        System.out.println("Use a opção de depósito informando o prazo.");
        System.out.println("----------------------------------------------------------");
        return false;
    }

    /**
     * MUDANÇA 2: SOBRECARGA (Overload)
     * Este é um NOVO método, que só existe na ContaInvestimento.
     * É o único jeito de depositar nesta conta.
     */
    public boolean depositar(double quantidade, int prazo) {
        // 1. Salva o prazo e calcula a taxa para este depósito
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


        // 2. Chama o 'depositar' da CLASSE PAI (super) para realmente guardar o dinheiro
        // Isso executa a lógica de 'this.saldo += quantidade' da classe Conta. Como preciso retornar um boolean, salvo o resultado em uma variável do tipo boolean.
        boolean sucesso = super.depositar(quantidade);

        return sucesso;
    }

    public void aplicarRendimento() {
        double saldoAtual = getSaldo();
        double rendimento = saldoAtual * this.taxa;

        if (rendimento > 0) {
            System.out.println("Aplicando rendimento de R$ " + rendimento + "...");
            // Chama o 'super.depositar' para não ser bloqueado pelo override
            super.depositar(rendimento);
        }
    }

    @Override
    public boolean sacar(double quantidade) {
        // No futuro: if (prazoExpirou) { ... }
        System.out.println("Processando saque de Conta Investimento...");
        return super.sacar(quantidade);
    }
}