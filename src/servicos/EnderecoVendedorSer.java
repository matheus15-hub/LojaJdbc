package servicos;

import DAO.EnderecoDAO;
import DAO.EnderecoVendedorDAO;
import DAO.VendedorDAO;
import entidades.Endereco;
import entidades.Vendedor;

public class EnderecoVendedorSer {
    public void vincular(Vendedor vendedor, Endereco endereco){
        int idEndereco = new EnderecoDAO().addEndereco(endereco);
        endereco.setId_endereco(idEndereco);
        int idVendedor  = new VendedorDAO().addVendedor(vendedor);
        vendedor.setIdVendedor(idVendedor);
        boolean vereficar =  new EnderecoVendedorDAO().vincularEnderecoVendedor(vendedor , endereco);

        vereficarVinculacao(vereficar, vendedor , endereco);


    }
    public void vereficarVinculacao(Boolean b, Vendedor v , Endereco e){
        if(b){
            System.out.println("Vendedor cadastrado com sucesso!");
        }
        else{
            new VendedorDAO().excluirVendedor(v);
            new EnderecoDAO().excluirEndereco(e);
        }
    }
}
