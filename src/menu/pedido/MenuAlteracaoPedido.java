package menu.pedido;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexao.Conexao;
import DAO.PedidoDAO;
import enums.StatusPedido;

public class MenuAlteracaoPedido {
    private final Scanner sca = new Scanner(System.in);

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
                System.out.println("|| -> ID DO PEDIDO: #" + rs.getInt("id_pedido") + " | Valor Total: R$ "
                        + rs.getDouble("valor_total"));
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

        System.out.print("Digite o ID do pedido que deseja modificar: ");
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números inteiros!");
            System.out.print("Digite o ID do pedido: ");
            sca.next();
        }
        int idPedido = sca.nextInt();
        sca.nextLine();

        if (!PedidoDAO.pedidoExiste(idPedido)) {
            System.out.println("Pedido com código " + idPedido + " não encontrado.");
            return;
        }

        String status = PedidoDAO.buscarStatusPedido(idPedido);
        if (!"ABERTO".equalsIgnoreCase(status)) {
            System.out.println("\n[BLOQUEIO DE SEGURANÇA] Este pedido possui o status: " + status);
            System.out.println("Não é permitido alterar dados de pedidos que não estejam em estado 'ABERTO'.");
            return;
        }

        while (true) {
            System.out.println("\n=================================");
            System.out.println("|| MODIFICANDO PEDIDO #" + idPedido);
            System.out.println("=================================");
            System.out.println("|| 1) Alterar Cliente          ||");
            System.out.println("|| 2) Refazer Itens / Produtos ||");
            System.out.println("|| 3) Alterar Observação       ||");
            System.out.println("|| 4) FecharPedido             ||");
            System.out.println("|| 5) Finalizar Alterações     ||");
            System.out.println("=================================");
            System.out.print("Escolha o que mudar: ");

            int subOpcao = sca.nextInt();
            sca.nextLine();

            if (subOpcao == 5) {
                System.out.println("Alterações concluídas para o Pedido #" + idPedido);
                break;
            }

            switch (subOpcao) {
                case 1:
                    System.out.println("\n============================ LISTA DE CLIENTES ============================");
                    new DAO.ClienteDAO().listarParaPedido();
                    System.out.print("\nDigite o ID do novo cliente: ");
                    int novoCli = sca.nextInt();
                    sca.nextLine();
                    executarUpdate("UPDATE pedido SET id_clientes = ? WHERE id_pedido = ?", novoCli, idPedido,
                            "Cliente updated!");
                    break;

                case 2:
                    System.out.println(
                            "\n[AVISO] Para alterar os produtos, os itens antigos deste pedido serão resetados e o estoque devolvido.");
                    System.out.print("Deseja continuar? (s/n): ");
                    String opc = sca.nextLine();
                    if (opc.equalsIgnoreCase("s")) {
                        devolverEstoqueELimparItens(idPedido);

                        System.out.println("\n--- REINSERÇÃO DE PRODUTOS ---");

                        double novoValorTotal = 0.0;
                        boolean adicionando = true;

                        while (adicionando) {
                            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
                            new DAO.ProdutoDAO().listarProdutos();

                            System.out.print("Digite o ID do Produto: ");
                            int idProd = sca.nextInt();
                            System.out.print("Quantidade: ");
                            int qtd = sca.nextInt();
                            sca.nextLine();

                            double precoUnidade = calcularPrecoProduto(idProd);
                            double subtotal = precoUnidade * qtd;
                            novoValorTotal += subtotal;

                            inserirNovoItem(idPedido, idProd, qtd, precoUnidade, subtotal);
                            atualizarEstoqueProduto(idProd, -qtd);

                            System.out.print("Deseja adicionar outro produto? (s/n): ");
                            adicionando = sca.nextLine().equalsIgnoreCase("s");
                        }

                        executarUpdateDouble("UPDATE pedido SET valor_total = ? WHERE id_pedido = ?", novoValorTotal,
                                idPedido, "Produtos e valor total atualizados!");
                    }
                    break;

                case 3:
                    System.out.print("Digite a nova observação para o pedido: ");
                    String novaObs = sca.nextLine();
                    PedidoDAO.alterarObservacao(idPedido, novaObs);
                    break;

                case 4:
                    PedidoDAO.alterarStatus(
                            idPedido,
                            StatusPedido.FILA);

                    System.out.println("Pedido enviado para processamento.");
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void executarUpdate(String sql, int novoId, int idPedido, String mensagemSucesso) {
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, novoId);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
            System.out.println("[SUCESSO] " + mensagemSucesso);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha ao atualizar dados: " + e.getMessage());
        }
    }

    private void executarUpdateDouble(String sql, double valor, int idPedido, String mensagemSucesso) {
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, valor);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
            System.out.println("[SUCESSO] " + mensagemSucesso);
        } catch (SQLException e) {
            System.out.println("[ERRO] Falha: " + e.getMessage());
        }
    }

    private void devolverEstoqueELimparItens(int idPedido) {
        String sqlItens = "SELECT id_produtos, quantidade FROM item_pedido WHERE id_pedido = ?";
        String sqlDel = "DELETE FROM item_pedido WHERE id_pedido = ?";

        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement psItens = conn.prepareStatement(sqlItens)) {

            psItens.setInt(1, idPedido);
            try (ResultSet rs = psItens.executeQuery()) {
                while (rs.next()) {
                    atualizarEstoqueProduto(rs.getInt("id_produtos"), rs.getInt("quantidade"));
                }
            }

            try (PreparedStatement psDel = conn.prepareStatement(sqlDel)) {
                psDel.setInt(1, idPedido);
                psDel.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao resetar itens: " + e.getMessage());
        }
    }

    private void atualizarEstoqueProduto(int idProd, int qtd) {
        String sql = "UPDATE produtos SET estoque = estoque + ? WHERE id_produtos = ?";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qtd);
            ps.setInt(2, idProd);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro estoque: " + e.getMessage());
        }
    }

    private double calcularPrecoProduto(int idProd) {
        String sql = "SELECT preco FROM produtos WHERE id_produtos = ?";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            System.out.println("Erro preco: " + e.getMessage());
        }
        return 0.0;
    }

    private void inserirNovoItem(int idPedido, int idProd, int qtd, double precoUnitario, double subtotal) {
        String sql = "INSERT INTO item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProd);
            ps.setInt(3, qtd);
            ps.setDouble(4, precoUnitario);
            ps.setDouble(5, subtotal);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir novo item: " + e.getMessage());
        }
    }

}