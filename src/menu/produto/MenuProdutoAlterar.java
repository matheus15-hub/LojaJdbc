package menu.produto;


import servicos.ClasseSer;
import servicos.MedidaSer;
import servicos.ProdutoSer;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuProdutoAlterar {
    Scanner sca = new Scanner(System.in);
    int id_produto;
    String certezadoProduto;
    public void menuAlterarProduto() {

    while (true) {

        System.out.println("\n======================== ALTERAÇÃO DE PRODUTOS =========================");
        System.out.println("1) Nome | 2) Preço | 3) Estoque | 4) Categoria | 5) Unidade de Medida | 0) Voltar");
        System.out.print("Escolha: ");

        while (!sca.hasNextInt()) {
            System.out.println("Opção inválida! Digite apenas números.");
            sca.nextLine();
            System.out.print("Escolha: ");
        }

        int opcao = sca.nextInt();
        sca.nextLine();

        switch (opcao) {

            case 1:
                nomeProduto();
                break;

            case 2:
                precoProduto();
                break;

            case 3:
                estoqueProduto();
                break;

            case 4:
                categoriaProduto();
                break;

            case 5:
                medidaProduto();
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }
}
    private int selecionarProduto() {
    while (true) {
        new MenuProdutoPrint().metodoBusca();
        System.out.println("======================================================\n");
        System.out.println("Escolha o ID correspondente do produto:");
        System.out.print("ID: ");

        while (!sca.hasNextInt()) {
            System.out.println("Entrada inválida! O ID deve conter apenas números inteiros.");
            sca.nextLine();
            System.out.print("ID: ");
        }

        int id = sca.nextInt();
        id = new ProdutoSer().verificarId(id);

        new ProdutoSer().mostrarId(id);

        System.out.println("Esse é o produto que deseja alterar? SIM = s , NÃO = n");
        System.out.print("Resposta: ");

        sca.nextLine();
        String resposta = sca.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            return id;
        }
    }
}
    public void nomeProduto() {
    System.out.println("Alteração de Nome de Produto");

    int id_produto = selecionarProduto();

    System.out.print("Novo Nome: ");
    String nome_produto = sca.nextLine();
    nome_produto = new ProdutoSer().verificarNome(nome_produto);

    new ProdutoSer().alterarNome(id_produto, nome_produto);
}
public void precoProduto() {
    System.out.println("Alteração de Preço de Produto");

    int id_produto = selecionarProduto();

    System.out.println("\tNovo Preço");
    BigDecimal preco = new ProdutoSer().verificarValor();

    new ProdutoSer().alterarPreco(id_produto, preco);
}
public void estoqueProduto() {
    System.out.println("Alteração de Estoque de Produto");

    int id_produto = selecionarProduto();

    int estoque = new ProdutoSer().verificarEstoque();

    new ProdutoSer().alterarEstoque(id_produto, estoque);
}
public void categoriaProduto() {
    System.out.println("Alteração de Categoria de Produto");

    int id_produto = selecionarProduto();

    ClasseSer.mostrar();

    System.out.print("Nova Categoria: ");

    while (!sca.hasNextInt()) {
        System.out.println("Código inválido!");
        sca.nextLine();
    }

    int categoria = sca.nextInt();
    categoria = new ClasseSer().vereficarid(categoria);

    new ProdutoSer().alterarCategoria(id_produto, categoria);
}
public void medidaProduto() {
    System.out.println("Alteração de Unidade de Medida");

    int id_produto = selecionarProduto();

    MedidaSer.mostrar();

    System.out.print("Nova Medida: ");

    while (!sca.hasNextInt()) {
        System.out.println("Código inválido!");
        sca.nextLine();
    }

    int medida = sca.nextInt();
    medida = new MedidaSer().vereficadorId(medida);

    new ProdutoSer().alterarMedida(id_produto, medida);
}
}
