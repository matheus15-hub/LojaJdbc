package DAO;

import entidades.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import conexao.Conexao;

public class VendedorDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public boolean addVendedor(Vendedor vendedor) {
        String sql = "insert into vendedor(nome_vendedor, telefone_vendedor, email_vendedor, salario) values (?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setString(2, vendedor.getTelefoneVendedor());
            stmt.setString(3, vendedor.getEmailVendedor());
            stmt.setBigDecimal(4, vendedor.getSalario());

            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            return true; 

        } catch (Exception e) {
            System.out.println("Erro ao adicionar vendedor: " + e.getMessage());
            return false;
        }
    }

    public void mostrarVendedor() {
        String sql = "select * from vendedor";

        try {
            Statement sts = Conexao.getConexao().createStatement();
            rs = sts.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_vendedor");
                String nome = rs.getString("nome_vendedor");
                String tel = rs.getString("telefone_vendedor");
                String email = rs.getString("email_vendedor");

                System.out.printf("ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tEMAIL: %s%n", id, nome, tel, email);
                linha();
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

public boolean addComissao(int idPedido) {

    String sqlPedido = """
        SELECT p.valor_total, p.status_pedido, p.id_vendedor
        FROM pedido p
        WHERE p.id_pedido = ?
    """;

    String sqlComissao = """
        UPDATE vendedor
        SET comissao = comissao + ?
        WHERE id_vendedor = ?
    """;

    try {

        conn = Conexao.getConexao();

        // ================= BUSCA DADOS DO PEDIDO =================
        stmt = conn.prepareStatement(sqlPedido);
        stmt.setInt(1, idPedido);

        rs = stmt.executeQuery();

        if (!rs.next()) {
            System.out.println("Pedido não encontrado.");
            return false;
        }

        java.math.BigDecimal valorTotal = rs.getBigDecimal("valor_total");

        String statusPedido = rs.getString("status_pedido")
                                .toUpperCase();

        int idVendedor = rs.getInt("id_vendedor");

        rs.close();
        stmt.close();

        // ================= VALIDA STATUS =================
        if (!statusPedido.equals("CONCLUIDO")) {

            System.out.println(
                "Comissão não adicionada. " +
                "Pedidos com status '" + statusPedido +
                "' não geram comissão."
            );

            conn.close();
            return false;
        }

        // ================= CALCULA 1% =================
        java.math.BigDecimal comissao = valorTotal.multiply(new java.math.BigDecimal("0.01"));

        // ================= ADICIONA COMISSÃO =================
        stmt = conn.prepareStatement(sqlComissao);

        stmt.setBigDecimal(1, comissao);
        stmt.setInt(2, idVendedor);

        stmt.executeUpdate();

        stmt.close();
        conn.close();

        System.out.println("Comissão adicionada com sucesso.");
        return true;

    } catch (Exception e) {

        System.out.println("Erro ao adicionar comissão: " + e.getMessage());

        return false;
    }
}
    public void mostrarVendedorFiltro(String nomePesquisa) {
        String sql = "select * from vendedor where nome_vendedor like ?";
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomePesquisa + "%");
            rs = stmt.executeQuery();
            boolean encontrou = false;
            while (rs.next()) {
                encontrou = true;
                int id = rs.getInt("id_vendedor");
                String nome = rs.getString("nome_vendedor");
                String tel = rs.getString("telefone_vendedor");
                String email = rs.getString("email_vendedor");
                System.out.printf("ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tEMAIL: %s%n", id, nome, tel, email);
            }
            if (!encontrou) { System.out.println("Nenhum vendedor encontrado."); }
            rs.close(); stmt.close(); conn.close();
        } catch (Exception e) { System.out.println("Erro ao pesquisar vendedor: " + e.getMessage()); }
    }

    public static boolean verificarExistencia(int h) {
        PreparedStatement ps = null; ResultSet resultSet = null;
        String sql = "SELECT COUNT(*) FROM vendedor WHERE id_vendedor=?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, h); resultSet = ps.executeQuery();
            if (resultSet.next()) {
                boolean existe = resultSet.getInt(1) > 0;
                resultSet.close(); ps.close(); return existe;
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
        return false;
    }

    public static void linha() {
        System.out.println("========================================================================================================================================================================================");
    }
}