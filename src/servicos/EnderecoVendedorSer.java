package servicos;

import DAO.EnderecoDAO;
import DAO.EnderecoVendedorDAO;
import DAO.VendedorDAO;
import entidades.Endereco;
import entidades.Vendedor;

public class EnderecoVendedorSer {
    public void addVendedorEndereco(Vendedor vendedor, Endereco endereco){
        int idEndereco = new EnderecoDAO().addEndereco(endereco);
        endereco.setId_endereco(idEndereco);
        int idVendedor  = new VendedorDAO().addVendedor(vendedor);
        vendedor.setIdVendedor(idVendedor);
        new EnderecoVendedorDAO().novoEnderecoVendedor(vendedor , endereco);

    }
    public void vincularVendedorEndero(Vendedor vendedor, int id) {
        int idVendedor = new VendedorDAO().addVendedor(vendedor);
        vendedor.setIdVendedor(idVendedor);
        new EnderecoVendedorDAO().vincularEnderecoVendedor(vendedor, id);

    }
    }
