package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import conexao.Conexao;
import entidades.Clientes;
import entidades.Endereco;

public class ClientesDAO {

    public  int addCliente(Clientes clientes) {
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
            conn.commit(); // Salva tudo de uma vez no banco
            return idCliente ;

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
        String sql = "SELECT c.id_clientes, c.nome_clientes, c.cpf_clientes, c.email_clientes, e.bairro, e.rua " +
                     "FROM clientes c " +
                     "LEFT JOIN cliente_endereco ce ON c.id_clientes = ce.id_clientes " +
                     "LEFT JOIN endereco e ON ce.id_endereco = e.id_endereco";
        
        try (Connection conn = Conexao.getConexao();
             Statement sts = conn.createStatement();
             ResultSet res = sts.executeQuery(sql)) {

            while (res.next()) {
                int id_clientes = res.getInt("id_clientes");
                String nome_clientes = res.getString("nome_clientes");
                String cpf = res.getString("cpf_clientes");
                String email_clientes = res.getString("email_clientes");
                
                linha();
                System.out.printf("|| ID: %5d | NOME: %-25s | CPF: %-15s | EMAIL: %-25s ||%n", 
                        id_clientes, nome_clientes, cpf, email_clientes);
                linha();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
    }

    public void mostrarId(int id) {
        String sql = "SELECT id_clientes, nome_clientes, cpf_clientes, email_clientes FROM clientes WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    linha();
                    System.out.printf("|| ID: %5d | NOME: %-25s | CPF: %-15s | EMAIL: %-25s ||%n", 
                            res.getInt("id_clientes"), res.getString("nome_clientes"), res.getString("cpf_clientes"), res.getString("email_clientes"));
                    linha();
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

    public void AlterarNomeClien(int id, String n) {
        String sql = "UPDATE clientes SET nome_clientes = ? WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AlterarCPFClien(int id, String n) {
        String sql = "UPDATE clientes SET cpf_clientes = ? WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AlterarEmailClien(int id, String n) {
        String sql = "UPDATE clientes SET email_clientes = ? WHERE id_clientes = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, n);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}