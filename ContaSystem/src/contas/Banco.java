package contas;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    // usando o final para garantir que a referência da lista não será alterada indevidamente.
    //A lista é inicializada como uma nova ArrayList, garantindo que o objeto lista esteja sempre pronto para uso.
    private static final List<Conta> contasCorrente = new ArrayList<>();

    // Método para adicionar uma nova contaCorrente ao banco
    public static void adicionarConta(Conta conta) {
        contasCorrente.add(conta);
    }

    // Método para imprimir todas as contasCorrente no banco. Só para ver se está funcionando. Depois remover.
    public static void imprimirContas() {
        for (Conta conta : contasCorrente) {
            System.out.println(conta);

        }
    }

    public static void getConta(int numeroConta) {
        for (Conta conta : contasCorrente) {
            if (conta.getNumeroConta() == numeroConta) {
                System.out.println("\nNome: " + conta.getNome());
                System.out.println(" ");
                System.out.println("Saldo: " + conta.getSaldo());
            }
            else {
                System.out.println("\nConta não encontrada.");
            }
        }

    }

    // Outros métodos para listar, remover, etc.
}