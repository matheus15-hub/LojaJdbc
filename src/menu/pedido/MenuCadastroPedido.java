package menu.pedido;

import java.util.Scanner;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import entidades.ItemPedido;
import servicos.PedidoService;

public class MenuCadastroPedido {

    private final Scanner sca = new Scanner(System.in);

    public void novoPedido() {
        PedidoService pedidoService = new PedidoService();

        int idCliente = escolherCliente();
        pedidoService.addClientePedido(idCliente);

        int idVendedor = escolherVendedor();
        pedidoService.addVendedorPedido(idVendedor);

        adicionarProdutos(pedidoService);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoService.getValorTotalAcumulado());

        System.out.println("1 - Enviar para FILA");
        System.out.println("2 - Salvar como ABERTO");
        System.out.println("3 - Cancelar");

        int opcao = lerInteiro();

        pedidoService.finalizarFluxo(opcao);
    }

    private int escolherCliente() {
        System.out.println("\n--- CLIENTES ---");

        new DAO.ClienteDAO().listarParaPedido();

        System.out.print("ID Cliente: ");

        return lerInteiro();
    }

    private int escolherVendedor() {
        System.out.println("\n--- VENDEDORES ---");

        new VendedorDAO().mostrarVendedor();

        System.out.print("ID Vendedor: ");

        return lerInteiro();
    }

    private void adicionarProdutos(PedidoService pedidoService) {

        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {

            new ProdutoDAO().listarProdutos();

            System.out.print("ID Produto: ");
            int idProduto = lerInteiro();

            System.out.print("Quantidade: ");
            int quantidade = lerInteiro();

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida.");
                continue;
            }

            boolean adicionou =
                    pedidoService.tentarAdicionarProduto(idProduto, quantidade);

            if (adicionou) {

                System.out.println("\n--- CARRINHO ---");

                for (ItemPedido item : pedidoService.getCarrinhoComponentes()) {

                    System.out.println(
                            "Produto: " + item.getIdProdutos()
                            + " | Qtd: " + item.getQuantidade()
                            + " | Subtotal: R$ " + item.getSubtotal()
                    );
                }
            }

            System.out.print("Adicionar outro produto? (s/n): ");
            continuar = sca.nextLine();
        }
    }

    private int lerInteiro() {
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números.");
            sca.nextLine();
        }

        int valor = sca.nextInt();
        sca.nextLine();

        return valor;
    }
}