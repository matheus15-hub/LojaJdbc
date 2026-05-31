package menu;

import menu.MenuAlterar.AlterarCliente;
import menu.MenuAlterar.AlterarProduto;

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
                case 1: menuClientes();  break;
                case 2: menuPedidos();   break;
                case 3: menuProdutos();  break;
                case 4: menuVendedores(); break;
                case 5: menuDashboard(); break;
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
                new Menuadd().Clienteadd();
                break;

            case 2:
                new Menuremov().ClienteaRemov();
                break;

            case 3:
                menuConsultarCliente();
                break;

            case 4:
                menuAlterarCliente();
                break;

            case 5:
                System.out.println("VOLTANDO...");
                break;

            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private void menuConsultarCliente() {
        System.out.println("Consultar com filtro?");
        System.out.println("1 - SIM");
        System.out.println("2 - NÃO");
        int opcao = lerInt();

        switch (opcao) {
            case 1: new Menuprint().printClienteFiltro(); break;
            case 2: new Menuprint().printCliente();       break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    private void menuAlterarCliente() {
        linha();
        System.out.println("||                              ALTERANDO CLIENTE                              ||");
        linha();
        System.out.println("|| 1) Nome   2) CPF   3) Email                                                ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();

        switch (opcao) {
            case 1: new AlterarCliente().nomeCliente();  break;
            case 2: new AlterarCliente().cpfCliente();   break;
            case 3: new AlterarCliente().emailCliente(); break;
            default: System.out.println("Escolha inválida"); break;
        }
    }

   
    private void menuPedidos() {
        linha();
        System.out.println("||                              PEDIDOS                                        ||");
        linha();
        System.out.println("|| 1) Criar   2) Remover   3) Consultar   4) Voltar                           ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();
        linha();

        switch (opcao) {
            case 1: new Menuadd().novoPedido();       break;
            case 2: System.out.println("PEDIDO REMOVIDO!"); break;
            case 3: new Menuprint().printPedido();    break;
            case 4: System.out.println("VOLTANDO..."); break;
            default: System.out.println("OPÇÃO INVÁLIDA!"); break;
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
            case 1: new Menuadd().Produtoadd();         break;
            case 2: new Menuremov().ProdutoRemov();     break;
            case 3: new Menuprint().metodoBusca();      break;
            case 4: menuAlterarProduto();               break;
            case 5: System.out.println("VOLTANDO..."); break;
            default: System.out.println("OPÇÃO INVÁLIDA!"); break;
        }
    }

    private void menuAlterarProduto() {
        linha();
        System.out.println("||                              ALTERANDO PRODUTO                              ||");
        linha();
        System.out.println("|| 1) Nome   2) Preço                                                         ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = lerInt();

        switch (opcao) {
            case 1: new AlterarProduto().nomeProduto();  break;
            case 2: new AlterarProduto().precoProduto(); break;
            default: System.out.println("Escolha inválida"); break;
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
            case 1: new Menuadd().Vendedoradd();           break;
            case 2: System.out.println("VENDEDOR REMOVIDO!"); break;
            case 3: new Menuprint().printVendedor();       break;
            case 4: System.out.println("VOLTANDO...");    break;
            default: System.out.println("OPÇÃO INVÁLIDA!"); break;
        }
    }

   
    private void menuDashboard() {
        System.out.println("\n=============================================================================");
        System.out.println("||              INICIANDO DASHBOARD GERAL DA LOJA                         ||");
        System.out.println("=============================================================================");

        Menuprint menuPrint = new Menuprint();

        System.out.println("\n--> [LISTAGEM] CLIENTES CADASTRADOS:");
        menuPrint.printCliente();

        System.out.println("\n--> [LISTAGEM] EQUIPE DE VENDEDORES:");
        menuPrint.printVendedor();

        System.out.println("\n--> [LISTAGEM] HISTÓRICO DE PEDIDOS:");
        menuPrint.printPedido();

        System.out.println("\n--> [MÉTRICAS] RELATÓRIOS ANALÍTICOS DE VENDA:");
        DAO.PedidoDAO.relatorioVendasPorVendedor();
        DAO.PedidoDAO.relatorioProdutosMaisVendidos();

        System.out.println("\n=============================================================================");
        System.out.println("||                     FIM DO DASHBOARD GERAL                             ||");
        System.out.println("=============================================================================");
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