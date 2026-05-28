import menu.Menuadd;
import menu.MenuAlterar.AlterarProduto;
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
                    "███████╗██╗   ██3╗███████╗████████╗███████╗███╗   ███╗    ██████╗ ██████╗       ██████╗ ██████╗      ██╗    ██████╗ ");
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
            System.out.println("||\t\t\t\t O QUE DESEJA FAZER?\t\t\t\t\t||");
            linha();
            System.out.println("||1)Clientes\t2)PEDIDO\t3)PRODUTO\t4)VENDEDOR\t5)CONSULTAR\t6)SAIR\t||");
            linha();
            System.out.print("||ESCOLHA :");
            int escolha = sca.nextInt();
            sca.nextLine();
            linha();
            
            if (escolha == 1) {

                linha();
                System.out.println("||\t\t\t\t\tCLIENTES\t\t\t\t\t||");
                linha();
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)VOLTAR\t||");
                linha();
                System.out.print("||ESCOLHA : ");
                int cliente = sca.nextInt();
                sca.nextLine();
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
                        sca.nextLine();

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
                sca.nextLine();
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
                System.out.println("||1)CRIAR\t\t2)REMOVER\t\t3)CONSULTAR\t\t4)Alterar\t\t5)Voltar||");
                linha();
                System.out.print("||ESCOLHA : ");
                int produto = sca.nextInt();
                sca.nextLine();
                linha();

                switch (produto) {
                    case 1:
                        new menu.Menuadd().Produtoadd();
                        break;

                    case 2:
                        new menu.Menuremov().ProdutoRemov();
                        break;

                    case 3:
                        new Menuprint().metodoBusca();
                        break;
                        
                    case 4:
                        linha();
                        System.out.println("||\t\t\t\t\tAlterando\t\t\t\t\t||");
                        linha();
                        System.out.println("||1)Nome\t\t2)Valor\t\t3)Estoque\t\t4)Classe\t\t5)Unidade de Venda\t||");
                        linha();
                        System.out.print("||ESCOLHA : ");
                        int mudar = sca.nextInt();
                        sca.nextLine();
                        
                        switch (mudar){
                            case 1:
                                new AlterarProduto().nomeProduto();
                                break;
                            case 2:
                                new AlterarProduto().precoProduto();
                                break;
                            default:
                                System.out.println("Escolha inválida");
                                break;
                        }
                        break;
                        
                    case 5:
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
                sca.nextLine(); // Limpa o buffer
                linha();

                switch (vendedor) {
                    case 1:
                        new menu.Menuadd().Vendedoradd();
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

                System.out.println("\n=========================================================================================");
                System.out.println("||                            INICIANDO DASHBOARD GERAL DA LOJA                         ||");
                System.out.println("=========================================================================================");

                menu.Menuprint menuPrint = new menu.Menuprint();

                System.out.println("\n--> [LISTAGEM] CLIENTES CADASTRADOS:");
                menuPrint.printCliente();

                System.out.println("\n--> [LISTAGEM] EQUIPE DE VENDEDORES:");
                menuPrint.printVendedor();

                System.out.println("\n--> [LISTAGEM] HISTÓRICO DE PEDIDOS:");
                menuPrint.printPedido();

                System.out.println("\n--> [MÉTRICAS] RELATÓRIOS ANALÍTICOS DE VENDA:");
                DAO.PedidoDAO.relatorioVendasPorVendedor();
                DAO.PedidoDAO.relatorioProdutosMaisVendidos(); 
                
                System.out.println("\n=========================================================================================");
                System.out.println("||                            FIM DO DASHBOARD GERAL                                   ||");
                System.out.println("=========================================================================================");

            } else if (escolha == 6) {

                System.out.println("SAINDO DO SISTEMA...");
                break;

            } else {
                System.out.println("OPÇÃO INVÁLIDA!");
            }
        }

        sca.close();
    }

    public static void linha() {
        System.out.println("==========================================================================================");
    }
}