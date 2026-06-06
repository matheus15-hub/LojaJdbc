package menu.produto;

import java.util.Scanner;

import servicos.ProdutoService;
import util.Console;

public class MenuRemocaoProduto {

    Scanner sca = new Scanner(System.in);

    public void produtoRemover() {
        Console.linha();
        System.out.println("\t\tDeletando PRODUTOS");
        Console.linha();
            new MenuConsultaProduto().metodoBusca();

        System.out.println("SELECIONE O ID DO PRODUTO QUE DESEJA EXCLUIR");
        System.out.print("ID: ");

        while (!sca.hasNextInt()) {
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes aos produtos cadastrados.");
            sca.nextLine();
            System.out.print("Digite um Código Cadastrado: ");
        }

        int codigo_produto = sca.nextInt();
        codigo_produto = new ProdutoService().verificarId(codigo_produto);

        new ProdutoService().remover(codigo_produto);
    }
}