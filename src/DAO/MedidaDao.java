package DAO;

import java.sql.ResultSet;
import java.sql.Statement;

public class MedidaDao {
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
}
