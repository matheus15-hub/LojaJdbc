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

    public void addVendedor(Vendedor vendedor) {

    String sql ="insert into vendedor(nome_vendedor, telefone_vendedor, email_vendedor, salario) values (?, ?, ?, ?)";

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, vendedor.getNomeVendedor());
            stmt.setString(2, vendedor.getTelefoneVendedor());
            stmt.setString(3, vendedor.getEmailVendedor());
            stmt.setBigDecimal(4, vendedor.getSalario());
            

            // vendedor vai iniciar zerado
            stmt.setDouble(5, 0);
            stmt.executeUpdate();

            System.out.println("Vendedor adicionado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao adicionar vendedor: " + e.getMessage());
        }
    }

public void addComissao(int idVendedor,
                         double valorPedido,
                         String statusPedido) {

    try {

        // valida status
        if (!statusPedido.equalsIgnoreCase("CONCLUIDO")) {

            System.out.println(
                "Pedido não concluído. Comissão não adicionada."
            );

            return;
        }

        double valorComissao = valorPedido * 0.01;

        String sql = """
            update vendedor
            set comissao = comissao + ?
            where id_vendedor = ?
            """;

        conn = Conexao.getConexao();

        stmt = conn.prepareStatement(sql);

        stmt.setDouble(1, valorComissao);
        stmt.setInt(2, idVendedor);

        stmt.executeUpdate();

        System.out.println(
            "Comissão adicionada com sucesso!"
        );

    } catch (Exception e) {

        System.out.println(
            "Erro ao adicionar comissão: "
            + e.getMessage()
        );
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
        String sql = "SELECT * FROM vendedor WHERE id_vendedor=?";
    try{

        conn =Conexao.getConexao();
        stmt =conn.prepareStatement(sql);
        stmt.setInt(1,idBusca);
        rs =stmt.executeQuery();
        if(rs.next()){
                int id = rs.getInt("id_vendedor");
                String nome = rs.getString("nome_vendedor");
                String telefone = rs.getString("telefone_vendedor");
                String email = rs.getString("email_vendedor");
                double salario = rs.getDouble("salario");
                float comissao = rs.getFloat("comissao");

}   
            rs.close();
            stmt.close();
            conn.close();
        
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