
import DAO.MedidaDao;
import menu.Menuadd;
import entidades.Clientes;
import entidades.Produto;
import menu.Menuprint;
import menu.Menuremov;
import threads.ProcessadorPedido;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sca = new Scanner(System.in);

        ProcessadorPedido filaProcessamento = new ProcessadorPedido();
        filaProcessamento.setDaemon(true);
        filaProcessamento.start();
        while (true) {

            System.out.println(
                    "███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗    ██████╗ ██████╗       ██████╗ ██████╗      ██╗    ██████╗ ");
            System.out.println(
                    "██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║    ██╔══██╗╚════██╗      ██╔══██╗╚════██╗    ███║   ██╔═████╗");
            System.out.println(
                    "███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║    ██████╔╝ █████╔╝█████╗██║  ██║ █████╔╝    ╚██║   ██║██╔██║");
            System.out.println(
                    "╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║    ██╔══██╗ ╚═══██╗╚════╝██║  ██║ ╚═══██╗     ██║   ████╔╝██║");
            System.out.println(
                    "███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║    ██║  ██║██████╔╝      ██████╔╝██████╔╝     ██║██╗╚██████╔╝");
            System.out.println(
                    "╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝    ╚═╝  ╚═╝╚═════╝       ╚═════╝ ╚═════╝      ╚═╝╚═╝ ╚═════╝  \n\n");

            linha();
            System.out.println("||\t\t\t\t O QUE DEJESA FAZER?\t\t\t\t\t||");
            linha();
            System.out.println("||1)Clientes\t2)PEDIDO\t3)PRODUTO\t4)VENDEDOR\t5)CONSULTAR\t6)SAIR\t||");
            linha();
            System.out.print("||ESCOLHA :");
            int escolha = sca.nextInt();
            linha();
            if (escolha == 1) {

                linha();
                System.out.println("||\t\t\t\t\tCLIENTES\t\t\t\t\t||");
                linha();
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)VOLTAR\t||");
                linha();
                System.out.print("||ESCOLHA : ");
                int cliente = sca.nextInt();
                linha();

                switch (cliente) {

                    case 1:
                        new menu.Menuadd().Clienteadd();
                        break;

                    case 2:
                        new menu.Menuremov().ClienteaRemov();
                        break;

                    case 3:
                        int opcao = 0;

                        System.out.println("Consultar com filtro?");
                        System.out.println("1 - SIM");
                        System.out.println("2 - NÃO");

                        while (!sca.hasNextInt()) {
                            System.out.println("Digite apenas números!");
                            sca.next();
                        }

                        opcao = sca.nextInt();

                        switch (opcao) {

                            case 1:
                                new menu.Menuprint().printClienteFiltro();
                                break;

                            case 2:
                                new menu.Menuprint().printCliente();
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                        break;

                    case 4:
                        System.out.println("VOLTANDO...");
                        break;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }

            } else if (escolha == 2) {

                linha();
                System.out.println("||\t\t\t\t\tPEDIDOS\t\t\t\t\t\t||");
                linha();
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)VOLTAR\t||");
                linha();
                System.out.print("||ESCOLHA : ");
                int pedido = sca.nextInt();
                linha();

                switch (pedido) {

                    case 1:
                        new Menuadd().novoPedido();
                        break;

                    case 2:
                        System.out.println("PEDIDO REMOVIDO!");
                        break;

                    case 3:
                        new Menuprint().printPedido();
                        break;

                    case 4:
                        System.out.println("VOLTANDO...");
                        break;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }

            } else if (escolha == 3) {

                linha();
                System.out.println("||\t\t\t\t\tPRODUTOS\t\t\t\t\t||");
                linha();
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)VOLTAR\t||");
                linha();
                System.out.print("||ESCOLHA : ");
                int produto = sca.nextInt();
                linha();

                switch (produto) {

                    case 1:
                        new menu.Menuadd().Produtoadd();
                        break;

                    case 2:
                        new Menuremov().ProdutoRemov();
                        break;

                    case 3:
                        sca.nextLine();
                        System.out.print("Busca: ");
                        String busca = sca.nextLine();
                        new Menuprint().metodoBusca(busca);
                        break;

                    case 4:
                        System.out.println("VOLTANDO...");
                        break;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }

            } else if (escolha == 4) {

                linha();
                System.out.println("||\t\t\t\t\tVENDEDORES\t\t\t\t\t||");
                linha();
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)VOLTAR\t||");
                linha();
                System.out.print("||ESCOLHA : ");
                int vendedor = sca.nextInt();
                linha();

                switch (vendedor) {

                    case 1:
                        new menu.Menuadd().Vendedoradd();
                        // System.out.println("CLIENTE CADASTRADO!");
                        break;

                    case 2:
                        System.out.println("VENDEDOR REMOVIDO!");
                        break;

                    case 3:
                        new Menuprint().printVendedor();
                        break;

                    case 4:
                        System.out.println("VOLTANDO...");
                        break;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }

            } else if (escolha == 5) {

                linha();
                System.out.println("||\t\t\t\t\tCONSULTAS\t\t\t\t\t||");
                linha();
                System.out.println("||1)CLIENTES\t  2)PEDIDOS\t  3)PRODUTOS\t  4)VENDEDOR\t  5)VOLTAR\t\t||");
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
                        System.out.println("LISTANDO VENDEDORES...");
                        break;
                    case 5:
                        System.out.println("VOLTANDO...");
                        break;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }

            } else if (escolha == 6) {

                System.out.println("SAINDO DO SISTEMA...");
                break;

            }

            else {

                System.out.println("OPÇÃO INVÁLIDA!");
            }

        }

        sca.close();
    }

    public static void linha() {
        System.out
                .println("==========================================================================================");
    }
}
