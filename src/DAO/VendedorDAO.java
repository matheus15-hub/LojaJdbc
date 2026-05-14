package DAO;

import entidades.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public void mostrarVendedor(Vendedor vendedor){
        String sql = "select * from vendedor";
        Statement sts = null;
        try {
            sts = Conexao.getConexao().createStatement();
            rs = sts.executeQuery(sql);

            while (rs.next()){
             int id = rs.getInt("id_vendedor");
             String nome = rs.getString("nome_vendedor");
             String tel = rs.getString("telefone_vendedor");
             String email = rs.getString("email_vendedor");
             float comissao = rs.getFloat("comissao");

             System.out.printf("ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tCOMISSAO: %.2f\tEMAIL: %s%n ", id,nome,tel,comissao,email);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
