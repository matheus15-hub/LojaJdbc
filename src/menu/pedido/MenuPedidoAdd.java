package menu.pedido;

import java.util.Scanner;

import DAO.ClientesDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import entidades.ItemPedido;
import menu.cliente.MenuClientePrint;
import servicos.Clienteser;
import servicos.PedidoServico;
import servicos.VendedorServico;

public class MenuPedidoAdd {
    Scanner sca = new Scanner(System.in);
      public void novoPedido() {
        PedidoServico pedidoServico = new PedidoServico();

        // 1. Seleção de Cliente
        int idCli;
        idCli = escolhaCliente();
        pedidoServico.addClientePedido(idCli);

        // 2. Seleção de Vendedor
       
        int idVend;
        idVend = escolhaVendedor();
        pedidoServico.addVendedorPedido(idVend);

        // 3. Loop do Carrinho
       adicionadoProdutosPedido(pedidoServico);

        sca.nextLine();
        System.out.print("Digite uma observação para o pedido (ou dê Enter para vazio): ");
        String observacao = sca.nextLine();
        pedidoServico.definirObservacao(observacao);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoServico.getValorTotalAcumulado());
        System.out.println("\nDeseja:");
        System.out.println("1 - Finalizar pedido (Status: EM_FILA)");
        System.out.println("2 - Deixar pedido em aberto (Status: ABERTO)");
        System.out.println("3 - Cancelar operação");
        System.out.print("Opção: ");

        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas o número da opção!");
            sca.nextLine();
            System.out.print("Opção: ");
        }
        int opcao = sca.nextInt();
        sca.nextLine();

        pedidoServico.finalizarFluxo(opcao);
    }
    
    public int escolhaCliente(){
        System.out.println("\n============================ LISTA DE CLIENTES ============================");
        new MenuClientePrint().metodoBusca();
        System.out.print("\nDigite o ID do cliente escolhido: ");
        
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.nextLine();
            System.out.print("Digite o ID do cliente escolhido: ");
        }
        int idCli = sca.nextInt();
        return new Clienteser().vereficarId(idCli);
    }


    public int escolhaVendedor(){
         System.out.println("\n============================ LISTA DE VENDEDORES ============================");
        new VendedorDAO().mostrarVendedor();
        System.out.print("Digite o ID do Vendedor escolhido: ");

        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.nextLine();
            System.out.print("Digite o ID do Vendedor escolhido: ");
        }
        int idVend = sca.nextInt();
        return new VendedorServico().vereficarId(idVend);
    }
    public void adicionadoProdutosPedido(PedidoServico pedidoServico){
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
            new ProdutoDAO().mostrarProduts();

            System.out.print("\nDigite o ID do Produto: ");
            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.nextLine();
                System.out.print("Digite o ID do Produto: ");
            }
            int idProd = sca.nextInt();

            System.out.print("Quantidade: ");
            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.nextLine();
                System.out.print("Quantidade: ");
            }
            int qtd = sca.nextInt();

            if (qtd <= 0) {
                System.out.println("Quantidade inválida!");
                continue;
            }

            boolean adicionado = pedidoServico.tentarAdicionarProduto(idProd, qtd);

            if (adicionado) {
                System.out.println("\n--- ITENS DO PEDIDO ---");
                for (ItemPedido itemCarrinho : pedidoServico.getCarrinhoComponentes()) {
                    System.out.println(
                            "Produto ID: " + itemCarrinho.getIdProdutos() +
                            " | Quantidade: " + itemCarrinho.getQuantidade() +
                            " | Subtotal: R$ " + itemCarrinho.getSubtotal());
                }
            }

            System.out.print("\nDeseja adicionar outro produto? (s/n): ");
            continuar = sca.next();
        }

    }
}