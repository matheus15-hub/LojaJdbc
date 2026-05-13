package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entidades.Produto;

public class ProdutoDAO {


    public static void addProduto(Produto produto){
        PreparedStatement ps = null;
        String sql = "INSERT INTO produtos (nome_produtos, preco , estoque) VALUES(?,?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome_Produtos());
            ps.setFloat(2,produto.getPreco());
            ps.setInt(3,produto.getEstoque());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void mostrarProduts(Produto produto){
        Statement sts = null;
        ResultSet res = null;
        String sql = "Select * from produtos";
        try {
            sts = conexao.Conexao.getConexao().createStatement();
            res = sts.executeQuery(sql);

            while (res.next()){
                int id = res.getInt("id");
                String nome = res.getString("nome");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");

                System.out.printf("ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d%n" , id , nome,preco,estoque);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
