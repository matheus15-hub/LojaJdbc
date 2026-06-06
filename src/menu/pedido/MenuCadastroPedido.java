package menu.pedido;

import java.util.Scanner;

import DAO.EnderecoClienteDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import entidades.ItemPedido;
import servicos.EnderecoClienteSer;
import servicos.PedidoService;
import servicos.ProdutoService;
import util.Console;

public class MenuCadastroPedido {

    private final Scanner sca = new Scanner(System.in);

    public void novoPedido() {
        PedidoService pedidoService = new PedidoService();

        int idCliente = escolherCliente();
        int idEndereco = escolhaerEndereco(idCliente);
        int idEnderecoClinte = new EnderecoClienteSer().escolherEnderecoCliente(idCliente, idEndereco);
        pedidoService.addClientePedido(idEnderecoClinte);

        int idVendedor = escolherVendedor();
        pedidoService.addVendedorPedido(idVendedor);

        adicionarProdutos(pedidoService);

        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoService.getValorTotalAcumulado());

        System.out.println("\nDeseja adicionar alguma observação ao pedido? (Deixe em branco para 'Sem observações')");
        System.out.print("Observação: ");
        String observacao = sca.nextLine();

        if (observacao.trim().isEmpty()) {
            observacao = "Sem observações.";
        }
        
        pedidoService.addObservacaoPedido(observacao); 

        System.out.println("\nComo deseja prosseguir?");
        System.out.println("1 - Enviar para FILA");
        System.out.println("2 - Salvar como ABERTO");
        System.out.println("3 - Cancelar");
        System.out.print("ESCOLHA: ");

        int opcao = lerInteiro();

        try {
            pedidoService.finalizarFluxo(opcao); 
        } catch (Exception e) {
            System.out.println("[ERRO] Não foi possível cadastrar o pedido: " + e.getMessage());
        }
    }

    private int escolherCliente() {
        int idCliente = 0;
        while (true) {
            System.out.println("\n--- CLIENTES ---");
            new DAO.ClienteDAO().listarParaPedido();

            System.out.print("ID Cliente: ");
            idCliente = lerInteiro();

            if (DAO.ClienteDAO.verificarExistencia(idCliente)) {
                break;
            } else {
                System.out.println("\n=====================================================");
                System.out.println("||            [ERRO] CLIENTE NÃO ENCONTRADO!       ||");
                System.out.println("||       Por favor, selecione um ID da lista acima. ||");
                System.out.println("=====================================================");
            }
        }
        return idCliente;
    }
    
    private int escolhaerEndereco(int idCliete){
        int idendereco;
        while (true) {
            Console.linha();
            System.out.println("|| TODOS OS ENDEREÇOS VINCULADOS AO CLINTE " + idCliete + " :");
            new EnderecoClienteDAO().mostrarEnderecoCliente(idCliete);
            Console.linhaSimples();
            System.out.println("|| Selecione o ID do endereço que sera feito a entrega:");
            System.out.println("|| Se aconteceder do Cliente vim retirar selecione um endereço, \n|| e na oberservação coloque que o cliente vem retirar:");
            System.out.print("|| Escolha: ");
            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("||Entrada de dados invalidos | Apenas Numeros inteiros são aceitos");
                System.out.println("||\t\t\tEx: 1 , 10 , 20.....");
                sca.nextLine();
                System.out.println("Digite o ID correspondente ao endereço: ");

            }
            idendereco = sca.nextInt();
            idendereco = new EnderecoClienteSer().verificarLigacao(idCliete, idendereco);
            Console.linha();
            System.out.println("|| Deseja mesmo selecionar o endereço do ID:" + idendereco+" ?");
            System.out.println("|| 1) Sim | 2)Não :");
            System.out.print("|| ESCOLHA:");
            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("||Entrada de dados invalidos | Apenas Numeros inteiros são aceitos");
                System.out.println("||\t\t\tEx: 1 , 10 , 20.....");
                sca.nextLine();
                System.out.println("|| Digite 1) Para Quero selecionar esse endereço | 2) Não : ");
                System.out.println("||        2) Buscar um novo endereço : ");
                System.out.print("|| ESCOLHA:");
            }
            int queralterar = sca.nextInt();
            if(queralterar == 1) break;
            if (queralterar != 1  && queralterar != 2) System.out.println("Opção Invalida!");
        }
        return idendereco;
    }

    private int escolherVendedor() {
        int idVendedor = 0;
        while (true) {
            System.out.println("\n--- VENDEDORES ---");
            new DAO.VendedorDAO().mostrarVendedor();

            System.out.print("ID Vendedor: ");
            idVendedor = lerInteiro();

            if (DAO.VendedorDAO.verificarExistencia(idVendedor)) {
                break;
            } else {
                System.out.println("\n=====================================================");
                System.out.println("||            [ERRO] VENDEDOR NÃO ENCONTRADO!      ||");
                System.out.println("||       Por favor, selecione um ID da lista acima. ||");
                System.out.println("=====================================================");
            }
        }
        return idVendedor;
    }

    private void adicionarProdutos(PedidoService pedidoService) {

        String continuar = "s";

        while (continuar.equalsIgnoreCase("s")) {

            new ProdutoDAO().listarProdutos();

            System.out.print("ID Produto: ");
            int idProduto = lerInteiro();
            idProduto = new ProdutoService().verificarId(idProduto);

            System.out.print("Quantidade: ");
            int quantidade = lerInteiro();

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida.");
                continue;
            }
            try {
                boolean adicionou =
                        pedidoService.tentarAdicionarProduto(idProduto, quantidade);

                if (adicionou) {

                    System.out.println("\n--- CARRINHO ---");

                    for (ItemPedido item : pedidoService.getCarrinhoComponentes()) {

                        System.out.println(
                                "Produto: " + item.getIdProdutos()
                                + " | Qtd: " + item.getQuantidade()
                                + " | Subtotal: R$ " + item.getSubtotal()
                        );
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n[AVISO] " + e.getMessage());
                System.out.println("Tente escolher outro produto da lista.\n");
                continue;
            }

            System.out.print("Adicionar outro produto? (s/n): ");
            continuar = sca.nextLine();
        }
    }

    private int lerInteiro() {
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números.");
            sca.nextLine();
        }

        int valor = sca.nextInt();
        sca.nextLine(); 

        return valor;
    }
}