package apps;

import contas.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {

        // 1. Garante que as tabelas do banco existam
        Banco.inicializarBanco();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Consultar Conta (Saldo/Detalhes)");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Encerrar Conta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        criarConta(scanner);
                        break;
                    case 2:
                        consultarConta(scanner);
                        break;
                    case 3:
                        depositar(scanner);
                        break;
                    case 4:
                        sacar(scanner);
                        break;
                    case 5:
                        encerrarConta(scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        break;
                    default:
                        System.out.println("Erro: Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                scanner.next(); // Limpa o buffer do scanner
                opcao = -1; // Reseta a opção
            }
        } while (opcao != 0);

        scanner.close();
    }

    // MÓDULO: Criar Conta
    private static void criarConta(Scanner scanner) {
        System.out.println("1 = Conta Corrente");
        System.out.println("2 = Conta Investimento");
        System.out.print("Informe o tipo de conta: ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha

        System.out.println("Informe o nome do titular da conta:");
        String nome = scanner.nextLine();

        Conta novaConta = null;
        if (tipoConta == 1) {
            novaConta = new ContaCorrente(nome);
        } else if (tipoConta == 2) {
            novaConta = new ContaInvestimento(nome);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        Banco.adicionarConta(novaConta);
    }

    private static void consultarConta(Scanner scanner) {
        System.out.println("Digite qual conta você deseja consultar:");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();

        Conta conta = Banco.getConta(numeroConta);

        if (conta != null) {
            System.out.println(conta);
        } else {
            System.out.println("Erro: Conta " + numeroConta + " não encontrada.");
        }
    }

    private static void depositar(Scanner scanner) {
        System.out.println("Digite o número da conta para depósito:");
        int contaDeposito = scanner.nextInt();

        System.out.println("Digite o valor para depósito:");
        double valorDeposito = scanner.nextDouble();
        scanner.nextLine(); // Consumir

        Conta contaParaDepositar = Banco.getConta(contaDeposito);

        if (contaParaDepositar == null) {
            System.out.println("Erro: Conta não encontrada.");
            return;
        }

        // Verifica se é uma ContaInvestimento para usar o método sobrecarregado
        if (contaParaDepositar instanceof ContaInvestimento contaInvest) {
            System.out.println("Digite o prazo do investimento (em dias):");
            int prazo = scanner.nextInt();
            scanner.nextLine();

            // Chama o depositar(valor, prazo) da ContaInvestimento
            contaInvest.depositar(valorDeposito, prazo);
            contaInvest.aplicarRendimento(); // Aplica o primeiro rendimento

        } else {
            // Chama o depositar(valor) padrão da Conta (usado pela ContaCorrente)
            contaParaDepositar.depositar(valorDeposito);
        }
    }

    private static void sacar(Scanner scanner) {
        System.out.println("Digite o número da conta para saque:");
        int contaSaque = scanner.nextInt();

        System.out.println("Digite o valor para saque:");
        double valorSaque = scanner.nextDouble();
        scanner.nextLine(); // Consumir
        Conta contaParaSacar = Banco.getConta(contaSaque);

        if (contaParaSacar == null) {
            System.out.println("Erro: Conta não encontrada.");
            return;
        }

        // O método sacar dentro da classe Conta já cuida de atualizar o objeto E salvar no banco.
        contaParaSacar.sacar(valorSaque);
    }

    private static void encerrarConta(Scanner scanner) {
        System.out.println("Qual conta você deseja encerrar? ");
        int contaDeletar = scanner.nextInt();
        scanner.nextLine();

        Banco.deletarConta(contaDeletar);
    }
}