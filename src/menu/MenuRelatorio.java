package menu;
import menu.produto.MenuRelatorioProduto;

import java.util.Scanner;

public class MenuRelatorio {
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Relatórios===========================");
        System.out.println("1) Relatório de Produtos");
        System.out.print("Escolha:  ");
        Scanner scanner = new Scanner(System.in);
        int busca = scanner.nextInt();
        if (busca  == 1) {
            new MenuRelatorioProduto().metodoBusca();
            break;
        }else {
            System.out.println("===========================Escolha invalida!===========================");
            System.out.println("===========================Tente novamente===========================");
        }
        }
        
    }
    public void RelatorioProduto() {
        new MenuRelatorioProduto().metodoBusca();
    }
}
