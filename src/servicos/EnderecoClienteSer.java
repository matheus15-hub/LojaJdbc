package servicos;

import DAO.ClienteDAO;
import DAO.EnderecoClienteDAO;
import DAO.EnderecoDAO;
import entidades.Cliente;
import entidades.Endereco;

public class EnderecoClienteSer {

       public void addEnderecoCliente(Cliente clientes , Endereco endereco){
           int idEndereco = new EnderecoDAO().addEndereco(endereco);
           endereco.setId_endereco(idEndereco);
           int idCliente = new ClienteDAO().addCliente(clientes);
           clientes.setId_clientes(idCliente);
           System.out.println("ID ENDERECO: " + endereco.getId_endereco());
           System.out.println("ID CLIENTE: " + clientes.getId_clientes());
           new EnderecoClienteDAO().novoClienteEndereco(clientes, endereco);
       }
       public void vincularClienteEndereco(Cliente clientes, int id){
           int idCliente = new ClienteDAO().addCliente(clientes);
           clientes.setId_clientes(idCliente);
           new EnderecoClienteDAO().VincularEnderecoCliente(clientes , id);
       }
}
