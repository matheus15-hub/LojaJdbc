package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import conexao.Conexao;
import entidades.Cliente;

public class ClienteDAO {

    public int addCliente(Cliente clientes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rsCliente = null;

        String sqlCliente = "INSERT INTO clientes (nome_clientes, cpf_clientes, email_clientes) VALUES (?, ?, ?)";
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, clientes.getNome_clientes());
            stmt.setString(2, clientes.getCpf());
            stmt.setString(3, clientes.getemail_clientes());
            stmt.executeUpdate();

            rsCliente = stmt.getGeneratedKeys();
            int idCliente = 0;
            if (rsCliente.next()) {
                 idCliente = rsCliente.getInt(1);
            }
            conn.commit(); 
            return idCliente;

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (rsCliente != null) rsCliente.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void removerCliente(int id_clientes) {
        String sql = "DELETE FROM clientes WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_clientes);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o cliente: " + e.getMessage());
        }
    }

    public void mostrarClient() {
        String sql = "select * from clientes c " +
                     "join cliente_endereco ce on c.id_clientes = ce.id_clientes " +
                     "join endereco e on ce.id_endereco = e.id_endereco";
        
        try (Connection conn = Conexao.getConexao();
             Statement sts = conn.createStatement();
             ResultSet res = sts.executeQuery(sql)) {

            while (res.next()) {
                int id_clientes = res.getInt("id_clientes");
                String nome_clientes = res.getString("nome_clientes");
                String cpf = res.getString("cpf_clientes");
                String email = res.getString("email_clientes");

                String rua = res.getString("rua");
                String numero = res.getString("numero");
                String bairro = res.getString("bairro");
                String cidade = res.getString("cidade");
                String cep = res.getString("cep");

                System.out.println("========================================================================================================================");
                System.out.printf("|| ID: %5d | NOME: %-25s | CPF: %-15s | EMAIL: %-25s ||%n", id_clientes, nome_clientes, cpf, email);
                System.out.println();
                System.out.printf("|| RUA: %-25s | Nº: %-10s | BAIRRO: %-20s | CIDADE: %-20s | CEP: %-10s ||%n", rua, numero, bairro, cidade, cep);
                System.out.println("========================================================================================================================");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
    }

    public void mostrarId(int id) {
        String sql = "SELECT * FROM clientes c " +
                     "JOIN cliente_endereco ce ON c.id_clientes = ce.id_clientes " +
                     "JOIN endereco e ON ce.id_endereco = e.id_endereco " +
                     "WHERE c.id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    int id_clientes = res.getInt("id_clientes");
                    String nome_clientes = res.getString("nome_clientes");
                    String cpf = res.getString("cpf_clientes");
                    String email = res.getString("email_clientes");
                    String rua = res.getString("rua");
                    String numero = res.getString("numero");
                    String bairro = res.getString("bairro");
                    String cidade = res.getString("cidade");
                    String cep = res.getString("cep");

                    System.out.println("========================================================================================================================");
                    System.out.printf("|| ID: %5d | NOME: %-25s | CPF: %-15s | EMAIL: %-25s ||%n", id_clientes, nome_clientes, cpf, email);
                    System.out.println();
                    System.out.printf("|| RUA: %-25s | Nº: %-10s | BAIRRO: %-20s | CIDADE: %-20s | CEP: %-10s ||%n", rua, numero, bairro, cidade, cep);
                    System.out.println("========================================================================================================================");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar id do cliente: " + e.getMessage());
        }
    }

    public void mostrarClientFiltro(String nome_clientes) {
        String sql = "SELECT id_clientes, nome_clientes, cpf_clientes, email_clientes FROM clientes WHERE nome_clientes LIKE ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement md = conn.prepareStatement(sql)) {
            
            md.setString(1, nome_clientes + "%");
            try (ResultSet resultSet = md.executeQuery()) {
                while (resultSet.next()) {
                    int id_clientes = resultSet.getInt("id_clientes");
                    String nome = resultSet.getString("nome_clientes");
                    String cpf = resultSet.getString("cpf_clientes");
                    String email_clientes = resultSet.getString("email_clientes");
                    linha();
                    System.out.printf("|| ID: %5d | NOME: %-25s | CPF: %-18s | EMAIL: %-25s ||%n", id_clientes, nome, cpf, email_clientes);
                    linha();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes com filtro: " + e.getMessage());
        }    
    }

    public static boolean vereficarExistencia(int h) {
        int contador = 0;
        String sql = "SELECT 1 FROM clientes WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, h);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    contador++;
                }
            }
            return contador > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void linha() {
        System.out.println("==================================================================================");
    }

    public void alterarNome(int id, String nome){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET nome_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarCpf(int id, String cpf){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET cpf_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarEmail(int id, String email){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET email_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listarParaPedido() {
        String sql = "SELECT id_clientes, nome_clientes FROM clientes";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-10s | %-30s%n", "ID", "NOME DO CLIENTE");
            System.out.println("-------------------------------------------------------");
            while (rst.next()) {
                System.out.printf("%-10d | %-30s%n", rst.getInt("id_clientes"), rst.getString("nome_clientes"));
            }
            System.out.println("-------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados básicos de clientes: " + e.getMessage());
        }
    }
}