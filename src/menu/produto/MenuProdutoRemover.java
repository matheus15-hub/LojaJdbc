package menu.produto;

import java.util.Scanner;

import servicos.Produtoser;

public class MenuProdutoRemover {

    Scanner sca = new Scanner(System.in);

    public void produtoRemover() {

        System.out.println("\t\tDeletando PRODUTOS");
        System.out.println("Buscar Produtos 1) Sim 2) Não");
        System.out.print("Escolha: ");

        int resposta = sca.nextInt();

        if (resposta == 1) {
            sca.nextLine();
            new MenuProdutoPrint().metodoBusca();
        }

        System.out.println("SELECIONE O ID DO PRODUTO QUE DESEJA EXCLUIR");
        System.out.print("ID: ");

        while (!sca.hasNextInt()) {
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes aos produtos cadastrados.");
            sca.nextLine();
            System.out.print("Digite um Código Cadastrado: ");
        }

        int codigo_produto = sca.nextInt();
        codigo_produto = new Produtoser().verificarId(codigo_produto);

        new Produtoser().remover(codigo_produto);
    }
}