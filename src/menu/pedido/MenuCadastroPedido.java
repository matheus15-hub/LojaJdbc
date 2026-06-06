package menu.pedido;

import java.util.Scanner;

import DAO.EnderecoClienteDAO;
import DAO.ProdutoDAO;
import DAO.VendedorDAO;
import entidades.ItemPedido;
import menu.vendedor.MenuConsultaVendedor;
import servicos.*;
import util.Console;

public class MenuCadastroPedido {

    private final Scanner sca = new Scanner(System.in);

    public void novoPedido() {
        PedidoService pedidoService = new PedidoService();

        if (!pedidoService.verificarPreRequisitos()) {
            Console.linha();
            System.out.println("\nCriação de pedido cancelada. Resolva os avisos acima primeiro.");
            return;
        }

        int idCliente = escolherCliente();
        int idEndereco = escolhaerEndereco(idCliente);
        int idEnderecoClinte = new EnderecoClienteSer().escolherEnderecoCliente(idCliente, idEndereco);
        pedidoService.addClientePedido(idEnderecoClinte);

        int idVendedor = escolherVendedor();
        pedidoService.addVendedorPedido(idVendedor);

        adicionarProdutos(pedidoService);
        Console.linha();
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.printf("Total: R$ %.2f%n", pedidoService.getValorTotalAcumulado());
        Console.linha();

        System.out.println("\nDeseja adicionar alguma observação ao pedido? (Deixe em branco para 'Sem observações')");
        Console.linhaSimples();
        System.out.print("Observação: ");
        String observacao = sca.nextLine();

        if (observacao.trim().isEmpty()) {
            observacao = "Sem observações.";
        }
        
        pedidoService.addObservacaoPedido(observacao);
        Console.linhaSimples();
        System.out.println("|| Como deseja prosseguir?");
        System.out.println("|| 1)  Enviar para FINALIZAR");
        System.out.println("|| 2)  Salvar como ABERTO");
        System.out.println("|| 3)  Cancelar");
        Console.linhaSimples();
        System.out.print("ESCOLHA: ");

        int opcao = lerInteiro();

        try {
            pedidoService.finalizarFluxo(opcao); 
        } catch (Exception e) {
            System.out.println("[ERRO] Não foi possível cadastrar o pedido: " + e.getMessage());
        }
    }

    private int escolherCliente() {
        return new ClienteService().selecionarClientePPedido();
    }

    private int escolhaerEndereco(int idCliente) {
        int idEndereco;

        while (true) {
            Console.linha();
            System.out.println("|| ENDEREÇOS CADASTRADOS PARA O CLIENTE ID: " + idCliente);
            new EnderecoClienteDAO().mostrarEnderecoCliente(idCliente);

            Console.linhaSimples();
            System.out.println("|| Informe o ID do endereço que será utilizado para a entrega.");
            System.out.println("|| Caso o cliente vá retirar o pedido pessoalmente,");
            System.out.println("|| selecione qualquer endereço cadastrado e informe");
            System.out.println("|| na observação do pedido que a retirada será feita pelo cliente.");
            Console.linhaSimples();
            System.out.print("|| ID DO ENDEREÇO: ");

            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("|| ENTRADA INVÁLIDA!");
                System.out.println("|| Apenas números inteiros são aceitos.");
                System.out.println("|| Exemplos: 1, 10, 20...");
                System.out.print("|| ID DO ENDEREÇO: ");
                sca.nextLine();
            }

            idEndereco = sca.nextInt();
            sca.nextLine();

            idEndereco = new EnderecoClienteSer().verificarLigacao(idCliente, idEndereco);

            Console.linha();
            System.out.println("|| CONFIRMA A SELEÇÃO DO ENDEREÇO ID: " + idEndereco + "?");
            System.out.println("|| 1) Sim");
            System.out.println("|| 2) Não, escolher outro endereço");
            Console.linhaSimples();
            System.out.print("|| ESCOLHA: ");

            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("|| ENTRADA INVÁLIDA!");
                System.out.println("|| Digite apenas:");
                System.out.println("|| 1 - Confirmar endereço");
                System.out.println("|| 2 - Escolher outro endereço");
                System.out.print("|| ESCOLHA: ");
                sca.nextLine();
            }

            int confirmar = sca.nextInt();
            sca.nextLine();

            if (confirmar == 1) {
                break;
            }

            if (confirmar != 2) {
                Console.linha();
                System.out.println("|| OPÇÃO INVÁLIDA!");
                System.out.println("|| Digite 1 para confirmar ou 2 para escolher outro endereço.");
            }
        }

        return idEndereco;
    }

    private int escolherVendedor() {
        return new VendedorSer().selecionarVendedorPedido();
    }

    private void adicionarProdutos(PedidoService pedidoService) {

        int continuar= 1;

        while (continuar == 1) {

            new ProdutoDAO().listarProdutos();
            Console.linhaSimples();
            System.out.print("Escolha um ID Produto: ");
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

                    pedidoService.mostrarCarrinho();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n[AVISO] " + e.getMessage());
                System.out.println("Tente escolher outro produto da lista.\n");
                continue;
            }
            try {
            Console.linhaSimples();
            System.out.println("Adicionar outro produto?: ");
            System.out.println("|| 1) Sim ");
            System.out.println("|| 2) Nao");
            Console.linhaSimples();
            System.out.print("|| Escolha:");
            continuar = Integer.parseInt(sca.nextLine());
            }catch (NumberFormatException e){
                Console.linha();
                System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1 ou 2");
                System.out.println("\t\t\t\t\tTENTE NOVAMENTE");
                Console.linha();
            }
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