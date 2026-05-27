package menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entidades.ItemPedido;
import entidades.Produto;
import entidades.Clientes;
import entidades.Vendedor;
import servicos.*;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.ClientesDAO;
import DAO.VendedorDAO;

public class Menuadd {
    Scanner cin = new Scanner(System.in);
//===============================================================PRODUTO
    public void Produtoadd() {
        System.out.print("Nome do Produto: ");
        String nome = cin.nextLine();
        nome = new Produtoser().verificarNome(nome);

        BigDecimal preco = new Produtoser().verificarValor();

        int estoque = new Produtoser().verificarEstoque();

        Classeser.mostrar();
        System.out.println("Escolha uma Categoria cadastrada para colocar em seu produto:");
        System.out.print("Categoria: ");
        while (!cin.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às categorias cadastradas.");
            cin.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int categoria = cin.nextInt();
        categoria = new Classeser().vereficarid(categoria);

        cin.nextLine();
        Medidaser.mostrar();
        System.out.println("Escolha uma medida de venda cadastrada para colocar em seu produto:");
        System.out.print("Escolha: ");
        while (!cin.hasNextInt()){
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes às unidades de medidas cadastradas.");
            cin.nextLine();
            System.out.print("Digite um Codigo Cadastrado: ");
        }
        int medida = cin.nextInt();
        medida = new Medidaser().vereficadorId(medida);


        Produto p = new Produto(nome, preco, estoque, categoria, medida);
        new Produtoser().adicionar(p);
        new Produtoser().mostrar();
    }
//===============================================================CLIENTE

    public void Clienteadd() {
        System.out.print("Nome do Cliente: ");
        String nome = cin.nextLine();
        nome = new Clienteser().verificarNome(nome);

        System.out.print("CPF (com formatação exemplo: 111.222.333-44): ");
        String cpf = cin.nextLine();
        cpf = new Clienteser().verificarCPF_clientes(cpf);

        System.out.print("Email: ");
        String email_clientes = cin.nextLine();
        email_clientes = new Clienteser().vereficarEmail_clientes(email_clientes);

        Clientes c = new Clientes(0, nome, cpf, email_clientes);
        c.setEmail(email_clientes);

        new Clienteser().adicionarCli(c);
        System.out.println("Cliente cadastrado com sucesso!");
    }
//===============================================================Vendedor

    public void Vendedoradd() {

    System.out.print("Nome do Vendedor: ");
    String nome = cin.nextLine();
    System.out.print("Telefone: ");
    String tel = cin.nextLine();
    System.out.print("Email: ");
    String email = cin.nextLine();
    Vendedor v = new Vendedor(0, nome, tel, email);
    new VendedorDAO().addVendedor(v);
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
        idCli = new Clienteser().vereficarId_clientes(idCli);

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
        idVend = new VendedorServico().vereficarId(idVend);

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
            PedidoDAO.finalizarVenda(idCli, idVend, carrinho, valorTotalPedido);
        } else {
            System.out.println("Venda cancelada.");
        }
    }
}