package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

public class EnderecoDAO {
    Connection conn;
    PreparedStatement stmt;

    public boolean addEndereco(String rua, String bairro, String cep, String cidade) {
        String sql = "INSERT INTO endereco(rua, bairro, cep, cidade) VALUES (?, ?, ?, ?)";
        try{
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, rua);
            stmt.setString(2, bairro);
            stmt.setString(3, cep);
            stmt.setString(4, cidade);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch(Exception e) {
            System.out.println("Erro ao criar endereço: " + e.getMessage());
            return false;
        }
    }
}
