package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemPedidoDao {
    public  void mostrarItemPedido(int id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "Select * from item_pedido i join produtos p on i.id_produtos = p.id_produtos where id_pedido = ?";
        try {
            preparedStatement = conexao.Conexao.getConexao().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int idp = resultSet.getInt("id_produtos");
                String nomep = resultSet.getNString("nome_produtos");
                int quant = resultSet.getInt("quantidade");
                float precV = resultSet.getFloat("preco_unitario");
                String mm = resultSet.getNString("medida_vendas");
                float sub = resultSet.getFloat("subtotal");
                linha();
                System.out.printf("|| %5d\t\t%-25s\t\t%5d\t\t%.2f\t\t%-5s\t\t%.2f ||%n", idp, nomep,quant , precV,mm,sub );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public  static void linha(){
        System.out.println("================================================================================");
    }
}
