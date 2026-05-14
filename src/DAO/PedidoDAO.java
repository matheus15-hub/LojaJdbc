package DAO;

import java.sql.*;
import java.util.List;
import entidades.ItemPedido;
import conexao.Conexao;

public class PedidoDAO {
    public static void finalizarVenda(int idCliente, int idVendedor, List<ItemPedido> carrinho, double total) {
        Connection conn = Conexao.getConexao();
        String sqlPedido = "INSERT INTO pedido (id_clientes, id_vendedor, valor_total) VALUES (?, ?, ?)";

        try {
            PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setDouble(3, total);
            psPedido.executeUpdate();

            ResultSet rs = psPedido.getGeneratedKeys();
            int idPedidoGerado = 0;
            if (rs.next()) {
                idPedidoGerado = rs.getInt(1);
            }

            String sqlItem = "INSERT INTO item_pedido (id_pedido, id_produtos, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psItem = conn.prepareStatement(sqlItem);

            for (ItemPedido item : carrinho) {
                psItem.setInt(1, idPedidoGerado);
                psItem.setInt(2, item.getIdProdutos());
                psItem.setInt(3, item.getQuantidade());
                psItem.setDouble(4, item.getPrecoUnitario());
                psItem.setDouble(5, item.getSubtotal());
                psItem.execute();
            } 
            System.out.println("Venda finalizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("ERRO ao finalizar venda: " + e.getMessage());
        }
    }
}