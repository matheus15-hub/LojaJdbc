package DAO;

import entidades.Vendedor;

import util.Console;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import conexao.Conexao;

public class VendedorDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public int addVendedor(Vendedor vendedor) {

        String sql = "insert into vendedor" +
                "(nome_vendedor, telefone_vendedor, email_vendedor, salario)" +
                "values (?, ?, ?, ?);";

        int ultimoId = -1;

        try {

            Connection conn = Conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setString(2, vendedor.getTelefoneVendedor());
            stmt.setString(3, vendedor.getEmailVendedor());
            stmt.setBigDecimal(4, vendedor.getSalario());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                ultimoId = rs.getInt(1);
            }

            rs.close();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao adicionar vendedor: " + e.getMessage());
        }

        return ultimoId;
    }

    public void mostrarVendedor() {
    String sql = """
            select * from vendedor v
            left join vendedor_endereco ve on v.id_vendedor = ve.id_vendedor
            left join endereco e on ve.id_endereco = e.id_endereco
            """;

    try {
        Statement sts = Conexao.getConexao().createStatement();
        rs = sts.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id_vendedor");
            String nome = rs.getString("nome_vendedor");
            String tel = rs.getString("telefone_vendedor");
            String email = rs.getString("email_vendedor");
            
            Console.linha();
            System.out.printf("|| ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tEMAIL: %s||%n", id, nome, tel, email);
            
            String rua = rs.getString("rua") != null ? rs.getString("rua") : "Não cadastrada";
            String numero = rs.getString("numero") != null ? rs.getString("numero") : "-";
            String bairro = rs.getString("bairro") != null ? rs.getString("bairro") : "-";
            String cidade = rs.getString("cidade") != null ? rs.getString("cidade") : "-";
            String cep = rs.getString("cep") != null ? rs.getString("cep") : "-";
            
            Console.linhaSimples();
            System.out.printf("|| RUA:    %-30s\t\t\t\t\t\t\t ||%n|| Nº:     %-30s\t\t\t\t\t\t\t ||%n|| BAIRRO: %-30s\t\t\t\t\t\t\t ||%n|| CIDADE: %-30s\t\t\t\t\t\t\t ||%n|| CEP:    %-30s\t\t\t\t\t\t\t ||%n", rua, numero, bairro, cidade, cep);
            Console.linha();
        }
        rs.close();
        sts.close();

    } catch (Exception e) {
        System.out.println("Erro ao mostrar vendedores: " + e.getMessage());
    }
}

    public Vendedor buscarPorId(int idBusca) {
        String sql = "SELECT * FROM vendedor WHERE id_vendedor=?";
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBusca);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_vendedor");
                String nome = rs.getString("nome_vendedor");
                String telefone = rs.getString("telefone_vendedor");
                String email = rs.getString("email_vendedor");
                java.math.BigDecimal salario = rs.getBigDecimal("salario");

                Vendedor v = new Vendedor();
                v.setIdVendedor(id);
                v.setNomeVendedor(nome);
                v.setTelefoneVendedor(telefone);
                v.setEmailVendedor(email);
                v.setSalario(salario);

                rs.close();
                stmt.close();
                conn.close();
                return v;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Erro ao buscar por ID: " + e.getMessage());
        }
        return null;
    }

    public void mostrarVendedorPorFiltro(String nomePesquisa) {
        String sql = """
                select * from vendedor_endereco ve
                join vendedor v on ve.id_vendedor = v.id_vendedor
                join endereco e on ve.id_endereco = e.id_endereco
                where v.nome_vendedor like ?
                """;
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomePesquisa + "%");
            rs = stmt.executeQuery();
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                Console.linha();
                System.out.println("|| ID: " + rs.getInt("id_vendedor"));
                System.out.println("|| Nome: " + rs.getString("nome_vendedor"));
                System.out.println("|| Telefone: " + rs.getString("telefone_vendedor"));
                System.out.println("|| Email: " + rs.getString("email_vendedor"));

                Console.linhaSimples();
                System.out.println("|| ENDEREÇO:");

                System.out.println("|| Rua: " + rs.getString("rua"));
                System.out.println("|| Número: " + rs.getString("numero"));
                System.out.println("|| Bairro: " + rs.getString("bairro"));
                System.out.println("|| Cidade: " + rs.getString("cidade"));
                System.out.println("|| CEP: " + rs.getString("cep"));

                Console.linha();
            }
            if (!encontrou) {
                System.out.println("Nenhum vendedor encontrado.");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar vendedor: " + e.getMessage());
        }
    }

    public static boolean verificarExistencia(int h) {
    String sql = "SELECT COUNT(*) FROM vendedor WHERE id_vendedor = ?";
    try (Connection conn = Conexao.getConexao();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, h);
        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao verificar vendedor: " + e.getMessage());
    }
    return false;
    }

    public void excluirVendedor(int v) {

        String sql = "DELETE FROM vendedor WHERE id_vendedor = ?";

        try {

            conn = Conexao.getConexao();

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, v);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void alterarNome(int idVendedor, String novoNome) {

        String sql = "uptade vendedor" +
                "set nome_vendedor = ?" +
                "where id_vendedor = ?";

        try {

            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, novoNome);
            stmt.setInt(2, idVendedor);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao alterar nome: " + e.getMessage());
        }
    }

    public void alterarTelefone(int idVendedor, String novoTelefone) {

        String sql = "update vendedor" +
                "set telefone_vendedor = ?" +
                "where id_vendedor = ?";

        try {

            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, novoTelefone);
            stmt.setInt(2, idVendedor);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao alterar telefone: " + e.getMessage());
        }
    }

    public void alterarEmail(int idVendedor, String novoEmail) {

        String sql = "update vendedor" +
                "set email_vendedor = ?" +
                "where id_vendedor = ?";

        try {

            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, novoEmail);
            stmt.setInt(2, idVendedor);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao alterar email: " + e.getMessage());
        }
    }

    public void alterarSalario(int idVendedor, BigDecimal novoSalario) {

        String sql = "update vendedor" +
                "set salario = ?" +
                "where id_vendedor = ?";

        try {

            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, novoSalario);
            stmt.setInt(2, idVendedor);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao alterar salário: " + e.getMessage());
        }
    }
    public void listarParaPedido() {
        String sql = "SELECT id_vendedor, nome_vendedor FROM vendedor";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                int idVendedor = rst.getInt("id_vendedor");
                String nome = rst.getString("nome_vendedor");

                Console.linha();
                System.out.println("|| ID: " + idVendedor);
                System.out.println("|| Nome: " + nome);
                Console.linha();
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados básicos dos vendedores: " + e.getMessage());
        }
    }
    public void listarParaPedidoFiltro(String nomePesquisa) {
        String sql = """
                 SELECT id_vendedor, nome_vendedor
                 FROM vendedor
                 WHERE nome_vendedor LIKE ?
                 """;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, "%" + nomePesquisa + "%");

            try (ResultSet rst = pstm.executeQuery()) {

                while (rst.next()) {
                    int idVendedor = rst.getInt("id_vendedor");
                    String nome = rst.getString("nome_vendedor");

                    Console.linha();
                    System.out.println("|| ID: " + idVendedor);
                    System.out.println("|| Nome: " + nome);
                    Console.linha();
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados básicos dos vendedores: " + e.getMessage());
        }
    }
}
