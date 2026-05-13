package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/sistema_vendas";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection conn = null;
    public static Connection getConexao(){
        try{
            if(conn == null){
                conn = DriverManager.getConnection(url,user,pass);
                return conn;
            }else {return  conn;}

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}
