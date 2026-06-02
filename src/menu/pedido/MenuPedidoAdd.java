package menu.pedido;

import java.util.Scanner;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import entidades.ItemPedido;
import servicos.PedidoSer;

public class MenuPedidoAdd {
    private final Scanner sca = new Scanner(System.in);

    public void novoPedido() {
        PedidoSer pedidoServico = new PedidoSer();

        int idCli = escolhaCliente();
        pedidoServico.addClientePedido(idCli);

        int idVend = escolhaVendedor();
        pedidoServico.addVendedorPedido(idVend);

        adicionadoProdutosPedido(pedidoServico);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total Atual do Carrinho: R$ %.2f%n", pedidoServico.getValorTotalAcumulado());
        System.out.println("\nDeseja:");
        System.out.println("1 - Finalizar pedido (Status: FILA)");
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
    
   public int escolhaCliente() {
        System.out.println("\n============================ LISTA DE CLIENTES ============================");
            
        new DAO.ClienteDAO().listarParaPedido(); 
        
        Scanner scaLocal = new Scanner(System.in);
        System.out.print("\nDigite o ID do cliente escolhido da lista acima: ");
        
        while (!scaLocal.hasNextInt()) {
            System.out.println("Digite apenas números inteiros para o ID!");
            scaLocal.nextLine();
            System.out.print("Digite o ID do cliente escolhido: ");
        }
        int idCli = scaLocal.nextInt();
        scaLocal.nextLine(); 
        
        return idCli;
    }

    public int escolhaVendedor() {
        System.out.println("\n============================ LISTA DE VENDEDORES ============================");
        new VendedorDAO().mostrarVendedor();
        
        System.out.print("\nDigite o ID do Vendedor escolhido: ");
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.nextLine();
            System.out.print("Digite o ID do Vendedor escolhido: ");
        }
        int idVend = sca.nextInt();
        sca.nextLine();
        
        return idVend;
    }

    public void adicionadoProdutosPedido(PedidoSer pedidoServico) {
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
            sca.nextLine();

            if (qtd <= 0) {
                System.out.println("Quantidade inválida!");
                continue;
            }

            boolean adicionado = pedidoServico.tentarAdicionarProduto(idProd, qtd);

            if (adicionado) {
                System.out.println("\n--- ITENS DO PEDIDO ATUAL ---");
                for (ItemPedido itemCarrinho : pedidoServico.getCarrinhoComponentes()) {
                    System.out.println(
                            "Produto ID: " + itemCarrinho.getIdProdutos() +
                            " | Quantidade: " + itemCarrinho.getQuantidade() +
                            " | Subtotal: R$ " + itemCarrinho.getSubtotal());
                }
            }

            System.out.print("\nDeseja adicionar outro produto? (s/n): ");
            continuar = sca.next();
            sca.nextLine();
        }
    }
}