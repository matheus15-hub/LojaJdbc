package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import conexao.Conexao;
import entidades.Endereco;
import entidades.Vendedor;

public class EnderecoVendedorDAO {
    Connection conn;
    PreparedStatement stmt;

    public boolean novoEnderecoVendedor(Vendedor idVendedor, Endereco idEndereco){
        String sql = "INSERT INTO vendedor_endereco(id_vendedor, id_endereco) VALUES (?, ?)";
        try{
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVendedor.getIdVendedor());
            stmt.setInt(2, idEndereco.getId_endereco());
            stmt.executeUpdate();
            stmt.close();
            stmt.close();
            conn.close();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao vincular endereço ao vendedor" + e.getMessage());
            return false;
        }
    }
    public boolean vincularEnderecoVendedor(Vendedor idVendedor, int idEndereco){
        String sql = "INSERT INTO vendedor_endereco(id_vendedor, id_endereco) VALUES (?, ?)";
        try{
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVendedor.getIdVendedor());
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
            stmt.close();
            stmt.close();
            conn.close();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao vincular endereço ao vendedor" + e.getMessage());
            return false;
        }
    }
}
