package contas;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private static final List<Conta> contas = new ArrayList<>();

    // Adiciona uma conta de qualquer tipo (ContaCorrente ou ContaInvestimento)
    public static void adicionarConta(Conta conta) {
        contas.add(conta);
    }


    public static Conta getConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta; // Encontrou a conta, retorna o objeto
            }
        }
        // Se o loop terminar e não encontrar, retorna null
        return null;
    }

    // Método para imprimir todas as contas (para debug)
    public static void imprimirContas() {
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }
}