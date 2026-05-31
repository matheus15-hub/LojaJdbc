package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.Conexao;
import entidades.Produto;

public class ProdutoDAO {

    public static void addProduto(Produto produto) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO produtos (nome_produtos, preco , estoque, idClasse, idUnidade) VALUES(?,?,?,?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome_Produtos());
            ps.setBigDecimal(2, produto.getPreco());
            ps.setInt(3, produto.getEstoque());
            ps.setInt(4, produto.getIdClasse());
            ps.setInt(5, produto.getIdUnidade());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void mostrarProduts() {
        Statement sts = null;
        ResultSet res = null;
        String sql = "Select * from produtos p join classe c on p.idClasse = c.idClasse join unidade_medida u on p.idUnidade = u.idUnidade";
        try {
            sts = conexao.Conexao.getConexao().createStatement();
            res = sts.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("nome_classe");
                String classe = res.getString("sigla_medida");
                linha();
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-15s\tMEDIDA: %-5s||%n",
                        id, nome, preco, estoque, categoria, classe);
            }   linha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filtarProdutos(String nome) {
        PreparedStatement ps = null;
        ResultSet res = null;
        String sql = "select * from produtos p " +
             "join classe c on p.idClasse = c.idClasse " +
             "join unidade_medida u on p.idUnidade = u.idUnidade " +
             "where nome_produtos like ?";
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome + "%");
            res = ps.executeQuery();

            while (res.next()) {
                int id = res.getInt("id_produtos");
                nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("nome_classe");
                String classe = res.getString("sigla_medida");
                linha();
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tMEDIDA: %-5s||%n",
                        id, nome, preco, estoque, categoria, classe);

            }   linha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filtarProdutosId(int d) {
        PreparedStatement ps = null;
        ResultSet res = null;
        String sql = "select * from produtos p " +
                "join classe c on p.idClasse = c.idClasse " +
                "join unidade_medida u on p.idUnidade = u.idUnidade " +
                "where id_produtos = ?";
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, d);
            res = ps.executeQuery();

            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("nome_classe");
                String classe = res.getString("sigla_medida");
                linha();
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tMEDIDA: %-5s||%n",
                        id, nome, preco, estoque, categoria, classe);

            }   linha();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirProduto(int d) {
        PreparedStatement ps = null;
        String sql = "delete from produtos where id_produtos = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1 , d);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // verifica se o produto existe realmente no banco de dados verficicar com o
    // matheus
    public static boolean produtoExiste(int idProduto) {
        Connection conn = Conexao.getConexao();
        String sql = "SELECT * FROM produtos WHERE id_produtos = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar produto: " + e.getMessage());
            return false;
        }
    }

    // vereficar com matheus tmb isso aqui serve para validar no estoque o que tem
    // la

    public static int buscarEstoque(int idProduto) {
        Connection conn = Conexao.getConexao();
        String sql = "SELECT estoque FROM produtos WHERE id_produtos = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("estoque");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar estoque: " + e.getMessage());
        }
        return 0;
    }

    // essa parte aqui serve para pegar o preço direto no banco sem que o usuario
    // digite o valor
    public static double buscarPreco(int idProduto) {
        Connection conn = Conexao.getConexao();
        String sql = "SELECT preco FROM produtos WHERE id_produtos = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar preço: " + e.getMessage());
        }
        return 0;
    }
    public static void linha(){
        System.out.println("=======================================================================================================================");
    }
    public void alterarnome(int id,String n){
        PreparedStatement ps = null;
        String sql = "UPDATE produtos SET nome_produtos = ? WHERE id_produtos = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,n);
            ps.setInt(2,id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarpreco(int id , BigDecimal p){
        PreparedStatement ps = null;
        String sql = "UPDATE produtos SET preco = ? WHERE id_produtos = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setBigDecimal(1,p);
            ps.setInt(2,id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarEstoque(int id, int estoque){
    PreparedStatement ps = null;
    String sql = "UPDATE produtos SET estoque_produtos = ? WHERE id_produtos = ?";
    try {
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1, estoque);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}
