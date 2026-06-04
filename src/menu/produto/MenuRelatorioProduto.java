package menu.produto;
import java.util.Scanner;
import DAO.Relatorio.RelatorioProdutoDAO;

public class MenuRelatorioProduto {
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Metodo de Busca===========================");
        System.out.println("Buscar: 1)Maior Quantidade de Produtos 2)Preço Alto 3)Preço Baixo 4)Mais Vendidos 5)Quantidade por Categoria");
        System.out.print("Escolha:  ");
        int busca = sca.nextInt();
        if (busca  == 1) {
            new RelatorioProdutoDAO().MaiorQuantidadeProduto();
            break;
        }
        if (busca == 2) {
            new RelatorioProdutoDAO().PrecoAltoProduto();
            break;
        }
        if (busca == 3) {
            new RelatorioProdutoDAO().PrecoBaixoProduto();
            break;
        }
        if (busca == 4) {
            new RelatorioProdutoDAO().MaisVendidoProduto();
            break;
        }
        if (busca == 5) {
            new RelatorioProdutoDAO().QuantidadeCategoriaProduto();
            break;        
        }else {
            System.out.println("===========================Escolha invalida!===========================");
            System.out.println("===========================Tente novamente===========================");
        }
        }
        
    }
    
}
