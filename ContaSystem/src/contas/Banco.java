package contas;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    // MUDANÇA 1: Renomeei a lista para "contas", pois ela guarda TODOS os tipos de conta.
    private static final List<Conta> contas = new ArrayList<>();

    // Adiciona uma conta de qualquer tipo (ContaCorrente ou ContaInvestimento)
    public static void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    // MUDANÇA 2: Método 'getConta' corrigido.
    // - Agora retorna 'Conta' em vez de 'Object'.
    // - Removido o 'else' de dentro do loop.
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