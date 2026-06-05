package menu;
import menu.produto.MenuRelatorioProduto;
import menu.vendedor.MenuRelatorioVendedor;

import java.util.Scanner;

public class MenuRelatorio {
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Relatórios===========================");
        System.out.println("1) Relatório de Produtos 2) Relatório de Vendedores 3) Relatório de Pedidos 4) Relatório de Clientes");
        System.out.print("Escolha:  ");
        Scanner scanner = new Scanner(System.in);
        int busca = scanner.nextInt();
        if (busca  == 1) {
            new MenuRelatorioProduto().metodoBusca();
            break;
        }
        if (busca == 2) {
            new MenuRelatorioVendedor().metodoBusca();
            break;
        }
        if (busca == 3) {
            System.out.println("A ser feito");
            break;
        }
        if (busca == 4) {
            System.out.println("A ser feito");
            break;
        }
        else {
            System.out.println("===========================Escolha invalida!===========================");
            System.out.println("===========================Tente novamente===========================");
        }
        }
    }
    public void RelatorioProduto() {
        new MenuRelatorioProduto().metodoBusca();
    }
}
