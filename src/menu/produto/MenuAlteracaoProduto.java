package menu.produto;


import servicos.ClasseService;
import servicos.MedidaService;
import servicos.ProdutoService;
import util.Console;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuAlteracaoProduto {
    Scanner sca = new Scanner(System.in);
    int id_produto;
    String certezadoProduto;
    public void menuAlterarProduto() {

    while (true) {

        Console.linha();
        System.out.println("||                 ALTERAÇÃO DE PRODUTOS              ||");
        System.out.println("1) Alterar Nome");
        System.out.println("2) Alterar Preço");
        System.out.println("3) Alterar Estoque");
        System.out.println("4) Alterar Categoria");
        System.out.println("5) Alterar Unidade de Medida");
        System.out.println("0) Voltar");
        Console.linhaSimples();
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

            new MenuConsultaProduto().metodoBusca();

            Console.linha();
            System.out.println("|| ESCOLHA O PRODUTO QUE DESEJA ALTERAR");
            Console.linhaSimples();
            System.out.print("|| ID: ");

            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("|| ENTRADA INVÁLIDA!");
                System.out.println("|| Digite apenas números inteiros.");
                System.out.print("|| ID: ");
                sca.nextLine();
            }
            int id = sca.nextInt();
            sca.nextLine();
            id = new ProdutoService().verificarId(id);
            new ProdutoService().mostrarId(id);
            while (true) {
                Console.linha();
                System.out.println("|| ESSE É O PRODUTO QUE DESEJA ALTERAR?");
                System.out.println("|| 1) Sim");
                System.out.println("|| 2) Não");
                Console.linhaSimples();
                System.out.print("|| RESPOSTA: ");
                while (!sca.hasNextInt()) {
                    Console.linha();
                    System.out.println("|| ENTRADA INVÁLIDA!");
                    System.out.println("|| Digite apenas 1 ou 2.");
                    System.out.print("|| RESPOSTA: ");
                    sca.nextLine();
                }
                int resposta = sca.nextInt();
                sca.nextLine();

                if (resposta == 1) {
                    return id;
                }
                else if (resposta == 2) {
                    break;
                }
                else {
                    Console.linhaSimples();
                    System.out.println("|| OPÇÃO INVÁLIDA! DIGITE 1 OU 2.");
                    Console.linhaSimples();
                }
            }
        }
    }
    public void nomeProduto() {
    System.out.println("Alteração de Nome de Produto");

    int id_produto = selecionarProduto();

    System.out.print("Novo Nome: ");
    String nome_produto = sca.nextLine();
    nome_produto = new ProdutoService().verificarNome(nome_produto);

    new ProdutoService().alterarNome(id_produto, nome_produto);
}
public void precoProduto() {
    System.out.println("Alteração de Preço de Produto");

    int id_produto = selecionarProduto();

    System.out.println("\tNovo Preço");
    BigDecimal preco = new ProdutoService().verificarValor();

    new ProdutoService().alterarPreco(id_produto, preco);
}
public void estoqueProduto() {
    System.out.println("Alteração de Estoque de Produto");

    int id_produto = selecionarProduto();

    int estoque = new ProdutoService().verificarEstoque();

    new ProdutoService().alterarEstoque(id_produto, estoque);
}
public void categoriaProduto() {
    System.out.println("Alteração de Categoria de Produto");

    int id_produto = selecionarProduto();

    ClasseService.mostrar();

    System.out.print("Nova Categoria: ");

    while (!sca.hasNextInt()) {
        System.out.println("Código inválido!");
        sca.nextLine();
    }

    int categoria = sca.nextInt();
    categoria = new ClasseService().verificarid(categoria);

    new ProdutoService().alterarCategoria(id_produto, categoria);
}
public void medidaProduto() {
    System.out.println("Alteração de Unidade de Medida");

    int id_produto = selecionarProduto();

    MedidaService.mostrar();

    System.out.print("Nova Medida: ");

    while (!sca.hasNextInt()) {
        System.out.println("Código inválido!");
        sca.nextLine();
    }

    int medida = sca.nextInt();
    medida = new MedidaService().verificadorId(medida);

    new ProdutoService().alterarMedida(id_produto, medida);
}
}
