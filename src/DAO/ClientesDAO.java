package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexao.Conexao;
import entidades.Clientes;

public class ClientesDAO {

    public static boolean addCliente(Clientes clientes){
        PreparedStatement ps = null;
        String sql = "INSERT INTO clientes (nome_clientes, CPF, email_clientes, bairroClientes, rua_clientes) VALUES(?,?,?,?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, clientes.getNome_clientes());
            ps.setString(2,clientes.getCpf()); // antes estava 3, foi alterado para 2(Por que ele poderia colocar o CPF numa coluna que não existe no comando MYSQL.)
            ps.setString(3, clientes.getemail_clientes());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace(); // esse negocio aqui vai dizer mesmo se o banco vai querer o dado mandado.
        }  

        return true;
    }

    public static void removerCliente(int id_clientes){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "DELETE FROM clientes WHERE id_clientes = ?";
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id_clientes);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o cliente: " + e.getMessage());
        }
    }


    public void mostrarClient() {
        String sql = "SELECT * FROM clientes";
        
        try (java.sql.Connection conn = conexao.Conexao.getConexao();
             java.sql.Statement sts = conn.createStatement();
             java.sql.ResultSet res = sts.executeQuery(sql)) {

            while (res.next()) {
                int id_clientes = res.getInt(1);
                String nome_clientes = res.getString(2);
                String cpf = res.getString(3);
                String email_clientes = res.getString(4);
                
                linha();
                System.out.printf("||ID: %5d | NOME: %-20s | CPF: %-15s | EMAIL: %-20s | END: %-20s | RUA: %-20%||%n", 
                        id_clientes, nome_clientes, cpf, email_clientes);
                linha();
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }
    }
    public void mostrarClientFiltro(String nome_clientes){
        PreparedStatement md = null;
        ResultSet resultSet = null;
        String sql = "Select * from clientes where nome_clientes like ?";
        try {
            md = conexao.Conexao.getConexao().prepareStatement(sql);
            md.setString(1, nome_clientes + "%");
            resultSet = md.executeQuery();
            while (resultSet.next()){
                int id_clientes = resultSet.getInt("id_clientes");
                nome_clientes = resultSet.getNString("nome_clientes");
                String cpf = resultSet.getNString("cpf");
                String email_clientes = resultSet.getString("email_clientes");
                linha();
                System.out.printf("||ID: %5d\\t NOME: %-25s\\t CPF: %-18s \\t Bairro: %-25s\\ Rua: %-25||%n", id_clientes, nome_clientes, cpf, email_clientes);
                linha();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage());
        }    
    }
    public static boolean vereficarExistencia(int h){
        int contador = 0;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sql = "Select * from clientes where id_clientes = ?";
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, h);
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                contador++;
            }
            if (contador <= 0){return true;}
            else {return false;}


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void linha(){
        System.out.println("==================================================================================");
    }

    public void AlterarNomeClien(int id,String n){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET nome_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,n);
            ps.setInt(2,id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AlterarCPFClien(int id,String n){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET cpf_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,n);
            ps.setInt(2,id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AlterarEmailClien(int id,String n){
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET email_clientes = ? WHERE id_clientes = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,n);
            ps.setInt(2,id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
