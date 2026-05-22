package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entidades.ItemPedido;
import entidades.Produto;
import servicos.Produtoser;
import servicos.Clienteser;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.ClientesDAO;
import DAO.VendedorDAO;

public class Menuremov {
    Scanner sca = new Scanner(System.in);

    public void ClienteaRemov() {
        System.out.println("Digite o ID do cliente: ");
        int id_clientes = sca.nextInt();
        new Clienteser().removerCli(id_clientes);
    }


    public void ProdutoRemov(){
        System.out.println("\t\tDeletando PRODUTOS");
        System.out.println("Buscar Produtos S | N");
        System.out.print(":: ");
        String resposta = sca.nextLine();
        if(resposta.equalsIgnoreCase("s")){
            System.out.print("Busca: ");
            String busca = sca.nextLine();
            new Menuprint().metodoBusca(busca);
        }

        System.out.print("ID: ");
        while (!sca.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes aos produtos cadastradas.");
            sca.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int codigo_produto = sca.nextInt();
        codigo_produto = new Produtoser().verificarId(codigo_produto);
        new Produtoser().remover(codigo_produto);
    }


    
}