package DAO.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class RelatorioProdutoDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet res = null;

    
    public void QuantidadeProduto() {
        String sql = "select * from produtos where estoque = (select max(estoque) from produtos)"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("idclasse");
                String classe = res.getString("idunidade");
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-15s\tMEDIDA: %-5s||%n",
                        id, nome, preco, estoque, categoria, classe);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}