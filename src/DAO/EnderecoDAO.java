package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexao.Conexao;
import entidades.Endereco;
import util.Console;


public class EnderecoDAO {
    Connection conn = Conexao.getConexao();;
    PreparedStatement stmt;
    ResultSet rsEndereco;

    public int addEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco(rua, numero, bairro, cidade, cep) VALUES (?, ?, ?, ? , ?)";
        try {

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getCep());
            stmt.executeUpdate();
            rsEndereco = stmt.getGeneratedKeys();
            int id_Endereco = 0;
            if (rsEndereco.next()) {
                id_Endereco = rsEndereco.getInt(1);
            }
            stmt.close();
            rsEndereco.close();
            conn.close();
            return id_Endereco;
        } catch (Exception e) {
            System.out.println("Erro ao criar endereço: " + e.getMessage());
            return -1;
        }
    }
    public void excluirEndereco(Endereco e){
        String sql = "delete from endereco where id_endereco";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getId_endereco());
            stmt.execute();
            stmt.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void mostrarEnderecos(){
        Statement st;
        String sql = "select * from endereco";
        try {
            st = conn.createStatement();
            rsEndereco = st.executeQuery(sql);
            while(rsEndereco.next()){
                int id = rsEndereco.getInt("id_endereco");
                String rua = rsEndereco.getString("rua");
                String numero = rsEndereco.getString("numero");
                String bairro = rsEndereco.getString("bairro");
                String cidade = rsEndereco.getString("cidade");
                String cep = rsEndereco.getString("cep");
                Console.linha();
                System.out.printf("|| ID: %5d\t RUA: %-35s\tNUMERO: %-8s\tBAIRRRO: %-20s\tCIDADE: %-20s\t CEP: %s || ",
                        id , rua , numero , bairro, cidade, cep );
                Console.linha();
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filtrarEnderecosRua(String s){

        String sql = "select * from endereco where rua like ?";
        try {
           stmt= conn.prepareStatement(sql);
           stmt.setString(1, s +"%");
            rsEndereco = stmt.executeQuery();
            while(rsEndereco.next()){
                int id = rsEndereco.getInt("id_endereco");
                String rua = rsEndereco.getString("rua");
                String numero = rsEndereco.getString("numero");
                String bairro = rsEndereco.getString("bairro");
                String cidade = rsEndereco.getString("cidade");
                String cep = rsEndereco.getString("cep");
                Console.linhaEndereco();
                System.out.printf("|| ID: %5d\t RUA: %-35s\tNUMERO: %-8s\tBAIRRRO: %-20s\tCIDADE: %-20s\t CEP: %s || ",
                        id , rua , numero , bairro, cidade, cep );
                Console.linhaEndereco();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void filtrarEnderecosCep(String s){
        String sql = "select * from endereco where cep = ?";
        try {
            stmt= conn.prepareStatement(sql);
            stmt.setString(1, s );
            rsEndereco = stmt.executeQuery();
            while(rsEndereco.next()){
                int id = rsEndereco.getInt("id_endereco");
                String rua = rsEndereco.getString("rua");
                String numero = rsEndereco.getString("numero");
                String bairro = rsEndereco.getString("bairro");
                String cidade = rsEndereco.getString("cidade");
                String cep = rsEndereco.getString("cep");
                Console.linhaEndereco();
                System.out.printf("|| ID: %5d\t RUA: %-35s\tNUMERO: %-8s\tBAIRRRO: %-20s\tCIDADE: %-20s\t CEP: %s || ",
                        id , rua , numero , bairro, cidade, cep );
                Console.linhaEndereco();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean verificarId(int id){;
        String sql = "select * from endereco where id_endereco = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rsEndereco = stmt.executeQuery();
            return rsEndereco.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarRua(int id, String rua){
        PreparedStatement ps = null;
        String sql = "UPDATE endereco SET rua = ? WHERE id_endereco = ?";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, rua);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarNumero(int id, String numero){
        PreparedStatement ps = null;
        String sql = "UPDATE endereco SET numero = ? WHERE id_endereco = ?";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, numero);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarBairro(int id, String bairro){
        PreparedStatement ps = null;
        String sql = "UPDATE endereco SET bairro = ? WHERE id_endereco = ?";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, bairro);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarCidade(int id, String cidade){
        PreparedStatement ps = null;
        String sql = "UPDATE endereco SET cidade = ? WHERE id_endereco = ?";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cidade);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void alterarCep(int id, String cep){
        PreparedStatement ps = null;
        String sql = "UPDATE endereco SET cep = ? WHERE id_endereco = ?";

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cep);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}