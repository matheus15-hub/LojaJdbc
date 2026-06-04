package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public static void  mostrarEnderecoVendedor(int id) {
    String sql = "SELECT * FROM vendedor v " +
                 "JOIN vendedor_endereco ve ON v.id_vendedor = ve.id_vendedor " +
                 "JOIN endereco e ON ve.id_endereco = e.id_endereco " +
                 "WHERE ve.id_vendedor = ?";

    try {
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            int id_endereco = res.getInt("id_endereco");
            String rua = res.getString("rua");
            String numero = res.getString("numero");
            String bairro = res.getString("bairro");
            String cidade = res.getString("cidade");
            String cep = res.getString("cep");

            System.out.println("========================================================================================================================");
            System.out.printf(
                "||ID: %5d | RUA: %-25s | Nº: %-10s | BAIRRO: %-20s | CIDADE: %-20s | CEP: %-10s ||%n",
                id_endereco, rua, numero, bairro, cidade, cep
            );
            System.out.println("========================================================================================================================");
        }

    } catch (Exception e) {
        throw new RuntimeException("Erro ao listar endereços do vendedor: " + e.getMessage());
    }
}
public static boolean verificarEnderecoVendedor(int idVendedor, int idEndereco) {
    String sql = "SELECT 1 FROM vendedor_endereco " +
                 "WHERE id_vendedor = ? AND id_endereco = ?";

    try {
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, idVendedor);
        stmt.setInt(2, idEndereco);

        ResultSet rs = stmt.executeQuery();

        return rs.next();

    } catch (Exception e) {
        throw new RuntimeException("Erro ao verificar endereço do vendedor: " + e.getMessage());
    }
}
}
