package menu.produto;


import java.math.BigDecimal;
import java.util.Scanner;

import DAO.*;
import entidades.*;
import servicos.*;
public class MenuCadastroProduto {


    Scanner sca = new Scanner(System.in);

    public void Produtoadd() {
        System.out.print("Nome do Produto: ");
        String nome = sca.nextLine();
        nome = new ProdutoService().verificarNome(nome);

        BigDecimal preco = new ProdutoService().verificarValor();

        int estoque = new ProdutoService().verificarEstoque();

        ClasseService.mostrar();
        System.out.println("Escolha uma Categoria cadastrada para colocar in seu produto:");
        System.out.print("Categoria: ");
        while (!sca.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às categorias cadastradas.");
            sca.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int categoria = sca.nextInt();
        categoria = new ClasseService().verificarid(categoria);

        sca.nextLine();
        MedidaService.mostrar();
        System.out.println("Escolha uma medida de venda cadastrada para colocar in seu produto:");
        System.out.print("Escolha: ");
        while (!sca.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às unidades de medidas cadastradas.");
            sca.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int medida = sca.nextInt();
        medida = new MedidaService().verificadorId(medida);

        Produto p = new Produto(nome, preco, estoque, categoria, medida);
        new ProdutoService().adicionarProduto(p);
        new ProdutoService().mostrar();
    }
}
