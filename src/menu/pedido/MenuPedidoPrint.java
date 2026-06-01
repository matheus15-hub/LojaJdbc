package menu.pedido;

import java.util.Scanner;
import DAO.PedidoDAO;

public class MenuPedidoPrint {
    private Scanner sca = new Scanner(System.in);

    public void exibirMenuPrint() {
        while (true) {
            System.out.println("\n--- CONSULTAS DE PEDIDOS ---");
            System.out.println("1 - Listar Todos os Pedidos (Detalhado)");
            System.out.println("2 - Relatório: Faturamento por Vendedor");
            System.out.println("3 - Relatório: Produtos Mais Vendidos");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.next();
            }
            int opcao = sca.nextInt();
            sca.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    PedidoDAO.imprimirPedidoS();
                    break;
                case 2:
                    PedidoDAO.relatorioVendasPorVendedor();
                    break;
                case 3:
                    PedidoDAO.relatorioProdutosMaisVendidos();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}