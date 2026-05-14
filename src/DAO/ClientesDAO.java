package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entidades.Clientes;

public class ClientesDAO {

    public static void addCliente(Clientes clientes){
        PreparedStatement ps = null;
        String sql = "INSERT INTO clientes (nome_clientes, CPF) VALUES(?,?)";

        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, clientes.getNome_clientes());
            ps.setString(3,clientes.getCpf());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void mostrarClient(Clientes clientes){
        Statement sts = null;
        ResultSet res = null;
        String sql = "Select * from clientes";
        try {
            sts = conexao.Conexao.getConexao().createStatement();
            res = sts.executeQuery(sql);

            while (res.next()){
                int id_clientes = res.getInt("id_clientes");
                String nome_clientes = res.getString("nome_clientes");
                String cpf = res.getString("cpf");

                System.out.printf("ID: %5D\t NOME: %-25s\t CPF: %-25s", id_clientes, nome_clientes, cpf);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            
    }
}
