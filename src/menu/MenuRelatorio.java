package menu;
import menu.produto.MenuRelatorioProduto;
import menu.vendedor.MenuRelatorioVendedor;
import menu.pedido.MenuRelatorioPedido;
import menu.cliente.MenuRelatorioCliente;

import java.util.Scanner;

public class MenuRelatorio {
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Relatórios===========================");
        System.out.println("1) Relatório de Produtos 2) Relatório de Vendedores 3) Relatório de Pedidos 4) Relatório de Clientes");
        System.out.print("Escolha:  ");
        Scanner scanner = new Scanner(System.in);
        int busca = scanner.nextInt();
        switch (busca) {
            case 1:
                new MenuRelatorioProduto().metodoBusca();
                break;
            case 2:
                new MenuRelatorioVendedor().metodoBusca();
                break;
            case 3:
                new MenuRelatorioPedido().metodoBusca();
                break;
            case 4:
                new MenuRelatorioCliente().metodoBusca();
                break;
            default:
                break;
        }
        scanner.close();
        }
    }
    public void RelatorioProduto() {
        new MenuRelatorioProduto().metodoBusca();
    }
}
