package DAO;

import java.sql.ResultSet;
import java.sql.Statement;

public class MedidaDao {
    public void mostrar(){
        Statement st = null;
        ResultSet rs = null;
        String sql = "Select * from unidade_medida";
        try {
            st = conexao.Conexao.getConexao().createStatement();
            rs = st.executeQuery(sql);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
