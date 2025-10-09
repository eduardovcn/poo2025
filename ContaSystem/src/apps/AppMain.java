//Define que a classe AppMain pertence ao pacote "apps"
//Pacotes servem para organizar o seu código em pastas lógicas
package apps;


// Importa a classe "Conta" que está dentro do pacote "contas".
// Isso é necessário para que a classe AppMain possa "enxergar" e usar a classe Conta.
import contas.Conta;

// Este é o método principal (main), o ponto de entrada de qualquer programa Java.
// Quando você executa o programa, o código dentro deste método é o primeiro a rodar.
public class AppMain {
    public static void main(String[] args) {
        // Conta conta01: Declara uma variável chamada "conta01" que vai guardar um objeto do tipo "Conta".
        // Esta é a parte que efetivamente cria o objeto.
        //    - O comando "new" aloca memória para um novo objeto.
        //    - "Conta()" é a chamada ao "construtor" da classe Conta.
        //    O construtor é um método especial que inicializa o objeto recém-criado.
        Conta conta01 = new Conta();
    }
}
