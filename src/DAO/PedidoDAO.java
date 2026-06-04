package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutionException;
import enums.StatusPedido;

import conexao.Conexao;
import entidades.ItemPedido;

public class PedidoDAO {

    public static int finalizarVenda(int idCliente, int idVendedor, List<ItemPedido> carrinho, double total,
            String observacao, StatusPedido status) {
        String sqlPedido = "insert into pedido (id_clientes, id_vendedor, status_pedido, valor_total) values (?, ?, ?, ?)";
        String sqlItem = "insert into item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) values (?, ?, ?, ?, ?)";
        String sqlEstoque = "update produtos set estoque = estoque - ? where id_produtos = ? and estoque >= ?";
        int idPrecisoComissao = 0;

        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psItem = conn.prepareStatement(sqlItem);
                PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque)) {

            conn.setAutoCommit(false);

            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, status.name());
            psPedido.setDouble(4, total);

            psPedido.executeUpdate();

            try (ResultSet rs = psPedido.getGeneratedKeys()) {
                int idPedidoGerado = 0;
                if (rs.next()) {
                    idPedidoGerado = rs.getInt(1);
                    idPrecisoComissao = rs.getInt(1);
                }

                for (ItemPedido item : carrinho) {
                    psEstoque.setInt(1, item.getQuantidade());
                    psEstoque.setInt(2, item.getIdProdutos());
                    psEstoque.setInt(3, item.getQuantidade());

                    int linhasAfetadas = psEstoque.executeUpdate();

                    if (linhasAfetadas == 0) {
                        conn.rollback();
                        System.out.println("Estoque insuficiente para o produto ID: " + item.getIdProdutos());
                        return -1;
                    }

                    psItem.setInt(1, idPedidoGerado);
                    psItem.setInt(2, item.getIdProdutos());
                    psItem.setInt(3, item.getQuantidade());
                    psItem.setDouble(4, item.getPrecoUnitario());
                    psItem.setDouble(5, item.getSubtotal());

                    psItem.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("Venda salva com sucesso no sistema!");
            return idPrecisoComissao;
        } catch (SQLException e) {
            System.out.println("ERRO ao finalizar venda: " + e.getMessage());
            return 0;
        }
    }

    public static void imprimirPedidoS() {
        System.out.println("\n=======================================================================");
        System.out.println("|| PEDIDOS DISPONÍVEIS PARA REMOÇÃO (STATUS: ABERTO):                 ||");
        System.out.println("=======================================================================");
        String sqlAbertos = "SELECT id_pedido, valor_total FROM pedido WHERE status_pedido = 'ABERTO'";
        try (Connection conn = Conexao.criarNovaConexao();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlAbertos)) {
            int cont = 0;
            while (rs.next()) {
                cont++;
                System.out.println("|| -> ID DO PEDIDO: #" + rs.getInt("id_pedido") + " | Valor Total: R$ "
                        + rs.getDouble("valor_total"));
            }
            if (cont == 0)
                System.out.println("|| NENHUM PEDIDO EM ESTADO 'ABERTO' ENCONTRADO.                      ||");
        } catch (Exception e) {
            System.out.println("|| Erro: " + e.getMessage());
        }
        System.out.println("=======================================================================\n");

        System.out.println("--- TODOS OS ITENS DE PEDIDOS NO SISTEMA ---");
        String sqlItens = "select id_pedido, id_produtos, quantidade, preco_unitario, subtotal from item_pedido";
        try (Connection conn = Conexao.criarNovaConexao();
                Statement stmt = conn.createStatement();
                ResultSet rsItem = stmt.executeQuery(sqlItens)) {

            while (rsItem.next()) {
                int idPedido = rsItem.getInt("id_pedido");
                int idProduto = rsItem.getInt("id_produtos");
                int quantidade = rsItem.getInt("quantidade");
                float precouni = rsItem.getFloat("preco_unitario");
                float subTotal = rsItem.getFloat("subtotal");

                String nomeProduto = "Produto ID: " + idProduto;
                String status = "PROCESSANDO";

                try (Statement s = conn.createStatement();
                        ResultSet rs = s
                                .executeQuery("select nome_produto from produtos where id_produtos = " + idProduto)) {
                    if (rs.next())
                        nomeProduto = rs.getString("nome_produto");
                } catch (SQLException e) {
                    try (Statement s = conn.createStatement();
                            ResultSet rs = s
                                    .executeQuery("select nome from produtos where id_produtos = " + idProduto)) {
                        if (rs.next())
                            nomeProduto = rs.getString("nome");
                    } catch (SQLException ex) {
                        nomeProduto = "Produto #" + idProduto;
                    }
                }

                try (Statement s = conn.createStatement();
                        ResultSet rs = s
                                .executeQuery("select status_pedido from pedido where id_pedido = " + idPedido)) {
                    if (rs.next())
                        status = rs.getString("status_pedido");
                }

                linha();
                System.out.println("|| PEDIDO #" + idPedido + " [" + status + "]");
                System.out.println("|| Produto: " + nomeProduto);
                System.out
                        .println("|| Qtd: " + quantidade + " | Preço: R$ " + precouni + " | Subtotal: R$ " + subTotal);
                linha();
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public static String buscarStatusPedido(int idPedido) {
        String sql = "select status_pedido from pedido where id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getString("status_pedido");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar status: " + e.getMessage());
        }
        return null;
    }

    public static boolean pedidoExiste(int idPedido) {
        String sql = "select 1 from pedido where id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static void alterarObservacao(int idPedido, String novaObs) {
        String sql = "UPDATE pedido SET observacao = ? WHERE id_pedido = ?";
        try (Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaObs);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
            System.out.println("Observação do pedido #" + idPedido + " atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar observação: " + e.getMessage());
        }
    }

    public static void alterarStatus(int idPedido, StatusPedido status) {
        String sql = "UPDATE pedido SET status_pedido = ? WHERE id_pedido = ?";

        try (
                Connection conn = Conexao.criarNovaConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ps.setInt(2, idPedido);

            ps.executeUpdate();

            System.out.println("Status atualizado para " + status);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void linha() {
        System.out.println(
                "============================================================================================");
    }
}