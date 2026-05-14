
import menu.Menuadd;
import entidades.Clientes;
import entidades.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner  sca = new Scanner(System.in);
        System.out.println("███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗    ██████╗ ██████╗       ██████╗ ██████╗      ██╗    ██████╗ ");
        System.out.println("██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║    ██╔══██╗╚════██╗      ██╔══██╗╚════██╗    ███║   ██╔═████╗");
        System.out.println("███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║    ██████╔╝ █████╔╝█████╗██║  ██║ █████╔╝    ╚██║   ██║██╔██║");
        System.out.println("╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║    ██╔══██╗ ╚═══██╗╚════╝██║  ██║ ╚═══██╗     ██║   ████╔╝██║");
        System.out.println("███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║    ██║  ██║██████╔╝      ██████╔╝██████╔╝     ██║██╗╚██████╔╝");
        System.out.println("╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝    ╚═╝  ╚═╝╚═════╝       ╚═════╝ ╚═════╝      ╚═╝╚═╝ ╚═════╝  \n\n");
        System.out.println("Adicionando um Produto");
        System.out.println("=========================================");



        linha();
        System.out.println("||\t\t\t\t\t O QUE DEJESA FAZER?\t\t\t\t\t||");
        linha();
        System.out.println("||1)Clientes\t2)PEDIDO\t3)PRODUTO\t4)CONSULTAR\t5)SAIR\t||");
        linha();
        System.out.print("||ESCOLHA :");
        int escolha = sca.nextInt();
        linha();
        if (escolha == 1) {

            linha();
            System.out.println("||\t\t\t\t\t\t\tCLIENTES\t\t\t\t\t\t||");
            linha();
            System.out.println("||1)CADASTRAR\t2)REMOVER\t3)CONSULTAR\t\t4)VOLTAR\t\t||");
            linha();
            System.out.print("||ESCOLHA : ");
            int cliente = sca.nextInt();
            linha();

            switch (cliente) {

                case 1:
                    new menu.Menuadd().Clienteadd();
                    //System.out.println("CLIENTE CADASTRADO!");
                    break;

                case 2:
                    System.out.println("CLIENTE REMOVIDO!");
                    break;

                case 3:
                    System.out.println("CONSULTANDO CLIENTE...");
                    break;

                case 4:
                    System.out.println("VOLTANDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        } else if (escolha == 2) {

            linha();
            System.out.println("||\t\t\t\t\t\t\tPEDIDOS\t\t\t\t\t\t\t||");
            linha();
            System.out.println("||1)CRIAR\t2)REMOVER\t3)CONSULTAR\t\t4)VOLTAR\t\t\t||");
            linha();
            System.out.print("||ESCOLHA : ");
            int pedido = sca.nextInt();
            linha();

            switch (pedido) {

                case 1:
                    new Menuadd().novoPedido();
                    //System.out.println("PEDIDO CRIADO!");
                    break;

                case 2:
                    System.out.println("PEDIDO REMOVIDO!");
                    break;

                case 3:
                    System.out.println("CONSULTANDO PEDIDO...");
                    break;

                case 4:
                    System.out.println("VOLTANDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        } else if (escolha == 3) {

            linha();
            System.out.println("||\t\t\t\t\t\t\tPRODUTOS\t\t\t\t\t\t||");
            linha();
            System.out.println("||1)CADASTRAR\t2)REMOVER\t3)CONSULTAR\t\t4)VOLTAR\t\t||");
            linha();
            System.out.print("||ESCOLHA : ");
            int produto = sca.nextInt();
            linha();

            switch (produto) {

                case 1:
                    System.out.println("PRODUTO CADASTRADO!");
                    break;

                case 2:
                    System.out.println("PRODUTO REMOVIDO!");
                    break;

                case 3:
                    System.out.println("CONSULTANDO PRODUTO...");
                    break;

                case 4:
                    System.out.println("VOLTANDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        } else if (escolha == 4) {

            linha();
            System.out.println("||\t\t\t\t\t\t\tCONSULTAS\t\t\t\t\t\t||");
            linha();
            System.out.println("||1)CLIENTES\t2)PEDIDOS\t3)PRODUTOS\t\t4)VOLTAR\t\t||");
            linha();
            System.out.print("||ESCOLHA : ");
            int consulta = sca.nextInt();
            linha();

            switch (consulta) {

                case 1:
                    System.out.println("LISTANDO CLIENTES...");
                    break;

                case 2:
                    System.out.println("LISTANDO PEDIDOS...");
                    break;

                case 3:
                    System.out.println("LISTANDO PRODUTOS...");
                    break;

                case 4:
                    System.out.println("VOLTANDO...");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }

        } else if (escolha == 5) {

            System.out.println("SAINDO DO SISTEMA...");

        } else {

            System.out.println("OPÇÃO INVÁLIDA!");
        }

    }



    public static void linha(){System.out.println("==============================================================");}
}
