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
        endereco.setId_endereco(idEndereco);
    }
}
