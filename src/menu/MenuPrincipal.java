package menu;

import menu.cliente.MenuCadastroCliente;
import menu.cliente.MenuAlteracaoCliente;
import menu.cliente.MenuConsultaCliente;
import menu.cliente.MenuRemocaoCliente;

import menu.pedido.MenuAlteracaoPedido;
import menu.pedido.MenuCadastroPedido;
import menu.pedido.MenuConsultaPedido;

import menu.produto.MenuAlteracaoProduto;
import menu.produto.MenuConsultaProduto;
import menu.produto.MenuRemocaoProduto;
import menu.produto.MenuCadastroProduto;

import menu.vendedor.MenuAlteracaoVendedor;
import menu.vendedor.MenuCadastroVendedor;
import menu.vendedor.MenuConsultaVendedor;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner sca = new Scanner(System.in);

    public void iniciar() {
            exibirCabecalho();
            linha();
            System.out.println("|| O QUE DESEJA FAZER?                                         ||");
            linha();
            System.out.println("|| 0) Voltar                                                   ||");
            System.out.println("|| 1) Clientes                                                 ||");
            System.out.println("|| 2) Pedidos                                                  ||");
            System.out.println("|| 3) Produtos                                                 ||");
            System.out.println("|| 4) Vendedores                                               ||");
            System.out.println("|| 5) Consultar                                                ||");
            System.out.println("|| 6) Relatórios                                               ||");
            linha();
            System.out.print("|| ESCOLHA: ");
            int escolha = lerInt();
            linha();

        switch (escolha) {
            case 1:
                menuClientes();
                break;
            case 2:
                menuPedidos();
                break;
            case 3:
                menuProdutos();
                break;
            case 4:
                menuVendedores();
                break;
            case 5:
                System.out.println("A ser feito");
                break;
            case 6:
                new MenuRelatorio().metodoBusca();
                break;
            case 0:
                System.out.println("SAINDO DO SISTEMA...");
                return;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private void menuClientes() {
            linha();
            System.out.println("|| CLIENTES                                                    ||");
            linha();
            System.out.println("|| 0) Voltar                                                   ||");
            System.out.println("|| 1) Criar                                                    ||");
            System.out.println("|| 2) Remover                                                  ||");
            System.out.println("|| 3) Consultar                                                ||");
            System.out.println("|| 4) Alterar                                                  ||");
            linha();
            System.out.print("|| ESCOLHA: ");
            int opcao = lerInt();
            linha();

            switch (opcao) {
                case 1:
                    new MenuCadastroCliente().Clienteadd();
                    menuClientes();
                    break;

                case 2:
                    new MenuRemocaoCliente().clienteRemover();
                    menuClientes();
                    break;

                case 3:
                    new MenuConsultaCliente().metodoBusca();
                    menuClientes();
                    break;

                case 4:
                    new MenuAlteracaoCliente().menuAlterarCliente();
                    menuClientes();
                    break;

                case 0:
                    System.out.println("VOLTANDO...");
                    iniciar();
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        }

    private void menuPedidos() {
            linha();
            System.out.println("|| PEDIDOS                                                     ||");
            linha();

            System.out.println("|| 0) Voltar                                                   ||");
            System.out.println("|| 1) Criar Pedido                                             ||");
            System.out.println("|| 2) Alterar Pedido                                           ||");
            System.out.println("|| 3) Consultar Pedido                                         ||");

            linha();

            System.out.print("ESCOLHA: ");

            int opcao = lerInt();

            switch (opcao) {

                case 1:
                    new MenuCadastroPedido().novoPedido();
                    menuPedidos();
                    break;

                case 2:
                    new MenuAlteracaoPedido().alterarPedido();
                    menuPedidos();
                    break;

                case 3:
                    new MenuConsultaPedido().consultarPedidos();
                    menuPedidos();
                    break;

                case 0:
                    System.out.println("VOLTANDO...");
                    iniciar();
                    return;

                default:
                    System.out.println("Opção inválida.");
        }
    }

    private void menuProdutos() {
        linha();
        System.out.println("|| PRODUTOS                                                     ||");
        linha();
        System.out.println("|| 0) Voltar                                                   ||");
        System.out.println("|| 1) Criar                                                    ||");
        System.out.println("|| 2) Remover                                                  ||");
        System.out.println("|| 3) Consultar                                                ||");
        System.out.println("|| 4) Alterar                                                  ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1:
                new MenuCadastroProduto().Produtoadd();
                menuProdutos();
                break;
            case 2:
                new MenuRemocaoProduto().produtoRemover();
                menuProdutos();
                break;
            case 3:
                new MenuConsultaProduto().metodoBusca();
                menuProdutos();
                break;
            case 4:
                new MenuAlteracaoProduto().menuAlterarProduto();
                menuProdutos();
                break;
            case 0:
                System.out.println("VOLTANDO...");
                iniciar();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
                break;
        }
    }

    private void menuVendedores() {
        linha();
        System.out.println("|| VENDEDORES                                                       ||");
        linha();
        System.out.println("|| 0) Voltar                                                        ||");
        System.out.println("|| 1) Criar                                                         ||");
        System.out.println("|| 2) Remover                                                       ||");
        System.out.println("|| 3) Consultar                                                     ||");
        System.out.println("|| 4) Alterar                                                       ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1:
                new MenuCadastroVendedor().Vendedoradd();
                menuVendedores();
                break;
            case 2:
                System.out.println("VENDEDOR REMOVIDO!");
                break;
            case 3:
                new MenuConsultaVendedor().metodoBusca();
                menuVendedores();
                break;
            case 4:
               new MenuAlteracaoVendedor().menuAlterarVendedor();
                menuVendedores();
                break;
            case 0:
                System.out.println("VOLTANDO...");
                iniciar();
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
                break;
        }
    }

    private int lerInt() {
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.next();
            System.out.print("ESCOLHA: ");
        }
        int valor = sca.nextInt();
        sca.nextLine();
        return valor;
    }

    public static void linha() {
        System.out.println("=================================================================");
    }

    private void exibirCabecalho() {
        System.out.println("███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗");
        System.out.println("██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║");
        System.out.println("███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║");
        System.out.println("╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║");
        System.out.println("███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║");
        System.out.println("╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝\n");
    }
}