package menu.produto;

import servicos.ProdutoService;
import DAO.Relatorio.RelatorioProdutoDAO;
import util.Console;

import java.util.Scanner;


public class MenuConsultaProduto {
    
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        while (true) {
            try {

            Console.linha();
            System.out.println("||\t\t\t! Metodo de Busca !");
            System.out.println("|| Buscar:");
            System.out.println("|| 1) Por Nome:");
            System.out.println("|| 2) Por Todos os Produtos Cadastrados");
            Console.linhaSimples();
        System.out.print("|| Escolha:  ");
        int busca = Integer.parseInt(sca.nextLine());
        if (busca  == 1) {
            printProduto();
            break;
        } else if (busca == 2) {
            printProdutoFiltro();
            break;
        }else if (busca == 3) {
            new RelatorioProdutoDAO().MaiorQuantidadeProduto();
            break;

        }else {
            System.out.println("===========================Escolha invalida!===========================");
            System.out.println("===========================Tente novamente===========================");
        }
            }catch (NumberFormatException e){
                Console.linha();
                System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
                System.out.println("\t\t\t\t\tTENTE NOVAMENTE");
                Console.linha();
            }
        }

    }

    public void printProduto() {
        new ProdutoService().mostrar();
    }

    public void printProdutoFiltro() {
        System.out.print("Nome do Produto: ");
        String nome = sca.nextLine();
        new ProdutoService().filtrarProdutos(nome);
    }

}
