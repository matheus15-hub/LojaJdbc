package menu.produto;

import servicos.ProdutoSer;

import java.util.Scanner;


public class MenuProdutoPrint {
    
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Metodo de Busca===========================");
        System.out.println("Buscar: 1)Com filtro(caso deseje um produto especifico) 2) Todos os Produtos cadastrados");
        System.out.print("Escolha:  ");
        int busca = sca.nextInt();
        if (busca  == 1) {
            printProduto();
            break;
        } else if (busca == 2) {
            printProdutoFiltro();
            break;
        }else {
            System.out.println("===========================Escolha invalida!===========================");
            System.out.println("===========================Tente novamente===========================");
        }
        }
        
    }

    public void printProduto() {
        new ProdutoSer().mostrar();
    }

    public void printProdutoFiltro() {
        System.out.print("Nome do Produto: ");
        String nome = sca.nextLine();
        new ProdutoSer().filtro(nome);
    }

}
