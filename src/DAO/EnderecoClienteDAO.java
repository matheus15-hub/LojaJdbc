package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

import conexao.Conexao;
import entidades.Clientes;
import entidades.Endereco;

public class EnderecoClienteDAO {
    Connection conn;
    PreparedStatement stmt;


    public void VincularEnderecoCliente(Clientes clientes, Endereco endereco){
        String sql = "INSERT INTO cliente_endereco(id_clientes, id_endereco) VALUES(?, ?)";
        try{
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clientes.getId_clientes());
            stmt.setInt(2, endereco.getId_endereco());
            stmt.executeUpdate();
            stmt.close();
            conn.close();


        } catch(Exception e){
            System.out.println("Erro ao vincular endereço e o cliente: " + e.getMessage());

        }
    }
}
