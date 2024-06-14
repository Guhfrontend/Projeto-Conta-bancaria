package conta;

import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int opcao;

        ContaPoupanca c1 = new ContaPoupanca(1, 123, 1, "Gustavo", 5000,100);
        c1.visualizar();
        c1.sacar(12000);
        c1.visualizar();
        c1.deposito(5000);
        c1.visualizar();

        ContaCorrente cc1 = new ContaCorrente(2,123,1,"Yuri", 15000,1000);
        cc1.visualizar();
        cc1.sacar(16000);
        cc1.visualizar();
        cc1.deposito(5000);
        cc1.visualizar();

        ContaPoupanca cp1 = new ContaPoupanca(3,123,2,"Camilla", 100000,15);
        cp1.visualizar();
        cp1.sacar(1000);
        cp1.visualizar();
        cp1.deposito(5000);
        cp1.visualizar();



        while (true) {

            System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND);
            System.out.println("""
*****************************************************

            BANCO DO BRAZIL COM Z
*****************************************************

             1 - Criar Conta
             2 - Listar todas as Contas
             3 - Buscar Conta por Numero
             4 - Atualizar Dados da Conta
             5 - Apagar Conta
             6 - Sacar
             7 - Depositar
             8 - Transferir valores entre Contas
             9 - Sair""");

            System.out.println("Entre com a opção desejada:");
            opcao = scan.nextInt();

            if (opcao == 9) {
                System.out.println(Cores.TEXT_WHITE_BOLD +"\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                sobre();
                scan.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Criar Conta\n\n");

                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Listar todas as Contas\n\n");

                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Consultar dados da Conta - por número\n\n");

                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Atualizar dados da Conta\n\n");

                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Apagar a Conta\n\n");

                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Saque\n\n");

                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Depósito\n\n");

                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Transferência entre Contas\n\n");

                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD+"\nOpção Inválida!\n");
                    break;
            }
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: Gustavo Ribeiro da Silva");
        System.out.println("Generation Brasil - generation@generation.org");
        System.out.println("https://github.com/Guhfrontend/Projeto-Conta-bancaria");
        System.out.println("*********************************************************");
    }

}
