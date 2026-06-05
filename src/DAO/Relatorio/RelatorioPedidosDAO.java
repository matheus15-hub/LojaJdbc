package DAO.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class RelatorioPedidosDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet res = null;

    public void MaisItensPedido() {
        String sql = "select id_pedido, sum(quantidade) as QTD_Itens from item_pedido group by id_pedido"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_pedido");
                int quantidade = res.getInt("QTD_Itens");
                System.out.printf("||ID: %5d\tQuantidade de Itens: %d||%n",
                        id,quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisCaroPedido() {
        String sql = "select id_pedido, valor_total from pedido order by valor_total desc limit 1"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_pedido");
                float valor = res.getFloat("valor_total");
                System.out.printf("||ID: %5d\tValor Total: R$%.2f||%n", id, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisBaratoPedido() {
        String sql = "select id_pedido, valor_total from pedido order by valor_total asc limit 1"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_pedido");
                float valor = res.getFloat("valor_total");
                System.out.printf("||ID: %5d\tValor Total: R$%.2f||%n", id, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ValorMedioPedido() {
        String sql = "select avg(valor_total) as Valor_Medio from pedido"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                float valorMedio = res.getFloat("Valor_Medio");
                System.out.printf("Valor Médio dos Pedidos: R$%.2f%n", valorMedio);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}