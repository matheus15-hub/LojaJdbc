package menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entidades.ItemPedido;
import entidades.Produto;
import entidades.Clientes;
import entidades.Vendedor;
import servicos.Produtoser;
import servicos.Clienteser;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.ClientesDAO;
import DAO.VendedorDAO;

public class Menuadd {
    Scanner cin = new Scanner(System.in);
    
    public void Produtoadd(){
        System.out.print("Nome do Produto: ");
        String nome = cin.nextLine();
        System.out.print("Preço: ");
        float preco = cin.nextFloat();
        System.out.print("Estoque: ");
        int estoque = cin.nextInt();
        cin.nextLine(); // Limpar o buffer

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

    /* Sugestão de codigo para adiocionar Vendedor.
    public void Vendedoradd() {
        System.out.print("Nome do Vendedor: ");
        String nome = cin.nextLine();
        System.out.print("Telefone: ");
        String tel = cin.nextLine();
        System.out.print("Email: ");
        String email = cin.nextLine();
        System.out.print("Comissão: ");
        double comissao = cin.nextDouble();
        cin.nextLine(); // Limpar o buffer
    }*/

    public void novoPedido() {
        // 1. Seleção de Cliente
        System.out.println("\n--- LISTA DE CLIENTES ---");
        new ClientesDAO().mostrarClient(null); // Aqui tem que arrumar por equanto para evitar erros
        System.out.print("\nDigite o ID do cliente escolhido: ");
        int idCli = cin.nextInt();

        /* 2. Seleção de Vendedor
        System.out.println("\n--- LISTA DE VENDEDORES ---");
        new VendedorDAO().mostrarVendedores();
        System.out.print("Digite o ID do Vendedor escolhido: ");
        int idVend = cin.nextInt();
        */

        List<ItemPedido> carrinho = new ArrayList<>();
        double valorTotalPedido = 0;

        // 3. Loop do Carrinho (ArrayList)
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
            new ProdutoDAO().mostrarProduts(null); 

            System.out.print("\nDigite o ID do Produto: ");
            int idProd = cin.nextInt();
            System.out.print("Quantidade: ");
            int qtd = cin.nextInt();
            System.out.print("Confirme o Preço Unitário: ");
            double preco = cin.nextDouble();

            // Adiciona ao carrinho e soma ao total
            ItemPedido item = new ItemPedido(idProd, qtd, preco);
            carrinho.add(item);
            valorTotalPedido += item.getSubtotal();

            System.out.print("\nDeseja adicionar outro produto? (s/n): ");
            continuar = cin.next();
        }

        // 4. Finalização
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Total: R$ " + valorTotalPedido);
        System.out.print("Confirmar venda? (s/n): ");
        String confirma = cin.next();
        cin.nextLine(); // Limpar buffer final

        if (confirma.equalsIgnoreCase("s")) {
            PedidoDAO.finalizarVenda(idCli, 1, carrinho, valorTotalPedido);
        } else {
            System.out.println("Venda cancelada.");
        }
    }
}