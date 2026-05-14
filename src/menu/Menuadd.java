package menu;

import java.util.Scanner;
import entidades.Produto;
import entidades.Clientes;
import servicos.Produtoser;
import servicos.Clienteser;
import DAO.ProdutoDAO;
import DAO.ClientesDAO;
import entidades.Clientes;

public class Menuadd {
    Scanner cin = new Scanner(System.in);
    
    public void Produtoadd(){
        System.out.print("Nome do Produto: ");
        String nome = cin.nextLine();
        System.out.print("Preço: ");
        float preco = cin.nextFloat();
        System.out.print("Estoque: ");
        int estoque = cin.nextInt();
        cin.nextLine(); // Limpar o buffer do scanner anterior

        Produto p = new Produto(0, nome, preco, estoque);
        new Produtoser().adicionar(p);
    }

    public void Clienteadd(){
        System.out.print("Nome do Cliente: ");
        String nome = cin.nextLine();
        System.out.print("CPF: ");
        String cpf = cin.nextLine();

        Clientes c = new Clientes(0, nome, cpf);
        new Clienteser().adicionarCli(c);
        }



    
    /*Codigo antigo que foi revisado
    public  void Produtoadd(Produto produto){
        System.out.print("Nome: ");
        produto.setNome_Produtos(cin.nextLine());
        System.out.print("Preço: ");
        produto.setPreco(cin.nextFloat());
        System.out.print("Estoque: ");
        produto.setEstoque(cin.nextInt());
        new Produtoser().adicionar(produto);
        new ProdutoDAO().mostrarProduts(produto);
    }*/

}
