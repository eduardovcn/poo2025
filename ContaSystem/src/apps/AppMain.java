//Define que a classe AppMain pertence ao pacote "apps"
//Pacotes servem para organizar o seu código em pastas lógicas
package apps;
import java.util.InputMismatchException;
import java.util.Scanner;
import contas.Conta;


// Importa a classe "Conta" que está dentro do pacote "contas".
// Isso é necessário para que a classe AppMain possa "enxergar" e usar a classe Conta.
import contas.ContaInvestimento;

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

        opcao = scanner.nextInt();
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Acessar Conta");
            System.out.println("3. Consultar Saldo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try{


                switch (opcao) {

                    case 1:
                        System.out.println("Informe o tipo de conta:");
                        System.out.println(" ");
                        System.out.println("1 = Conta Corrente");
                        System.out.println("2 = Conta Investimento");
                        opcao = scanner.nextInt();
                        if (opcao == 1) {
                            System.out.println("Informe o nome do titular da conta:");
                            String nome = scanner.nextLine();
                            Conta conta = new Conta(nome);

                            System.out.println("Conta criada com sucesso!");

                        }


                        break;
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
