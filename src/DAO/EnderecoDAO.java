package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexao.Conexao;
import entidades.Endereco;

public class EnderecoDAO {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rsEndereco;

    public int addEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco(rua, numero, bairro, cidade, cep) VALUES (?, ?, ?, ? , ?)";
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getCep());
            stmt.executeUpdate();
            rsEndereco = stmt.getGeneratedKeys();
            int id_Endereco = 0;
            if (rsEndereco.next()) {
                id_Endereco = rsEndereco.getInt(1);
            }
            stmt.close();
            rsEndereco.close();
            conn.close();
            return id_Endereco;
        } catch (Exception e) {
            System.out.println("Erro ao criar endereço: " + e.getMessage());
            return -1;
        }
    }
    public void excluirEndereco(Endereco e){
        String sql = "delete from endereco where id_endereco";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getId_endereco());
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}