package DAO.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class RelatorioVendedorDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet res = null;

    public void MaiorVendaVendedor() {
        String sql = "select id_vendedor, (select nome_vendedor from vendedor where pedido.id_vendedor = vendedor.id_vendedor) as Nome_vendedor, max(valor_total) as Maior_Venda from pedido group by id_vendedor order by max(valor_total) desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("Nome_vendedor");
                float valor = res.getFloat("Maior_Venda");
                System.out.printf("||ID: %5d\tNOME: %-25s\tValor da Maior Venda: R$%.2f||%n", id, nome, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void MenorVendaVendedor() {
        String sql = "select id_vendedor, (select nome_vendedor from vendedor where pedido.id_vendedor = vendedor.id_vendedor) as Nome_vendedor, min(valor_total) as Menor_Venda from pedido group by id_vendedor order by min(valor_total) asc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("Nome_vendedor");
                float valor = res.getFloat("Menor_Venda");
                System.out.printf("||ID: %5d\tNOME: %-25s\tValor da Menor Venda: R$%.2f||%n", id, nome, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisVendasVendedor() {
        String sql = "select id_vendedor, (select nome_vendedor from vendedor where pedido.id_vendedor = vendedor.id_vendedor) as Nome_vendedor, count(*) as Quantidade_Vendas from pedido group by id_vendedor order by count(*) desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("Nome_vendedor");
                int quantidade = res.getInt("Quantidade_Vendas");
                System.out.printf("||ID: %5d\tNOME: %-25s\tQuantidade de Vendas: %d||%n", id, nome, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MenosVendasVendedor() {
        String sql = "select id_vendedor, (select nome_vendedor from vendedor where pedido.id_vendedor = vendedor.id_vendedor) as Nome_vendedor, count(*) as Quantidade_Vendas from pedido group by id_vendedor order by count(*) asc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("Nome_vendedor");
                int quantidade = res.getInt("Quantidade_Vendas");
                System.out.printf("||ID: %5d\tNOME: %-25s\tQuantidade de Vendas: %d||%n", id, nome, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaiorSalarioVendedor() {
        String sql = "select id_vendedor, nome_vendedor, salario from vendedor order by salario desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("nome_vendedor");
                float salario = res.getFloat("salario");
                System.out.printf("||ID: %5d\tNOME: %-25s\tSalário: R$%.2f||%n", id, nome, salario);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MenorSalarioVendedor() {
        String sql = "select id_vendedor, nome_vendedor, salario from vendedor order by salario asc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("nome_vendedor");
                float salario = res.getFloat("salario");
                System.out.printf("||ID: %5d\tNOME: %-25s\tSalário: R$%.2f||%n", id, nome, salario);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }

     public void MediaVendaVendedor() {
        String sql = "select id_vendedor, (select nome_vendedor from vendedor where pedido.id_vendedor = vendedor.id_vendedor) as Nome_vendedor, avg(valor_total) as Media_Venda from pedido group by id_vendedor;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_vendedor");
                String nome = res.getString("Nome_vendedor");
                float valor = res.getFloat("Media_Venda");
                System.out.printf("||ID: %5d\tNOME: %-25s\tValor da Média de Venda: R$%.2f||%n", id, nome, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
