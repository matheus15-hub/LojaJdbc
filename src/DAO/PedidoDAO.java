package DAO;

import java.sql.*;
import java.util.List;
import entidades.ItemPedido;

public class PedidoDAO {

        public static void finalizarVenda(int idCliente, int idVendedor, List<ItemPedido> carrinho, double total) {
            Connection conn = conexao.Conexao.getConexao();
            String sqlPedido = "INSERT INTO pedido (id_clientes, id_vendedor, valor_total) VALUES (?, ?, ?)";

        try {
            // Statement.RETURN_GENERATED_KEYS é esse negocio que pega o id do pedido.
            PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setInt(1, idCliente);
            psPedido.setInt(2, idVendedor);
            psPedido.setDouble(3, total);
            psPedido.executeUpdate();

            // esse aqui pega o ID do pedido que foi criado, para depois inserir na tabela.
            ResultSet rs = psPedido.getGeneratedKeys();
            int idPedidoGerado = 0;
            if (rs.next()) {
                idPedidoGerado = rs.getInt(1);
            }

            // JA aqui salva os itens na ArrayList(Basicamente um carrinho de compras).
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
