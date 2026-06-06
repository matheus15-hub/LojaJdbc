package DAO;

import conexao.Conexao;
import util.Console;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemPedidoDAO {
    public void mostrarItemPedido(int id) {
        String sql = """
        SELECT *
        FROM item_pedido i
        JOIN produtos p ON i.id_produtos = p.id_produtos
        WHERE id_pedido = ?
        """;

        try (Connection conn = Conexao.criarNovaConexao();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Console.linha();
                System.out.println("|| ITENS DO PEDIDO");
                Console.linhaSimples();
                while (resultSet.next()) {
                    System.out.println("|| ID PRODUTO : " + resultSet.getInt("id_produtos"));
                    System.out.println("|| PRODUTO    : " + resultSet.getString("nome_produtos"));
                    System.out.println("|| QUANTIDADE : " + resultSet.getInt("quantidade"));
                    System.out.println("|| PREÇO UNIT.: R$ " + String.format("%.2f", resultSet.getDouble("preco_unitario")));
                    System.out.println("|| SUBTOTAL   : R$ " + String.format("%.2f", resultSet.getDouble("subtotal")));

                    Console.linhaSimples();
                }
                Console.linha();

            }

        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao consultar os itens do pedido: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    

}