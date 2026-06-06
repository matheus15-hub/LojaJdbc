package DAO;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import conexao.Conexao;
import entidades.Cliente;
import entidades.Endereco;
import util.Console;

public class EnderecoClienteDAO {
    Connection conn;
    PreparedStatement stmt;
 
    public void novoClienteEndereco(Cliente clientes, Endereco endereco) {
        String sql = "INSERT INTO cliente_endereco(id_clientes, id_endereco) VALUES(?, ?)";
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clientes.getId_clientes());
            stmt.setInt(2, endereco.getId_endereco());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao vincular endereço e o cliente: " + e.getMessage());
        }
    }
 
    public void vincularEnderecoCliente(Cliente clientes, int idEndereco) {
        String sql = "INSERT INTO cliente_endereco(id_clientes, id_endereco) VALUES(?, ?)";
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, clientes.getId_clientes());
            stmt.setInt(2, idEndereco);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao vincular endereço e o cliente: " + e.getMessage());
        }
    }
 
    public void mostrarEnderecoCliente(int id) {
        String sql = "select * from clientes c " +
                     "join cliente_endereco ce on c.id_clientes = ce.id_clientes " +
                     "join endereco e on ce.id_endereco = e.id_endereco " +
                     "where ce.id_clientes = ?";
        try {
            Connection conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id_endereco = res.getInt("id_endereco");
                String rua = res.getString("rua");
                String numero = res.getString("numero");
                String bairro = res.getString("bairro");
                String cidade = res.getString("cidade");
                String cep = res.getString("cep");
                Console.linha();

                    Console.linhaSimples();
                    System.out.println("|| ID Endereço: " + res.getInt("id_endereco"));
                    System.out.println("|| Rua: " + res.getString("rua"));
                    System.out.println("|| Número: " + res.getString("numero"));
                    System.out.println("|| Bairro: " + res.getString("bairro"));
                    System.out.println("|| Cidade: " + res.getString("cidade"));
                    System.out.println("|| CEP: " + res.getString("cep"));

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar endereços do cliente: " + e.getMessage());
        }
    }

    public boolean verificarEnderecoCliente(int idcliente, int idendereco) {
        String sql = "SELECT * FROM cliente_endereco WHERE id_clientes = ? AND id_endereco = ?";
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idcliente);
            stmt.setInt(2, idendereco);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar endereço do cliente: " + e.getMessage());
        }
    }

    public int getIdEnderecoCliente(int idcliente , int idendereco){
        String sql = "SELECT * FROM cliente_endereco WHERE id_clientes = ? AND id_endereco = ?";
        try {
            int id_endereco_cliente =0;
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idcliente);
            stmt.setInt(2, idendereco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                id_endereco_cliente = rs.getInt("id_cliente_endereco");
            }
                return id_endereco_cliente;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar endereço do cliente: " + e.getMessage());
        }
    }

    public void excluirEnderecoCliente(int idEndereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try (
                Connection conn = Conexao.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idEndereco);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir endereço do cliente: " + e.getMessage());
        }
    }
}