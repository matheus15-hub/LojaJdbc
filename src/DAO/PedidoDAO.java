package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import conexao.Conexao;
import entidades.ItemPedido;

public class PedidoDAO {

    public static void finalizarVenda(int idCliente, int idVendedor, List<ItemPedido> carrinho, double total, String observacao, String status) {
        String sqlPedido = "insert into pedido (id_clientes, id_vendedor, status_pedido, valor_total) values (?, ?, ?, ?)";
        String sqlItem = "insert into item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) values (?, ?, ?, ?, ?)";
        String sqlEstoque = "update produtos set estoque = estoque - ? where id_produtos = ? and estoque >= ?";

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psItem = conn.prepareStatement(sqlItem);
             PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque)) {

            conn.setAutoCommit(false);

            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, status); 
            psPedido.setDouble(4, total);

            psPedido.executeUpdate();

            try (ResultSet rs = psPedido.getGeneratedKeys()) {
                int idPedidoGerado = 0;
                if (rs.next()) {
                    idPedidoGerado = rs.getInt(1);
                }

                for (ItemPedido item : carrinho) {
                    psEstoque.setInt(1, item.getQuantidade());
                    psEstoque.setInt(2, item.getIdProdutos());
                    psEstoque.setInt(3, item.getQuantidade());

                    int linhasAfetadas = psEstoque.executeUpdate();

                    if (linhasAfetadas == 0) {
                        conn.rollback();
                        System.out.println("Estoque insuficiente para o produto ID: " + item.getIdProdutos());
                        return;
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

        } catch (SQLException e) {
            System.out.println("ERRO ao finalizar venda: " + e.getMessage());
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
                System.out.println("|| -> ID DO PEDIDO: #" + rs.getInt("id_pedido") + " | Valor Total: R$ " + rs.getDouble("valor_total"));
            }
            if (cont == 0) System.out.println("|| NENHUM PEDIDO EM ESTADO 'ABERTO' ENCONTRADO.                      ||");
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
                     ResultSet rs = s.executeQuery("select nome_produto from produtos where id_produtos = " + idProduto)) {
                    if (rs.next()) nomeProduto = rs.getString("nome_produto");
                } catch (SQLException e) {
                    try (Statement s = conn.createStatement(); 
                         ResultSet rs = s.executeQuery("select nome from produtos where id_produtos = " + idProduto)) {
                        if (rs.next()) nomeProduto = rs.getString("nome");
                    } catch (SQLException ex) {
                        nomeProduto = "Produto #" + idProduto;
                    }
                }

                try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery("select status_pedido from pedido where id_pedido = " + idPedido)) {
                    if (rs.next()) status = rs.getString("status_pedido");
                }

                linha();
                System.out.println("|| PEDIDO #" + idPedido + " [" + status + "]");
                System.out.println("|| Produto: " + nomeProduto);
                System.out.println("|| Qtd: " + quantidade + " | Preço: R$ " + precouni + " | Subtotal: R$ " + subTotal);
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
                if (rs.next()) return rs.getString("status_pedido");
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

    public static void cancelarPedido(int idPedido) {
    String sqlItens = "SELECT id_produtos, quantidade FROM item_pedido WHERE id_pedido = ?";
    String sqlUpdateEstoque = "UPDATE produtos SET estoque = estoque + ? WHERE id_produtos = ?";
    String sqlDeleteItens = "DELETE FROM item_pedido WHERE id_pedido = ?";
    String sqlDeletePedido = "DELETE FROM pedido WHERE id_pedido = ?";

    try (Connection conn = Conexao.criarNovaConexao()) {
        conn.setAutoCommit(false);
        try (PreparedStatement psItens = conn.prepareStatement(sqlItens)) {
            psItens.setInt(1, idPedido);
            try (ResultSet rs = psItens.executeQuery()) {
                try (PreparedStatement psEstoque = conn.prepareStatement(sqlUpdateEstoque)) {
                    while (rs.next()) {
                        psEstoque.setInt(1, rs.getInt("quantidade"));
                        psEstoque.setInt(2, rs.getInt("id_produtos"));
                        psEstoque.executeUpdate();
                    }
                }
            }
        }
        
        try (PreparedStatement psDeleteItens = conn.prepareStatement(sqlDeleteItens)) {
            psDeleteItens.setInt(1, idPedido);
            psDeleteItens.executeUpdate();
        }

        try (PreparedStatement psDeletePedido = conn.prepareStatement(sqlDeletePedido)) {
            psDeletePedido.setInt(1, idPedido);
            psDeletePedido.executeUpdate();
        }
        
        conn.commit();
        System.out.println("Pedido #" + idPedido + " removido e estoque devolvido com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao cancelar o pedido: " + e.getMessage());
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

    public static void linha() {
        System.out.println("============================================================================================");
    }
}