package menu;

import java.math.BigDecimal;
import java.util.Scanner;

import entidades.*;
import servicos.*;
import DAO.ProdutoDAO;
import DAO.ClientesDAO;
import DAO.VendedorDAO;

public class Menuadd {
    Scanner sca = new Scanner(System.in);

//===============================================================PRODUTO
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

//===============================================================CLIENTE
    public void Clienteadd() {

        Clienteser clienteServico = new Clienteser();

        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();
        nome = clienteServico.verificarNome(nome);

        System.out.print("CPF (sem formatação exemplo: 11122233344): ");
        String cpf = sca.nextLine();
        cpf = clienteServico.verificarCPF_clientes(cpf);

        System.out.print("Email: ");
        String email_clientes = sca.nextLine();
        email_clientes = clienteServico.vereficarEmail_clientes(email_clientes);

        Clientes c = new Clientes( nome, cpf, email_clientes);

        System.out.println("==============ENDEREÇO================");
        System.out.print("Rua: ");
        String rua = sca.nextLine();
        rua = new EnderecoSer().vereficarRua(rua);
        System.out.print("Numero: ");
        String numero = sca.nextLine();
        numero = new EnderecoSer().vereficarNumero(numero);
        System.out.print("Bairro: ");
        String bairro = sca.nextLine();
        bairro = new EnderecoSer().vereficarBairro(bairro);
        System.out.print("Cidade: ");
        String cidade = sca.nextLine();
        cidade = new EnderecoSer().vereficarCidade(cidade);
        System.out.print("Cep: ");
        String cep = sca.nextLine();
        cep = new EnderecoSer().vereficarCep(cep);

        Endereco e = new Endereco(rua, numero , bairro, cidade , cep);

        new EnderecoClienteSer().addEnderecoCliente(c , e);
    }

//===============================================================VENDEDOR
    public void Vendedoradd() {

        System.out.print("Nome do Vendedor: ");
        String nome = sca.nextLine();
        nome = new VendedorServico().verificarNome(nome);

        System.out.print("Telefone: ");
        String tel = sca.nextLine();
        tel = new VendedorServico().verificarTelefone(tel);

        System.out.print("Email: ");
        String email = sca.nextLine();
        email = new VendedorServico().verificarEmail(email);

        System.out.print("Salário: ");
        while (!sca.hasNextDouble()) {
            System.out.println("Digite um número válido para o salário!");
            sca.next();
            System.out.print("Salário: ");
        }
        BigDecimal salario = sca.nextBigDecimal();
        sca.nextLine();
        Vendedor vendedor = new Vendedor(nome, tel ,email , salario);
     }

//===============================================================PEDIDO
    public void novoPedido() {
        PedidoServico pedidoServico = new PedidoServico();

        // 1. Seleção de Cliente
        System.out.println("\n--- LISTA DE CLIENTES ---");
        new ClientesDAO().mostrarClient();
        System.out.print("\nDigite o ID do cliente escolhido: ");

        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.nextLine();
            System.out.print("Digite o ID do cliente escolhido: ");
        }
        int idCli = sca.nextInt();
        idCli = new Clienteser().vereficarId_clientes(idCli);
        pedidoServico.addClientePedido(idCli);

        // 2. Seleção de Vendedor
        System.out.println("\n--- LISTA DE VENDEDORES ---");
        new VendedorDAO().mostrarVendedor();
        System.out.print("Digite o ID do Vendedor escolhido: ");

        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sca.nextLine();
            System.out.print("Digite o ID do Vendedor escolhido: ");
        }
        int idVend = sca.nextInt();
        idVend = new VendedorServico().vereficarId(idVend);
        pedidoServico.addVendedorPedido(idVend);

        // 3. Loop do Carrinho
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
            new ProdutoDAO().mostrarProduts();

            System.out.print("\nDigite o ID do Produto: ");
            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.nextLine();
                System.out.print("Digite o ID do Produto: ");
            }
            int idProd = sca.nextInt();

            System.out.print("Quantidade: ");
            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.nextLine();
                System.out.print("Quantidade: ");
            }
            int qtd = sca.nextInt();

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
            continuar = sca.next();
        }

        sca.nextLine();
        System.out.print("Digite uma observação para o pedido (ou dê Enter para vazio): ");
        String observacao = sca.nextLine();
        pedidoServico.definirObservacao(observacao);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoServico.getValorTotalAcumulado());
        System.out.println("\nDeseja:");
        System.out.println("1 - Finalizar pedido (Status: EM_FILA)");
        System.out.println("2 - Deixar pedido em aberto (Status: ABERTO)");
        System.out.println("3 - Cancelar operação");
        System.out.print("Opção: ");

        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas o número da opção!");
            sca.nextLine();
            System.out.print("Opção: ");
        }
        int opcao = sca.nextInt();
        sca.nextLine();

        pedidoServico.finalizarFluxo(opcao);
    }
}