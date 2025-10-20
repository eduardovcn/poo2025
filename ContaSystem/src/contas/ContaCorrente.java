package contas;

public class ContaCorrente extends Conta {
    private static int proximoNumeroContaCorrente = 1000;

    // Construtor da classe ContaCorrente
    public ContaCorrente(String nome) {
        super(nome, proximoNumeroContaCorrente++);


    }

}
