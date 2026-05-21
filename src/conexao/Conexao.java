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
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, pass);
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("Erro na conexão principal: " + e.getMessage());
            return null;
        }
    }

    public static Connection criarNovaConexao() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Erro ao criar conexão isolada: " + e.getMessage());
            return null;
        }
    }
}