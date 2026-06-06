package menu.pedido;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import conexao.Conexao;
import DAO.PedidoDAO;
import enums.StatusPedido;
import servicos.PedidoService;

public class MenuAlteracaoPedido {
    private final Scanner sca = new Scanner(System.in);
    private final PedidoService pedidoService = new PedidoService();

    public void alterarPedido() {
        System.out.println("\n--- GERENCIAR / ALTERAR PEDIDO ---");
        System.out.println("=======================================================================");
        System.out.println("|| PEDIDOS DISPONÍVEIS PARA ALTERAÇÃO (STATUS: ABERTO):               ||");
        System.out.println("=======================================================================");
        String sqlAbertos = "SELECT id_pedido, valor_total FROM pedido WHERE status_pedido = 'ABERTO'";
        int contagem = 0;

        try (Connection conn = Conexao.criarNovaConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlAbertos)) {

            while (rs.next()) {
                contagem++;
                System.out.println("|| -> ID DO PEDIDO: #" + rs.getInt("id_pedido") + " | Valor Total: R$ " + rs.getDouble("valor_total"));
            }

            if (contagem == 0) {
                System.out.println("|| NENHUM PEDIDO EM ESTADO 'ABERTO' ENCONTRADO NO MOMENTO.           ||");
            }

        } catch (Exception e) {
            System.out.println("|| Erro ao listar pedidos: " + e.getMessage());
        }
        System.out.println("=======================================================================\n");

        if (contagem == 0) {
            return;
        }

        int idPedido = pedirIdPedidoValido();

        String status = PedidoDAO.buscarStatusPedido(idPedido);
        try {
            pedidoService.validarSePermiteAlteracao(status);
        } catch (IllegalStateException e) {
            System.out.println("\n[BLOQUEIO DE SEGURANÇA] " + e.getMessage());
            return;
        }

        PedidoDAO.listarItensPedido(idPedido);

