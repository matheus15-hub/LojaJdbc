package menu.vendedor;
import DAO.Relatorio.RelatorioVendedorDAO;
import java.util.Scanner;

public class MenuRelatorioVendedor {
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Metodo de Busca===========================");
        System.out.println("Buscar:  1)Maior Venda, 2)Menor Venda, 3)Mais Vendas, 4)Menos Vendas, 5)Maior Salário, 6)Menor Salário, 7)Média de Venda");
        System.out.print("Escolha:  ");
        int busca = sca.nextInt();
        if (busca  == 1) {
            new RelatorioVendedorDAO().MaiorVendaVendedor();;
            break;
        }
        if (busca == 2) {
            new RelatorioVendedorDAO().MenorVendaVendedor();
            break;
        }/*
        if (busca == 3) {
            new RelatorioVendedorDAO().MaisVendasVendedor();
            break;
        }/*
        if (busca == 4) {
            new RelatorioVendedorDAO().MenosVendasVendedor();
            break;
        }/*
        if (busca == 5) {
            new RelatorioVendedorDAO().MaiorSalarioVendedor();
            break;
        }/*
        if (busca == 6) {
            new RelatorioVendedorDAO().MenorSalarioVendedor();
            break;
        }/*
        if (busca == 7) {
            new RelatorioVendedorDAO().MediaVendaVendedor();
            break;
    }*/

        }
    }
}