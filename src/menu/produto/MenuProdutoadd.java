package menu.produto;


import java.math.BigDecimal;
import java.util.Scanner;

import DAO.*;
import entidades.*;
import servicos.*;
public class MenuProdutoadd {


    Scanner sca = new Scanner(System.in);

    public void Produtoadd() {
        System.out.print("Nome do Produto: ");
        String nome = sca.nextLine();
        nome = new Produtoser().verificarNome(nome);

        BigDecimal preco = new Produtoser().verificarValor();

        int estoque = new Produtoser().verificarEstoque();

        Classeser.mostrar();
        System.out.println("Escolha uma Categoria cadastrada para colocar in seu produto:");
        System.out.print("Categoria: ");
        while (!sca.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às categorias cadastradas.");
            sca.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int categoria = sca.nextInt();
        categoria = new Classeser().vereficarid(categoria);

        sca.nextLine();
        Medidaser.mostrar();
        System.out.println("Escolha uma medida de venda cadastrada para colocar in seu produto:");
        System.out.print("Escolha: ");
        while (!sca.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às unidades de medidas cadastradas.");
            sca.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int medida = sca.nextInt();
        medida = new Medidaser().vereficadorId(medida);

        Produto p = new Produto(nome, preco, estoque, categoria, medida);
        new Produtoser().adicionar(p);
        new Produtoser().mostrar();
    }
}
