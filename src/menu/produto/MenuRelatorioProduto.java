package menu.produto;
import java.util.Scanner;
import DAO.Relatorio.RelatorioProdutoDAO;
import menu.MenuRelatorio;
import util.Console;

public class MenuRelatorioProduto {
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
            while (true) {
        try {
            Console.linha();
            System.out.println("||\t\t\t! Metodo de Busca !");
            System.out.println("|| Buscar Por:");
            System.out.println("|| 1) Maior quantidade");
            System.out.println("|| 2) Maior caro");
            System.out.println("|| 3) Mais barato");
            System.out.println("|| 4) Mias Vendidos");
            System.out.println("|| 5) Quantidade por Categoria");
            System.out.println("|| 6) Valor Médio");
            System.out.println("|| 7) Voltar");
            Console.linhaSimples();
                System.out.print("Escolha:  ");
                int busca = Integer.parseInt(sca.nextLine());;
                switch (busca) {
                    case 1:
                        new RelatorioProdutoDAO().MaiorQuantidadeProduto();
                    break;
                    case 2:
                        new RelatorioProdutoDAO().MaisCaroProduto();
                    break;
                     case 3:
                        new RelatorioProdutoDAO().MaisBaratoProduto();
                    break;
                     case 4:
                        new RelatorioProdutoDAO().MaisVendidoProduto();
                    break;
                    case 5:
                        new RelatorioProdutoDAO().QuantidadeCategoriaProduto();
                      break;
                    case 6:
                        new RelatorioProdutoDAO().MediaValorProduto();
                    break;
                    case 7:
                        new MenuRelatorio().metodoBusca();
                        return;
                    default:
                    System.out.println("===========================Escolha invalida!===========================");
                    System.out.println("===========================Tente novamente===========================");
              }
            }
        catch (NumberFormatException e) {
            Console.linha();
            System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
            System.out.println("\t\t\t\t\tTENTE NOVAMENTE");
            Console.linha();
        }
          }
    }
}
