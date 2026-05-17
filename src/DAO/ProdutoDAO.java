package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entidades.Produto;

public class ProdutoDAO {


    public static void addProduto(Produto produto){
        PreparedStatement ps = null;
        String sql = "INSERT INTO produtos (nome_produtos, preco , estoque, categoria, medida_vendas) VALUES(?,?,?,?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome_Produtos());
            ps.setFloat(2,produto.getPreco());
            ps.setInt(3,produto.getEstoque());
            ps.setString(4, produto.getCategoria() );
            ps.setString(5, produto.getMedida_vendas());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void mostrarProduts(){
        Statement sts = null;
        ResultSet res = null;
        String sql = "Select * from produtos";
        try {
            sts = conexao.Conexao.getConexao().createStatement();
            res = sts.executeQuery(sql);

            while (res.next()){
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("categoria");
                String classe = res.getString("medida_vendas");

                System.out.printf("ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tCLASSE: %s%n" , id , nome,preco,estoque,categoria,classe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filtarProdutos(String nome ){
        PreparedStatement ps = null;
        ResultSet res = null;
        String sql ="select * from produtos where nome_produtos like ?";
        try{
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome + "%");
            res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id_produtos");
                nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("categoria");
                String classe = res.getString("medida_vendas");

                System.out.printf("ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tCLASSE: %s%n" , id , nome,preco,estoque,categoria,classe);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public  void excluirProduto(int d){
        PreparedStatement ps = null;
        String sql = "delete from produtos where id_produtos = ?";
    }
}
