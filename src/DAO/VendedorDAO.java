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

    public void adicionarVendedor(Vendedor vendedor) {

        String sql = "insert into vendedor(nome_vendedor, telefone_vendedor, email_vendedor, comissao) values (?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setString(2, vendedor.getTelefoneVendedor());
            stmt.setString(3, vendedor.getEmailVendedor());
            stmt.setDouble(4, vendedor.getComissao());

            stmt.executeUpdate();

            System.out.println("Vendedor adicionado com sucesso!");

        } catch(java.sql.SQLIntegrityConstraintViolationException e){
                System.out.println("Email já cadastrado!");
        }catch(Exception e){
                System.out.println("Erro:");
                System.out.println(e.getMessage());
        }}

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
                float comissao = rs.getFloat("comissao");

                System.out.printf(
                        "ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tCOMISSÃO: %.2f\tEMAIL: %s%n",
                        id, nome, tel, comissao, email
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao mostrar vendedores: " + e.getMessage());
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
                float comissao = rs.getFloat("comissao");

                System.out.printf(
                        "ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tCOMISSÃO: %.2f\tEMAIL: %s%n",
                        id, nome, tel, comissao, email
                );
            }

            if (!encontrou) {
                System.out.println("Nenhum vendedor encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar vendedor: " + e.getMessage());
        }
    }
}