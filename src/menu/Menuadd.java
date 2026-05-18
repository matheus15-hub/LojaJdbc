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

    public void Produtoadd() {
        System.out.print("Nome do Produto: ");
        String nome = cin.nextLine();
        nome = new Produtoser().verificarNome(nome);
        float preco;
        System.out.print("Preço: ");
        while (!cin.hasNextFloat()) {
            System.out.println("Apenas Numeros!!");
            cin.nextLine();
            System.out.print("Preço: ");
        }
        preco = cin.nextFloat();
        preco = new Produtoser().verificarValor(preco);
        int estoque;
        System.out.print("Estoque: ");
        while (!cin.hasNextInt()) {
            System.out.println("Apenas Numero !");
            cin.nextLine();
            System.out.println("Estoque");
        }
        estoque = cin.nextInt();
        cin.nextLine();
        estoque = new Produtoser().verificarEstoque(estoque);
        System.out.print("Categoria: ");
        String categoria = cin.nextLine();
        System.out.println("MEDIDA DE VENDA (UNI, M, M2 , M3 , KG)");
        System.out.print("MEDIDA: ");
        String medida = cin.nextLine();
        medida = new Produtoser().verificarUnidade(medida);

        Produto p = new Produto(nome, preco, estoque, categoria, medida);
        new Produtoser().adicionar(p);
        new Produtoser().mostrar();
    }

    public void Clienteadd() {
        System.out.print("Nome do Cliente: ");
        String nome = cin.nextLine();
        System.out.print("CPF (com formatação): ");
        String cpf = cin.nextLine();

        Clientes c = new Clientes(0, nome, cpf);
        new Clienteser().adicionarCli(c);
    }

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
    }

    public void novoPedido() {
        // 1. Seleção de Cliente
        System.out.println("\n--- LISTA DE CLIENTES ---");
        new ClientesDAO().mostrarClient();
        System.out.print("\nDigite o ID do cliente escolhido: ");

        while (!cin.hasNextInt()) {
            System.out.println("Digite apenas números!");
            cin.nextLine();
            System.out.print("Digite o ID do cliente escolhido: ");
        }

        int idCli = cin.nextInt();

        if (idCli <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        // 2. Seleção de Vendedor
        System.out.println("\n--- LISTA DE VENDEDORES ---");
        new VendedorDAO().mostrarVendedor();
        System.out.print("Digite o ID do Vendedor escolhido: ");

        while (!cin.hasNextInt()) {
            System.out.println("Digite apenas números!");
            cin.nextLine();
            System.out.print("Digite o ID do Vendedor escolhido: ");
        }

        int idVend = cin.nextInt();

        if (idVend <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        List<ItemPedido> carrinho = new ArrayList<>();
        double valorTotalPedido = 0;

        // 3. Loop do Carrinho (ArrayList)
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
            new ProdutoDAO().mostrarProduts();

            System.out.print("\nDigite o ID do Produto: ");

            while (!cin.hasNextInt()) {
                System.out.println("Digite apenas números!");
                cin.nextLine();
                System.out.print("Digite o ID do Produto: ");
            }

            int idProd = cin.nextInt();

            if (!ProdutoDAO.produtoExiste(idProd)) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Quantidade: ");

            while (!cin.hasNextInt()) {
                System.out.println("Digite apenas números!");
                cin.nextLine();
                System.out.print("Quantidade: ");
            }

            int qtd = cin.nextInt();

            if (qtd <= 0) {
                System.out.println("Quantidade inválida!");
                continue;
            }

            int estoque = ProdutoDAO.buscarEstoque(idProd);

            if (qtd > estoque) {
                System.out.println("Estoque insuficiente!");
                continue;
            }

            double preco = ProdutoDAO.buscarPreco(idProd);

            System.out.println("Preço do produto: R$ " + preco);

            // Adiciona ao carrinho e soma ao total

            ItemPedido item = new ItemPedido(idProd, qtd, preco);
            carrinho.add(item);
            valorTotalPedido += item.getSubtotal();

            System.out.println("\n--- ITENS DO PEDIDO ---");

            for (ItemPedido itemCarrinho : carrinho) {

                System.out.println(
                        "Produto ID: " + itemCarrinho.getIdProdutos() +
                                " | Quantidade: " + itemCarrinho.getQuantidade() +
                                " | Subtotal: R$ " + itemCarrinho.getSubtotal());
            }

            System.out.print("\nDeseja adicionar outro produto? (s/n): ");
            continuar = cin.next();
        }

        // 4. Finalização
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", valorTotalPedido);
        System.out.print("Confirmar venda? (s/n): ");
        String confirma = cin.next();
        cin.nextLine(); // Limpar buffer final

        if (confirma.equalsIgnoreCase("s")) {
            PedidoDAO.finalizarVenda(idCli, idVend, carrinho, valorTotalPedido);
        } else {
            System.out.println("Venda cancelada.");
        }
    }
}