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

    public static void finalizarVenda(int idCliente, int idVendedor,
            List<ItemPedido> carrinho, double total) {

        Connection conn = Conexao.getConexao();

        String sqlPedido = "INSERT INTO pedido (id_clientes, id_vendedor, status_pedido, valor_total) " +
                "VALUES (?, ?, ?, ?)";

        try {

            conn.setAutoCommit(false);

            PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);

            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setString(3, "FILA");
            psPedido.setDouble(4, total);

            psPedido.executeUpdate();

            ResultSet rs = psPedido.getGeneratedKeys();

            int idPedidoGerado = 0;

            if (rs.next()) {
                idPedidoGerado = rs.getInt(1);
            }

            String sqlItem = "INSERT INTO item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement psItem = conn.prepareStatement(sqlItem);

            String sqlEstoque = "UPDATE produtos SET estoque = estoque - ? WHERE id_produtos = ? AND estoque >= ?";

            PreparedStatement psEstoque = conn.prepareStatement(sqlEstoque);

            for (ItemPedido item : carrinho) {

                psEstoque.setInt(1, item.getQuantidade());
                psEstoque.setInt(2, item.getIdProdutos());
                psEstoque.setInt(3, item.getQuantidade());

                int linhasAfetadas = psEstoque.executeUpdate();

                if (linhasAfetadas == 0) {

                    conn.rollback();

                    System.out.println("Estoque insuficiente para o produto ID: "
                            + item.getIdProdutos());

                    return;
                }

                psItem.setInt(1, idPedidoGerado);
                psItem.setInt(2, item.getIdProdutos());
                psItem.setInt(3, item.getQuantidade());
                psItem.setDouble(4, item.getPrecoUnitario());
                psItem.setDouble(5, item.getSubtotal());

                psItem.executeUpdate();
            }

            conn.commit();

            System.out.println("Venda finalizada com sucesso!");

        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("ERRO ao finalizar venda: " + e.getMessage());

        } finally {

            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão");
            }
        }
    }
}