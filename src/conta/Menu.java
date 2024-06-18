package conta;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);

        ContaController contas = new ContaController();

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

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
             9 - Sair
             """);
            System.out.println("Entre com a opção desejada:");
            System.out.print(Cores.TEXT_RESET);

            try {
                opcao = scan.nextInt();
            }catch(InputMismatchException e){
                System.out.println("\nDigite valores inteiros!");
                scan.nextLine();
                opcao=0;
            }

            if (opcao == 9) {
                System.out.println(Cores.TEXT_WHITE_BOLD +"\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                sobre();
                scan.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta");

                    System.out.print("Digite o Numero da Agência: ");
                    agencia = scan.nextInt();
                    System.out.print("Digite o nome do Titular: ");
                    scan.skip("\\R?");
                    titular = scan.nextLine();

                    do {
                        System.out.print("Digite o tipo de Conta (1-Corrente ou 2-Poupança):");
                        tipo = scan.nextInt();
                    } while (tipo < 1 || tipo > 2);

                    System.out.print("Digite o saldo da conta (R$): ");
                    saldo = scan.nextFloat();

                    switch (tipo) {
                        case 1 -> {
                            System.out.print("Digite o limite de crédito (R$): ");
                            limite = scan.nextFloat();
                            contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                        }
                        case 2 -> {
                            System.out.print("Digite o dia do aniversario da Conta: ");
                            aniversario = scan.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                        }
                    }


                    keyPress();
                    break;

                case 2:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = scan.nextInt();

                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Atualizar dados da Conta\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = scan.nextInt();

                    var buscaConta = contas.buscarNaCollection(numero);

                    if (buscaConta != null){
                        tipo = buscaConta.getTipo();

                        System.out.println("Digite o numero da agência: ");
                        agencia = scan.nextInt();
                        System.out.println("Digite o nome do titular: ");
                        scan.skip("\\R?");
                        titular = scan.nextLine();

                        System.out.println("Digite o saldo da conta (R$): ");
                        saldo = scan.nextFloat();

                        switch (tipo) {
                            case 1 -> {
                                System.out.println("Digite o limite de crédito (R$): ");
                                limite = scan.nextFloat();

                                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
                            }
                            case 2 -> {
                                System.out.println("Digite o dia do aniversário da conta: ");
                                aniversario = scan.nextInt();

                                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, aniversario));
                            }
                            default -> System.out.println("Tipo de conta inválido!");
                        }
                    }else {
                        System.out.println("A conta não foi encontrada!");
                    }

                    keyPress();
                    break;
                case 5:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Apagar a Conta\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = scan.nextInt();
                    contas.deletar(numero);

                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Saque\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = scan.nextInt();

                    do {
                        System.out.println("Digite o valor do Saque (R$): ");
                        valor = scan.nextFloat();
                    }while (valor <= 0);
                    contas.sacar(numero, valor);

                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Depósito\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = scan.nextInt();

                    do {
                        System.out.println("Digite o valor do depósito (R$): ");
                        valor = scan.nextFloat();
                    }while (valor <=0);
                    contas.depositar(numero, valor);

                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE_BOLD +"Transferência entre Contas\n\n");
                    System.out.println("Digite o numero da conta de origem: ");
                    numero = scan.nextInt();
                    System.out.println("Digite o numero da conta de destino: ");
                    numeroDestino = scan.nextInt();

                    do {
                        System.out.println("Digite o valor da transfrencia (R$): ");
                        valor = scan.nextFloat();
                    }while (valor <= 0);

                    contas.transferir(numero,numeroDestino,valor);

                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD+"\nOpção Inválida!\n");
                    keyPress();
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
    public static void keyPress() {

        try {

            System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
            System.in.read();

        } catch (IOException e) {

            System.out.println("Você pressionou uma tecla diferente de enter!");

        }
    }
}
