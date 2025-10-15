//Define que a classe AppMain pertence ao pacote "apps"
//Pacotes servem para organizar o seu código em pastas lógicas
package apps;
import java.util.InputMismatchException;
import java.util.Scanner;
import contas.Conta;
import contas.ContaInvestimento;


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
            System.out.println("2. Acessar Conta");
            System.out.println("3. Consultar Saldo");
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
                            System.out.println(" ");
                            System.out.println("Conta criada com sucesso!");

                        } else if (opcao == 2) {
                            System.out.println("Informe o nome do titular da conta:");
                            String nome = scanner.nextLine();
                            System.out.println("Informe a taxa de investimento:");
                            double taxa = scanner.nextDouble();
                            System.out.println("Informe o prazo que você deseja manter seu investimento (em dias):");
                            int prazo = scanner.nextInt();
                            ContaInvestimento contaInvestimento = new ContaInvestimento(nome, taxa, prazo);

                            System.out.println("Conta de Investimento criada com sucesso!");

                        } else {
                            System.out.println("Opção inválida. Retornando ao menu principal.");
                            continue;
                        }

                    case 2:
                        System.out.println("Executando Opção B...");
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
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
