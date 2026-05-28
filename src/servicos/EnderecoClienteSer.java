package servicos;

import DAO.ClientesDAO;
import DAO.EnderecoClienteDAO;
import DAO.EnderecoDAO;
import entidades.Clientes;
import entidades.Endereco;

public class EnderecoClienteSer {

       public void addEnderecoCliente(Clientes clientes , Endereco endereco){
           int idEndereco = new EnderecoDAO().addEndereco(endereco);
           endereco.setId_endereco(idEndereco);
           int idCliente = new ClientesDAO().addCliente(clientes);
           clientes.setId_clientes(idCliente);
           System.out.println("ID ENDERECO: " + endereco.getId_endereco());
           System.out.println("ID CLIENTE: " + clientes.getId_clientes());
           new EnderecoClienteDAO().VincularEnderecoCliente(clientes, endereco);
       }
}
