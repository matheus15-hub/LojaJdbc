package menu;

import java.math.BigDecimal;
import java.util.Scanner;
import entidades.ItemPedido;
import entidades.Produto;
import entidades.Clientes;
import entidades.Vendedor;
import servicos.*;
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
        System.out.println("Escolha uma Categoria cadastrada para colocar in seu produto:");
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
        System.out.println("Escolha uma medida de venda cadastrada para colocar in seu produto:");
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

        Clienteser clienteServico = new Clienteser();

        System.out.print("Nome do Cliente: ");
        String nome = cin.nextLine();
        nome = clienteServico.verificarNome(nome);

        System.out.print("CPF (com formatação exemplo: 111.222.333-44): ");
        String cpf = cin.nextLine();
        cpf = clienteServico.verificarCPF_clientes(cpf);

        System.out.print("Email: ");
        String email_clientes = cin.nextLine();
        email_clientes = clienteServico.vereficarEmail_clientes(email_clientes);

        System.out.print("Bairro: ");
        String bairro = cin.nextLine();
        while (bairro.trim().isEmpty()) {
            System.out.print("O bairro não pode ser vazio! Bairro: ");
            bairro = cin.nextLine();
        }

        System.out.print("Rua: ");
        String rua = cin.nextLine();
        while (rua.trim().isEmpty()) {
            System.out.print("A rua não pode ser vazia! Rua: ");
            rua = cin.nextLine();
        }

        
        Clientes c = new Clientes(0, nome, cpf, email_clientes, bairro, rua);

        boolean sucesso = clienteServico.adicionarCli(c);
        
        if (sucesso) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar o cliente. Verifique os dados.");
        };
    }

//===============================================================VENDEDOR
    public void Vendedoradd() {

        System.out.print("Nome do Vendedor: ");
        String nome = cin.nextLine();

        System.out.print("Telefone: ");
        String tel = cin.nextLine();

        System.out.print("Email: ");
        String email = cin.nextLine();

        email = new VendedorServico().verificarEmail(email);

        System.out.print("Comissão: ");

        while (!cin.hasNextDouble()) {
            System.out.println("Digite um número válido!");
            cin.next();
        }

        double comissao = cin.nextDouble();
        cin.nextLine();

        comissao = new VendedorServico().verificarComissao(comissao);


        //Vendedor v = new Vendedor(0, nome, tel, email);
        //v.setComissao(comissao);

        //new VendedorServico().adicionar(v);

        System.out.println("Vendedor cadastrado com sucesso!");
    }

//===============================================================PEDIDO
    public void novoPedido() {
        PedidoServico pedidoServico = new PedidoServico();

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
        pedidoServico.addClientePedido(idCli);

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
        pedidoServico.addVendedorPedido(idVend);

        // 3. Loop do Carrinho
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

            boolean adicionado = pedidoServico.tentarAdicionarProduto(idProd, qtd);

            if (adicionado) {
                System.out.println("\n--- ITENS DO PEDIDO ---");
                for (ItemPedido itemCarrinho : pedidoServico.getCarrinhoComponentes()) {
                    System.out.println(
                            "Produto ID: " + itemCarrinho.getIdProdutos() +
                            " | Quantidade: " + itemCarrinho.getQuantidade() +
                            " | Subtotal: R$ " + itemCarrinho.getSubtotal());
                }
            }

            System.out.print("\nDeseja adicionar outro produto? (s/n): ");
            continuar = cin.next();
        }

        cin.nextLine();
        System.out.print("Digite uma observação para o pedido (ou dê Enter para vazio): ");
        String observacao = cin.nextLine();
        pedidoServico.definirObservacao(observacao);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoServico.getValorTotalAcumulado());
        System.out.println("\nDeseja:");
        System.out.println("1 - Finalizar pedido (Status: EM_FILA)");
        System.out.println("2 - Deixar pedido em aberto (Status: ABERTO)");
        System.out.println("3 - Cancelar operação");
        System.out.print("Opção: ");

        while (!cin.hasNextInt()) {
            System.out.println("Digite apenas o número da opção!");
            cin.nextLine();
            System.out.print("Opção: ");
        }
        int opcao = cin.nextInt();
        cin.nextLine(); 

        pedidoServico.finalizarFluxo(opcao);
    }
}