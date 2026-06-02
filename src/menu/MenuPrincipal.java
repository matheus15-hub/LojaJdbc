package menu;
import menu.cliente.MenuClienteAdd;
import menu.cliente.MenuClienteAlterar;
import menu.cliente.MenuClientePrint;
import menu.cliente.MenuClienteRemover;
import menu.pedido.MenuPedidoAdd;
import menu.produto.MenuProdutoAlterar;
import menu.produto.MenuProdutoPrint;
import menu.produto.MenuProdutoRemover;
import menu.produto.MenuProdutoadd;
import menu.vendedor.MenuVendedorAdd;
import menu.pedido.MenuPedidoAlterar;
import menu.pedido.MenuPedidoPrint;
import menu.pedido.MenuPedidoRemover;

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
                case 5: System.out.println("A ser feito");; break;
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
                new MenuClienteAdd().Clienteadd();;
                break;

            case 2:
                new MenuClienteRemover().clienteRemover();
                break;

            case 3:
                new MenuClientePrint().metodoBusca();
                break;

            case 4:
                new MenuClienteAlterar().menuAlterarCliente();
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
        System.out.println("||                               PEDIDOS                                        ||");
        linha();
        System.out.println("|| 1) Criar   2) Remover   3) Alterar   4) Consultar   5) Voltar               ||");
        linha();
        System.out.print("|| ESCOLHA: ");
        int opcao = sca.nextInt();
        sca.nextLine(); // Garante a limpeza do buffer do scanner

        switch(opcao) {
            case 1:
                new MenuPedidoAdd().novoPedido();
                break;
            case 2:
                new menu.pedido.MenuPedidoRemover().removerPedido();
                break;
            case 3: 
                new menu.pedido.MenuPedidoAlterar().alterarPedido();
                break;
            case 4:
                new MenuPedidoPrint().exibirMenuPrint();
                break;
            case 5:
                System.out.println("Voltando...");
                return;
            default:
                System.out.println("Opção inválida!");
                break;
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
            case 1: new MenuProdutoadd().Produtoadd();
            break;
            case 2: new MenuProdutoRemover().produtoRemover();;  
            break;
            case 3: new MenuProdutoPrint().metodoBusca();  
            break;
            case 4: new MenuProdutoAlterar().menuAlterarProduto();
            break;
            case 5: System.out.println("VOLTANDO..."); 
            break;
            default: System.out.println("OPÇÃO INVÁLIDA!"); 
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
            case 1: new MenuVendedorAdd().Vendedoradd();
                       break;
            case 2: System.out.println("VENDEDOR REMOVIDO!"); 
            break;
            case 3: System.out.print("\na ser feito");;      
             break;
            case 4: System.out.println("VOLTANDO...");   
             break;
            default: System.out.println("OPÇÃO INVÁLIDA!"); 
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