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

    String sql ="insert into vendedor(nome_vendedor, telefone_vendedor, email_vendedor, comissao) values (?, ?, ?, ?)";

    try {

        conn =Conexao.getConexao();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,vendedor.getNomeVendedor());
        stmt.setString(2, vendedor.getTelefoneVendedor());
        stmt.setString(3,vendedor.getEmailVendedor());
        stmt.setDouble(4,vendedor.getComissao());
            stmt.executeUpdate();

        System.out.println("Vendedor adicionado!");
            stmt.close();
            conn.close();
    }
    catch(java.sql.SQLIntegrityConstraintViolationException e){
        System.out.println("Email já cadastrado!");
    }
    catch(Exception e){System.out.println(
        e.getMessage());
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
                float comissao = rs.getFloat("comissao");

        System.out.printf("ID: %5d\tNOME: %-20s\tTELEFONE: %-11s\tCOMISSÃO: %.2f%%\tEMAIL: %s%n",id, nome, tel, comissao, email);
                linha();
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
    public Vendedor buscarPorId(int idBusca){
        String sql ="SELECT * FROM vendedor WHERE id_vendedor=?";
    try{

        conn =Conexao.getConexao();
        stmt =conn.prepareStatement(sql);
        stmt.setInt(1,idBusca);
        rs =stmt.executeQuery();
        if(rs.next()){

            Vendedor v =new Vendedor();
            v.setIdVendedor(rs.getInt("id_vendedor"));
            v.setNomeVendedor(rs.getString("nome_vendedor"));
            v.setTelefoneVendedor(rs.getString("telefone_vendedor"));
            v.setEmailVendedor(rs.getString("email_vendedor"));
            v.setComissao(rs.getDouble("comissao"));

            rs.close();
            stmt.close();
            conn.close();

            return v;
        }
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    return null;
}
    public static boolean verificarExistencia(int h){
    PreparedStatement ps =null;
    ResultSet resultSet =null;
    String sql ="SELECT COUNT(*) FROM vendedor WHERE id_vendedor=?";

    try{
        ps =Conexao.getConexao().prepareStatement(sql);
        ps.setInt(1,h);

        resultSet =ps.executeQuery();

        if(resultSet.next()){
            boolean existe =resultSet.getInt(1) > 0;
            resultSet.close();
            ps.close();
            return existe;

        }

    }

    catch(Exception e){

        System.out.println(e.getMessage());
    }
    return false;

}
    public static void linha(){
        System.out.println("========================================================================================================================================================================================");
    }
}