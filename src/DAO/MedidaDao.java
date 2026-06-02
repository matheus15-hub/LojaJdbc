package DAO;

import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedidaDAO {
    public static void mostrar(){
        Statement st = null;
        ResultSet rs = null;
        int cont = 0;
        String sql = "Select * from unidade_medida";
        try {
            st = conexao.Conexao.getConexao().createStatement();
            rs = st.executeQuery(sql);
            System.out.println("\t\t\t\t Unidades De Medida Cadastradas");
            while (rs.next()){
                int id  = rs.getInt("idUnidade");
                String nome = rs.getString("nome_medida");
                System.out.printf("CODIGO: %3d | %-15s \t" , id, nome);
                cont++;
                if(cont % 4 == 0){
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean vereficarid(int id){ 
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * from unidade_medida where idUnidade = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarMedida(int id, int medida){
    PreparedStatement ps = null;
    String sql = "UPDATE produtos SET idclasse = ? WHERE id_produtos = ?";
    try {
        ps = Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1, medida);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}
