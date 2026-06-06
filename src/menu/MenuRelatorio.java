package menu;
import menu.produto.MenuRelatorioProduto;
import menu.vendedor.MenuRelatorioVendedor;
import menu.pedido.MenuRelatorioPedido;
import menu.cliente.MenuRelatorioCliente;
import util.Console;

import java.util.Scanner;

public class MenuRelatorio {
     public void metodoBusca() {
        while (true) {
            System.out.println("|| RELATÓRIOS                                                  ||");
            System.out.println("|| 1) Relatório de Produtos                                    ||");
            System.out.println("|| 2) Relatório de Vendedores                                  ||");
            System.out.println("|| 3) Relatório de Pedidos                                     ||");
            System.out.println("|| 4) Relatório de Clientes                                    ||");
            System.out.println("|| 5) Voltar                                                   ||");
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
            case 5:
                new MenuRelatorio().metodoBusca();
                break;
            default:
                System.out.println("Escolha inválida!");
        }
        scanner.close();
        }
    }
    public void RelatorioProduto() {
        new MenuRelatorioProduto().metodoBusca();
    }
}
