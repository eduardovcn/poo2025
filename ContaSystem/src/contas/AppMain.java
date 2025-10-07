package contas;

public class AppMain {
    public static void main(String[] args) {

        Conta conta01 = new Conta(); // Conta() é o construtor
        Conta conta02 = new Conta();

        conta01.nome = "Eduardo Vitor";
        conta01.numero = 1;
        conta01.limite = 100000;
        conta01.saldo = 10000;

        conta02.nome = "Joelho de Bronze";
        conta02.numero = 2;
        conta02.limite = 200000;
        conta02.saldo = 20000;

        System.out.println(conta01);
        System.out.println();
        System.out.println(conta02);

        conta01.sacar(1000);
        System.out.println(conta01.saldo);

        conta02.sacar(250);
        System.out.println(conta02.saldo);

        conta01.depositar(2225);
        System.out.println(conta01.saldo);

        conta02.depositar(4550);
        System.out.println(conta02.saldo);

    }
}
