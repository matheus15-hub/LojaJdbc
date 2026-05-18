package DAO;

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
        String sql = "INSERT INTO produtos (nome_produtos, preco , estoque, categoria, medida_vendas) VALUES(?,?,?,?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome_Produtos());
            ps.setFloat(2, produto.getPreco());
            ps.setInt(3, produto.getEstoque());
            ps.setString(4, produto.getCategoria());
            ps.setString(5, produto.getMedida_vendas());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void mostrarProduts() {
        Statement sts = null;
        ResultSet res = null;
        String sql = "Select * from produtos";
        try {
            sts = conexao.Conexao.getConexao().createStatement();
            res = sts.executeQuery(sql);

            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("categoria");
                String classe = res.getString("medida_vendas");

                System.out.printf("ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tCLASSE: %s%n",
                        id, nome, preco, estoque, categoria, classe);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void filtarProdutos(String nome) {
        PreparedStatement ps = null;
        ResultSet res = null;
        String sql = "select * from produtos where nome_produtos like ?";
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome + "%");
            res = ps.executeQuery();

            while (res.next()) {
                int id = res.getInt("id_produtos");
                nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int estoque = res.getInt("estoque");
                String categoria = res.getString("categoria");
                String classe = res.getString("medida_vendas");

                System.out.printf("ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tEstoque: %d\tCATEGORIA: %-12s\tCLASSE: %s%n",
                        id, nome, preco, estoque, categoria, classe);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirProduto(int d) {
        PreparedStatement ps = null;
        String sql = "delete from produtos where id_produtos = ?";
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
}
