package menu;

import menu.cliente.MenuCadastroCliente;
import menu.cliente.MenuAlteracaoCliente;
import menu.cliente.MenuConsultaCliente;
import menu.cliente.MenuRemocaoCliente;
import menu.pedido.MenuAlteracaoPedido;
import menu.pedido.MenuCadastroPedido;
import menu.produto.MenuCadastroProduto;
import menu.produto.MenuConsultaProduto;
import menu.produto.MenuRemocaoProduto;
import menu.produto.MenuAlteracaoProduto;
import menu.vendedor.MenuAlteracaoVendedor;
import menu.pedido.MenuConsultaPedido;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner sca = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            exibirCabecalho();
            linha();
            System.out.println("||                          O QUE DESEJA FAZER?                               ||");
            linha();
            System.out.println("|| 1) Clientes   2) Pedido   3) Produto   4) Vendedor   5) Consultar  6) Sair ||");
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
                    ;
                    break;
                case 6:
                    System.out.println("SAINDO DO SISTEMA...");
                    return;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
    }

    private void menuClientes() {
        linha();
        System.out.println("||                              CLIENTES                                       ||");
        linha();
        System.out.println("|| 1) Criar   2) Remover   3) Consultar   4) Alterar   5) Voltar              ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1:
                new MenuCadastroCliente().Clienteadd();
                ;
                break;

            case 2:
                new MenuRemocaoCliente().clienteRemover();
                break;

            case 3:
                new MenuConsultaCliente().metodoBusca();
                break;

            case 4:
                new MenuAlteracaoCliente().menuAlterarCliente();
                break;

            case 5:
                System.out.println("VOLTANDO...");
                break;

            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private void menuPedidos() {

        linha();
        System.out.println("|| PEDIDOS                                                     ||");
        linha();

        System.out.println("|| 1) Criar Pedido                                             ||");
        System.out.println("|| 2) Alterar Pedido                                           ||");
        System.out.println("|| 3) Consultar Pedido                                         ||");
        System.out.println("|| 4) Voltar                                                   ||");

        linha();

        System.out.print("ESCOLHA: ");

        int opcao = lerInt();

        switch (opcao) {

            case 1:
                new MenuCadastroPedido().novoPedido();
                break;

            case 2:
                new MenuAlteracaoPedido().alterarPedido();
                break;

            case 3:
                new MenuConsultaPedido().consultarPedidos();
                break;

            case 4:
                return;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private void menuProdutos() {
        linha();
        System.out.println("||                              PRODUTOS                                       ||");
        linha();
        System.out.println("|| 1) Criar   2) Remover   3) Consultar   4) Alterar   5) Voltar              ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1:
                new MenuAlteracaoProduto().Produtoadd();
                break;
            case 2:
                new MenuRemocaoProduto().produtoRemover();
                ;
                break;
            case 3:
                new MenuConsultaProduto().metodoBusca();
                break;
            case 4:
                new MenuCadastroProduto().menuAlterarProduto();
                break;
            case 5:
                System.out.println("VOLTANDO...");
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
                break;
        }
    }

    private void menuVendedores() {
        linha();
        System.out.println("||                              VENDEDORES                                     ||");
        linha();
        System.out.println("|| 1) Criar   2) Remover   3) Consultar   4) Voltar                           ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1:
                new MenuAlteracaoVendedor().Vendedoradd();
                break;
            case 2:
                System.out.println("VENDEDOR REMOVIDO!");
                break;
            case 3:
                System.out.print("\na ser feito");
                ;
                break;
            case 4:
                System.out.println("VOLTANDO...");
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
        System.out.println("==============================================================================");
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