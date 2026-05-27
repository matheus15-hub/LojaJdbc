package menu.MenuAlterar;

import menu.Menuprint;
import servicos.Produtoser;

import java.math.BigDecimal;
import java.util.Scanner;

public class AlterarProduto {
    Scanner sca = new Scanner(System.in);
    int id_produto;
    String certezadoProduto;

    //Produto.
    public void nomeProduto(){

        while (true) {
            System.out.println("Alteração de Nome de Produto");
            new Menuprint().metodoBusca();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id_produto = sca.nextInt();
            id_produto = new Produtoser().verificarId(id_produto);
            new Produtoser().mostrarId(id_produto);
            System.out.println("Esse é o produto que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            certezadoProduto = sca.nextLine();
            if (certezadoProduto.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.print("Novo Nome: ");
        String nome_produto = sca.nextLine();
        nome_produto = new Produtoser().verificarNome(nome_produto);
        new Produtoser().alterarNome(id_produto, nome_produto);
    }

    public void precoProduto(){

        while (true) {
            System.out.println("Alteração de Preço de Produto");
            new Menuprint().metodoBusca();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id_produto = sca.nextInt();
            id_produto = new Produtoser().verificarId(id_produto);
            new Produtoser().mostrarId(id_produto);
            System.out.println("Esse é o produto que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            certezadoProduto = sca.nextLine();
            if (certezadoProduto.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.println("\tNovo Preco");
        BigDecimal b = new Produtoser().verificarValor();
        new Produtoser().alterarPreco(id_produto , b);
    }



}
