package DAO.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class RelatorioProdutoDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet res = null;

    
    public void MaiorQuantidadeProduto() {
        String sql = "select * from produtos where estoque = (select max(estoque) from produtos)"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                int estoque = res.getInt("estoque");
                System.out.printf("||ID: %5d\tNOME: %-25s\tEstoque: %d||%n",
                        id, nome, estoque);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void PrecoAltoProduto() {
        String sql = "select * from produtos where preco = (select max(preco) from produtos)"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f||%n",
                        id, nome, preco);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void PrecoBaixoProduto() {
        String sql = "select * from produtos where preco = (select min(preco) from produtos)"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f||%n",
                        id, nome, preco);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisVendidoProduto() {
        String sql = "select *, (select SUM(quantidade) from item_pedido where item_pedido.id_produtos = produtos.id_produtos ) AS QTD_Vendida from produtos order by QTD_Vendida desc limit 1"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_produtos");
                String nome = res.getString("nome_produtos");
                float preco = res.getFloat("preco");
                int QTD_Vendida = res.getInt("QTD_Vendida");
                System.out.printf("||ID: %5d\tNOME: %-25s\tPRECO: R$%.2f\tQTD_Vendida: %d||%n",
                        id, nome, preco, QTD_Vendida);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void QuantidadeCategoriaProduto() {
        String sql = "select idclasse, count(*) as QTDClasse from produtos group by idclasse order by id_produtos desc"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery();
            while (res.next()) {
                int idClasse = res.getInt("idclasse");
                int qtdClasse = res.getInt("QTDClasse");
                System.out.printf("Classe: %d\tQuantidade: %d%n",
                    idClasse,qtdClasse);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MediaValorProduto() {
        String sql = "select round(avg(preco), 2) as ValorMedio from produtos"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery();
            while (res.next()) {
                float mediaPreco = res.getFloat("ValorMedio");
                System.out.printf("Media Preço dos Produtos: R$%.2f%n",
                    mediaPreco);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}