        while (true) {
            System.out.println("\n=================================");
            System.out.println("|| MODIFICANDO PEDIDO #" + idPedido);
            System.out.println("=================================");
            System.out.println("|| 1) Alterar Cliente          ||");
            System.out.println("|| 2) Refazer Itens / Produtos ||");
            System.out.println("|| 3) Alterar Observação       ||");
            System.out.println("|| 4) Fechar Pedido            ||");
            System.out.println("|| 5) Finalizar Alterações     ||");
            System.out.println("=================================");
            System.out.print("Escolha o que mudar: ");

            int subOpcao = lerInteiro();

            if (subOpcao == 5) {
                System.out.println("Alterações concluídas para o Pedido #" + idPedido);
                break;
            }

            switch (subOpcao) {
                case 1:
                    System.out.println("\n============================ LISTA DE CLIENTES ============================");
                    new DAO.ClienteDAO().listarParaPedido();
                    System.out.print("\nDigite o ID do novo cliente: ");
                    int novoCli = lerInteiro();
                    try {
                        PedidoDAO.executarUpdateGenerico("UPDATE pedido SET id_cliente_endereco = ? WHERE id_pedido = ?", novoCli, idPedido);
                        System.out.println("[SUCESSO] Cliente updated!");
                    } catch (Exception e) {
                        System.out.println("[ERRO] Falha ao alterar cliente: " + e.getMessage());
                    }
                    break;

                case 2:
                    gerenciarSubMenuItens(idPedido);
                    break;

                case 3:
                    System.out.print("Digite a nova observação para o pedido: ");
                    String novaObs = sca.nextLine();
                    try {
                        PedidoDAO.alterarObservacao(idPedido, novaObs);
                        System.out.println("Observação do pedido #" + idPedido + " atualizada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao alterar observação: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        PedidoDAO.alterarStatus(idPedido, StatusPedido.FILA);
                        System.out.println("Status atualizado para FILA");
                        System.out.println("Pedido enviado para processamento.");
                    } catch (Exception e) {
                        System.out.println("Erro ao alterar status: " + e.getMessage());
                    }
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void gerenciarSubMenuItens(int idPedido) {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("|| GERENCIAR ITENS DO PEDIDO   ||");
            System.out.println("=================================");
            System.out.println("|| 1) Adicionar Produto        ||");
            System.out.println("|| 2) Remover Produto          ||");
            System.out.println("|| 3) Alterar Quantidade       ||");
            System.out.println("|| 4) Refazer Pedido Inteiro   ||");
            System.out.println("|| 5) Voltar                   ||");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            int opcaoItem = lerInteiro();

            if (opcaoItem == 5) {
                break;
            }

            switch (opcaoItem) {
                case 1:
                    adicionarProdutoPedido(idPedido);
                    break;

                case 2:
                    PedidoDAO.listarItensPedido(idPedido);
                    System.out.print("Digite o ID do produto que deseja remover: ");
                    int idProduto = lerInteiro();
                    try {
                        PedidoDAO.removerProdutoPedido(idPedido, idProduto);
                        PedidoDAO.recalcularTotalPedido(idPedido);
                        System.out.println("Produto removido do pedido.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 3:
                    PedidoDAO.listarItensPedido(idPedido);
                    System.out.print("Digite o ID do produto: ");
                    int idProdutoAlterar = lerInteiro();
                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = lerInteiro();

                    if (novaQuantidade < 1) {
                        System.out.println("A quantidade deve ser maior que zero");
                        break;
                    }

                    try {
                        PedidoDAO.alterarQuantidadeProdutoPedido(idPedido, idProdutoAlterar, novaQuantidade);
                        System.out.println("Quantidade alterada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao alterar quantidade: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n[AVISO] Para alterar os produtos, os itens antigos deste pedido serão resetados e o estoque devolvido.");
                    System.out.print("Deseja continuar? (s/n): ");
                    String opc = sca.nextLine();

                    if (opc.equalsIgnoreCase("s")) {
                        try {
                            PedidoDAO.devolverEstoqueELimparItens(idPedido);
                            System.out.println("\n--- REINSERÇÃO DE PRODUTOS ---");

                            double novoValorTotal = 0.0;
                            boolean adicionando = true;

                            while (adicionando) {
                                System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
                                new DAO.ProdutoDAO().listarProdutos();

                                System.out.print("Digite o ID do Produto: ");
                                int idProd = lerInteiro();

                                System.out.print("Quantidade: ");
                                int qtd = lerInteiro();

                                double precoUnidade = PedidoDAO.calcularPrecoProduto(idProd);
                                double subtotal = precoUnidade * qtd;
                                novoValorTotal += subtotal;

                                PedidoDAO.inserirOuAtualizarItem(idPedido, idProd, qtd, precoUnidade);
                                PedidoDAO.atualizarEstoqueProduto(idProd, -qtd);

                                System.out.print("Deseja adicionar outro produto? (s/n): ");
                                adicionando = sca.nextLine().equalsIgnoreCase("s");
                            }

                            PedidoDAO.executarUpdateDoubleGenerico("UPDATE pedido SET valor_total = ? WHERE id_pedido = ?", novoValorTotal, idPedido);
                            System.out.println("[SUCESSO] Produtos e valor total atualizados!");

                        } catch (Exception e) {
                            System.out.println("Erro no processo de reset: " + e.getMessage());
                        }
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void adicionarProdutoPedido(int idPedido) {
        new DAO.ProdutoDAO().listarProdutos();
        System.out.print("ID Produto: ");
        int idProd = lerInteiro();
        System.out.print("Quantidade: ");
        int qtd = lerInteiro();

        try {
            double preco = PedidoDAO.calcularPrecoProduto(idProd);
            PedidoDAO.inserirOuAtualizarItem(idPedido, idProd, qtd, preco);
            PedidoDAO.atualizarEstoqueProduto(idProd, -qtd);
            PedidoDAO.recalcularTotalPedido(idPedido);
            System.out.println("Produto adicionado ao pedido!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    private int pedirIdPedidoValido() {
        System.out.print("Digite o ID do pedido que deseja modificar: ");
        while (true) {
            int id = lerInteiro();
            if (pedidoService.verificarSePedidoExiste(id)) {
                return id;
            }
            System.out.println("Pedido com código " + id + " não encontrado.");
            System.out.print("Digite um ID de pedido válido: ");
        }
    }

    private int lerInteiro() {
        while (!sca.hasNextInt()) {
            System.out.println("Entrada inválida! Digite apenas números inteiros.");
            System.out.print("Escolha: ");
            sca.next();
        }
        int valor = sca.nextInt();
        sca.nextLine();
        return valor;
    }
}