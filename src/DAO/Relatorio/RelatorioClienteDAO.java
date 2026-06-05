package DAO.Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class RelatorioClienteDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet res = null;

    public void ClientePorBairro(){
        String sql = "select bairro, count(id_clientes) as Quantidade_Clientes from cliente_endereco ce join endereco e on ce.id_endereco = e.id_endereco group by bairro"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                String bairro = res.getString("bairro");
                int quantidade = res.getInt("Quantidade_Clientes");
                System.out.printf("||BAIRRO: %-25s\tQuantidade de Clientes: %d||%n", bairro, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ClientePorCidade(){
        String sql = "select cidade, count(id_clientes) as Quantidade_Clientes from cliente_endereco ce join endereco e on ce.id_endereco = e.id_endereco group by cidade"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                String cidade = res.getString("cidade");
                int quantidade = res.getInt("Quantidade_Clientes");
                System.out.printf("||CIDADE: %-25s\tQuantidade de Clientes: %d||%n", cidade, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisComprasCliente(){
        String sql = "select id_clientes, (select nome_clientes from clientes where pedido.id_clientes = clientes.id_clientes) as Nome_cliente, count(*) as Quantidade_Pedidos from pedido group by id_clientes order by count(*) desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_clientes");
                String nome = res.getString("Nome_cliente");
                int quantidade = res.getInt("Quantidade_Pedidos");
                System.out.printf("||ID: %5d\tNOME: %-25s\tQuantidade de Pedidos: %d||%n", id, nome, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MenosComprasCliente(){
        String sql = "select id_clientes, (select nome_clientes from clientes where pedido.id_clientes = clientes.id_clientes) as Nome_cliente, count(*) as Quantidade_Pedidos from pedido group by id_clientes order by count(*) asc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_clientes");
                String nome = res.getString("Nome_cliente");
                int quantidade = res.getInt("Quantidade_Pedidos");
                System.out.printf("||ID: %5d\tNOME: %-25s\tQuantidade de Pedidos: %d||%n", id, nome, quantidade);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MaisCaraCliente(){
        String sql = "select id_clientes, (select nome_clientes from clientes where pedido.id_clientes = clientes.id_clientes) as Nome_cliente, max(valor_total) as Valor_Pedido from pedido group by id_clientes order by max(valor_total) desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_clientes");
                String nome = res.getString("Nome_cliente");
                float valor = res.getFloat("Valor_Pedido");
                System.out.printf("||ID: %5d\tNOME: %-25s\tValor do Pedido Mais Caro: R$%.2f||%n", id, nome, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void MediaCompraCliente(){
        String sql = "select id_clientes, (select nome_clientes from clientes where pedido.id_clientes = clientes.id_clientes) as Nome_cliente, round(avg(valor_total), 2) as Valor_Medio from pedido group by id_clientes order by avg(valor_total) desc limit 1;"; 
        try {
            stmt = conexao.Conexao.getConexao().prepareStatement(sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int id = res.getInt("id_clientes");
                String nome = res.getString("Nome_cliente");
                float valor = res.getFloat("Valor_Medio");
                System.out.printf("||ID: %5d\tNOME: %-25s\tValor Médio dos Pedidos: R$%.2f||%n", id, nome, valor);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
