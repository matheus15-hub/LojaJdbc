package DAO;

import entidades.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;

public class VendedorDAO {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public void adicionarVendedor(Vendedor vendedor) {

        String sql = "INSERT INTO vendedor(nome_vendedor, telefone_vendedor, email_vendedor, comissao) VALUES (?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setString(2, vendedor.getTelefoneVendedor());
            stmt.setString(3, vendedor.getEmailVendedor());
            stmt.setDouble(4, vendedor.getComissao());

            stmt.executeUpdate();

            System.out.println("Vendedor adicionado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao adicionar vendedor: " + e.getMessage());
        }
    }
}
