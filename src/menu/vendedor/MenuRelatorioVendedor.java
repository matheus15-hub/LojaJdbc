package menu.vendedor;
import DAO.Relatorio.RelatorioVendedorDAO;
import menu.MenuRelatorio;
import servicos.VendedorSer;
import util.Console;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuRelatorioVendedor {
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        try {
            while (true) {
            Console.linha();
            System.out.println("===========================Metodo de Busca===========================");
            System.out.println("|| Buscar Por:");
            System.out.println("|| 1) Maior Venda");
            System.out.println("|| 2) Menor Venda");
            System.out.println("|| 3) Mais Vendas");
            System.out.println("|| 4) Menos Vendas");
            System.out.println("|| 5) Maior Salário");
            System.out.println("|| 6) Menor Salário");
            System.out.println("|| 7) Média de Venda");
            System.out.println("|| 8) COMISSÂO");
            System.out.println("|| 9) Voltar");
        Console.linhaSimples();
              System.out.print("Escolha:  ");
              int busca = Integer.parseInt(sca.nextLine());;
              switch (busca) {
                  case 1:
                      new RelatorioVendedorDAO().MaiorVendaVendedor();
                      break;
                  case 2:
                      new RelatorioVendedorDAO().MenorVendaVendedor();
                      break;
                  case 3:
                      new RelatorioVendedorDAO().MaisVendasVendedor();
                      break;
                  case 4:
                      new RelatorioVendedorDAO().MenosVendasVendedor();
                      break;
                  case 5:
                      new RelatorioVendedorDAO().MaiorSalarioVendedor();
                      break;
                  case 6:
                      new RelatorioVendedorDAO().MenorSalarioVendedor();
                      break;
                  case 7:
                      new RelatorioVendedorDAO().MediaVendaVendedor();
                      break;
                  case 8:
                      new VendedorSer().comissaoVendedor();
                      break;
                  case 9:
                      new MenuRelatorio().metodoBusca();
                  default:
                      System.out.println("===========================Escolha invalida!===========================");
                      System.out.println("===========================Tente novamente===========================");

              }
            }
        } catch (NumberFormatException e) {
            sca.next();
            Console.linhaSimples();
            System.out.println("\t Entrada de Dados Invalidas! Apenas Numeros Inteiros Como 1,2,3..\n\t\t\t Tente Novamente");
        }
    }
}
