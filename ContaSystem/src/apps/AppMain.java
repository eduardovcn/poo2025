//Define que a classe AppMain pertence ao pacote "apps"
//Pacotes servem para organizar o seu código em pastas lógicas
package apps;
import java.util.InputMismatchException;
import java.util.Scanner;
import contas.Conta;
import contas.ContaInvestimento;
import contas.Banco;

import static contas.Banco.imprimirContas;


// Importa a classe "Conta" que está dentro do pacote "contas".
// Isso é necessário para que a classe AppMain possa "enxergar" e usar a classe Conta.


// Este é o método principal (main), o ponto de entrada de qualquer programa Java.
// Quando você executa o programa, o código dentro deste método é o primeiro a rodar.
public class AppMain {
    public static void main(String[] args) {
        // Conta conta01: Declara uma variável chamada "conta01" que vai guardar um objeto do tipo "Conta".
        // Esta é a parte que efetivamente cria o objeto.
        //    - O comando "new" aloca memória para um novo objeto.
        //    - "Conta()" é a chamada ao "construtor" da classe Conta.
        //    O construtor é um método especial que inicializa o objeto recém-criado.

        Scanner scanner = new Scanner(System.in);
        int opcao;


        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Consultar Saldo");
            System.out.println("3. Sacar");
            System.out.println("4. Depositar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            try{

                switch (opcao) {

                    case 1:

                        System.out.println("1 = Conta Corrente");
                        System.out.println("2 = Conta Investimento");
                        System.out.println(" ");
                        System.out.println("Informe o tipo de conta: ");
                        opcao = scanner.nextInt();
                        scanner.nextLine(); // Consome a quebra de linha pendente


                        if (opcao == 1) {
                            System.out.println("Informe o nome do titular da conta:");
                            String nome = scanner.nextLine();
                            Conta conta = new Conta(nome);
                            Banco.adicionarConta(conta);
                            System.out.println(" ");
                            System.out.println("Conta criada com sucesso!");
                            System.out.println(" ");

                        }
                        if (opcao == 2) {
                            System.out.println("Informe o nome do titular da conta:");
                            String nome = scanner.nextLine();
                            ContaInvestimento contaInvestimento = new ContaInvestimento(nome);
                            System.out.println(" ");
                            System.out.println("Conta de Investimento criada com sucesso!");

                        }
                        else {
                            System.out.println("Opção inválida. Retornando ao menu principal.");
                            continue;
                        }
                        break;

                    case 2:
                        System.out.println("Digite o número da conta que você deseja consultar:");
                        int numeroConta = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha pendente
                        Banco.getConta(numeroConta);
                        break;

//                        case 3:
//                        System.out.println("Digite o número da conta que você deseja sacar:");
//                        numeroConta = scanner.nextInt();
//                        scanner.nextLine(); // Consumir a quebra de linha pendente
//                        conta = Banco.getConta(numeroConta);
//                        if (conta != null) {
//                            System.out.println("Digite o valor que você deseja sacar:");
//                            double quantidade = scanner.nextDouble();
//                            if (conta.sacar(quantidade)) {
//                                System.out.println("Saque realizado com sucesso!");
//                            } else {
//                                System.out.println("Saldo insuficiente para saque.");
//                            }
//                        } else {
//                            System.out.println("Conta não encontrada.");
//                        }
                    //

                    // o case 0 esta com o metodo só para imprimir as contas criadas, depois remover.
                    case 0:
                        imprimirContas();
                        System.out.println("Saindo do programa. Até mais!");
                        break;
                    default:
                        System.out.println("Erro: Opção inválida!");
                }
            } catch (InputMismatchException e) {
                // Se o usuário digitar algo que não é um número inteiro
                System.out.println("Erro: Por favor, digite apenas números.");
                scanner.next(); // IMPORTANTE: Limpa o buffer do scanner para evitar um loop infinito
                opcao = -1; // Reseta a opção para garantir a continuidade do loop
            }
        } while (opcao != 0);



        scanner.close();
    }

}
