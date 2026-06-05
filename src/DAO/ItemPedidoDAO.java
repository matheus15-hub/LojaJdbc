package DAO;

import util.Console;

import java.sql.Connection; // ALTERADO: Importado para gerenciar a conexão de forma segura
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemPedidoDAO {
    public void mostrarItemPedido(int id){
        String sql = "select * from item_pedido i join produtos p on i.id_produtos = p.id_produtos where id_pedido = ?";
        
        try (Connection conn = conexao.Conexao.criarNovaConexao();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    int idp = resultSet.getInt("id_produtos");
                    
                    String nomep = resultSet.getString("nome_produtos");
                    
                    int quant = resultSet.getInt("quantidade");
                    
                    double precV = resultSet.getDouble("preco_unitario");
                    
                    String mm = "UN"; 
                    
                    double sub = resultSet.getDouble("subtotal");
                    
                    Console.linhaSimples();
                    System.out.printf("|| %5d\t\t%-25s\t\t%5d\t\t%.2f\t\t%-5s\t\t%.2f ||%n", idp, nomep, quant, precV, mm, sub);
                }
                Console.linha();
            }
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao consultar os itens do pedido: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    

}