package DAO;

import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClasseDao {
    public static void mostrar(){
        Statement st = null;
        ResultSet rs = null;
        int cont = 0;
        String sql = "Select * from classe";
        try {
            st = conexao.Conexao.getConexao().createStatement();
            rs = st.executeQuery(sql);
            System.out.println("\t\t\t\t Classes Cadastradas No Sistema");
            while (rs.next()){
                int id  = rs.getInt("idClasse");
                String nome = rs.getString("nome_classe");
                System.out.printf("CODIGO: %3d | %-20s \t" , id, nome);
                cont++;
                if(cont % 3 == 0){
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean vereficarId(int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from classe where idClasse = ?";
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1 , id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
