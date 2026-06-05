package DAO.Relatorio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import conexao.Conexao;
import util.Console;

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
    public void comissaoPorVendedor(int idVendedor, LocalDateTime periInicio, LocalDateTime periFinal){
        conn = Conexao.getConexao();
        String sql= """
                SELECT
                    v.id_vendedor,
                    v.nome_vendedor,
                    SUM(p.valor_total) AS total_vendas,
                    SUM(p.valor_total * 0.01) AS comissao
                FROM vendedor v
                JOIN pedido p ON v.id_vendedor = p.id_vendedor
                WHERE p.id_vendedor = ?
                AND p.data_pedido BETWEEN ? AND ?
                GROUP BY v.id_vendedor, v.nome_vendedor
                """;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVendedor);
            stmt.setTimestamp(2, Timestamp.valueOf(periInicio));
            stmt.setTimestamp(3, Timestamp.valueOf(periFinal));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome_vendedor");
                BigDecimal totalVendas = rs.getBigDecimal("total_vendas");
                BigDecimal comissao = rs.getBigDecimal("comissao");
                Console.linha();
                System.out.println("||Vendedor: " + nome);
                System.out.println("||Total vendido: R$ " + totalVendas);
                System.out.println("||Comissão: R$ " + comissao);
                Console.linha();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void comissaoTodosVendedor( LocalDateTime periInicio, LocalDateTime periFinal){
        conn = Conexao.getConexao();
        String sql= """
                SELECT
                    v.id_vendedor,
                    v.nome_vendedor,
                    SUM(p.valor_total) AS total_vendas,
                    SUM(p.valor_total * 0.01) AS comissao
                FROM vendedor v
                JOIN pedido p ON v.id_vendedor = p.id_vendedor
                WHERE p.data_pedido BETWEEN ? AND ?
                GROUP BY v.id_vendedor, v.nome_vendedor
                """;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(periInicio));
            stmt.setTimestamp(2, Timestamp.valueOf(periFinal));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome_vendedor");
                BigDecimal totalVendas = rs.getBigDecimal("total_vendas");
                BigDecimal comissao = rs.getBigDecimal("comissao");
                Console.linha();
                System.out.println("||Vendedor: " + nome);
                System.out.println("||Total vendido: R$ " + totalVendas);
                System.out.println("||Comissão: R$ " + comissao);
                Console.linha();
            }
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
