// MUDANÇA: Mudei o 'package' para 'apps' como no seu original.
package apps;

// MUDANÇA: Importa todas as classes do pacote 'contas'
import contas.*;

import java.util.InputMismatchException;
import java.util.Scanner;

// Import estático para chamar 'imprimirContas' diretamente
import static contas.Banco.imprimirContas;

public class AppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Encerrar Conta");
            System.out.println("9. Listar Contas (Debug)"); //Para debug
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha

                switch (opcao) {
                    case 1:
                        System.out.println("1 = Conta Corrente");
                        System.out.println("2 = Conta Investimento");
                        System.out.print("Informe o tipo de conta: ");
                        int tipoConta = scanner.nextInt();
                        scanner.nextLine(); // Consome a quebra de linha

                        System.out.println("Informe o nome do titular da conta:");
                        String nome = scanner.nextLine();

                        if (tipoConta == 1) {
                            ContaCorrente contaC = new ContaCorrente(nome);
                            Banco.adicionarConta(contaC);
                        } else if (tipoConta == 2) {
                            ContaInvestimento contaI = new ContaInvestimento(nome);
                            Banco.adicionarConta(contaI);
                        } else {
                            System.out.println("Opção inválida.");
                        }
                        break;

                    case 2:

                        System.out.println("Digite qual conta vocë deseja consultar o saldo: ");
                        int saldoConta = scanner.nextInt();
                        scanner.nextLine();

                        Conta contaSaldo = Banco.getConta(saldoConta);

                        System.out.println(contaSaldo);

                        break;

                    case 3:
                        System.out.println("Digite o número da conta para depósito:");
                        int contaDeposito = scanner.nextInt();

                        System.out.println("Digite o valor para depósito:");
                        double valorDeposito = scanner.nextDouble();
                        scanner.nextLine(); // Consumir

                        // Busca a conta
                        Conta contaParaDepositar = Banco.getConta(contaDeposito);

                        // Valida se a conta existe
                        if (contaParaDepositar == null) {
                            System.out.println("Erro: Conta não encontrada.");
                            break;
                        }

                        // Verifica a conta - 'instanceof'
                        if (contaParaDepositar instanceof ContaInvestimento) {
                            //ContaInvestimento:
                            System.out.println("Digite o prazo do investimento (em dias):");
                            int prazo = scanner.nextInt();
                            scanner.nextLine();

                            // Cast
                            // Transforma 'contaParaDepositar' de 'Conta' para 'ContaInvestimento'
                            ContaInvestimento contaInvest = (ContaInvestimento) contaParaDepositar;

                            // Método especial
                            contaInvest.depositar(valorDeposito, prazo);
                            contaInvest.aplicarRendimento();

                        } else {
                            //Método padrão para ContaCorrente
                            contaParaDepositar.depositar(valorDeposito);
                        }
                        break;

                    case 4:
                        System.out.println("Digite o número da conta para saque:");
                        int contaSaque = scanner.nextInt();

                        System.out.println("Digite o valor para saque:");
                        double valorSaque = scanner.nextDouble();
                        scanner.nextLine(); // Consumir

                        // Busca a conta
                        Conta contaParaSacar = Banco.getConta(contaSaque);

                        // Valida se a conta existe
                        if (contaParaSacar == null) {
                            System.out.println("Erro: Conta não encontrada.");
                            break;
                        }

                        contaParaSacar.sacar(valorSaque);
                        break;

                    case 5:

                        System.out.println("Qual conta você deseja encerrar? ");
                        int contaDeletar = scanner.nextInt();
                        scanner.nextLine();

                        Conta contaParaDeletar = Banco.getConta(contaDeletar);
                        Banco.deletarConta(contaParaDeletar);

                        break;

                    case 9:
                        System.out.println("--- Contas no Banco ---");
                        imprimirContas();
                        System.out.println("-----------------------");
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
}