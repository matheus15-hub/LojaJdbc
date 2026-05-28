package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ExecutionException;

import conexao.Conexao;

public class EnderecoClienteDAO {
    Connection conn;
    PreparedStatement stmt;

    public boolean VincularEnderecoCliente(int idCliente, int idEndereco){
        String sql = "INSERT INTO cliente_endereco(id_clientes, id_endereco) VALUES(?, ?)";
        try{
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
            stmt.close();
            stmt.close();
            conn.close();
            return true;
        } catch(Exception e){
            System.out.println("Erro ao vincular endereço e o cliente: " + e.getMessage());
            return false;
        }
    }
}
