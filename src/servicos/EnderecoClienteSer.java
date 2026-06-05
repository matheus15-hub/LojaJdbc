package servicos;

import DAO.ClienteDAO;
import DAO.EnderecoClienteDAO;
import DAO.EnderecoDAO;
import entidades.Cliente;
import entidades.Endereco;

import java.util.Scanner;

public class EnderecoClienteSer {
    private EnderecoClienteDAO enderecoClienteDAO = new EnderecoClienteDAO();
        Scanner sca = new Scanner(System.in);
       public void addEnderecoCliente(Cliente clientes , Endereco endereco){
           int idEndereco = new EnderecoDAO().addEndereco(endereco);
           endereco.setId_endereco(idEndereco);
           int idCliente = new ClienteDAO().addCliente(clientes);
           clientes.setId_clientes(idCliente);
           System.out.println("ID ENDERECO: " + endereco.getId_endereco());
           System.out.println("ID CLIENTE: " + clientes.getId_clientes());
           new EnderecoClienteDAO().novoClienteEndereco(clientes, endereco);
       }
    public void maisEnderecoCliente(int idClientes , Endereco endereco){
        Cliente clientes = new Cliente();
        int idEndereco = new EnderecoDAO().addEndereco(endereco);
        endereco.setId_endereco(idEndereco);
        clientes.setId_clientes(idClientes);
        System.out.println("ID ENDERECO: " + endereco.getId_endereco());
        System.out.println("ID CLIENTE: " + clientes.getId_clientes());
        new EnderecoClienteDAO().novoClienteEndereco(clientes, endereco);
    }
    public void maisvincularClienteEndereco(Cliente clientes, int id) {
    new EnderecoClienteDAO().vincularEnderecoCliente(clientes, id);
}

public void vincularClienteEndereco(Cliente clientes, int id) {
    int idCliente = new ClienteDAO().addCliente(clientes);
    clientes.setId_clientes(idCliente);
    new EnderecoClienteDAO().vincularEnderecoCliente(clientes, id);
}

public void mostrarEnderecoCliente(int id) {
    new EnderecoClienteDAO().mostrarEnderecoCliente(id);
}

public int verificarLigacao(int idclientes, int idendereco) {
    while (!enderecoClienteDAO.verificarEnderecoCliente(idclientes, idendereco)) {
        System.out.println("\n=====================================================================");
        System.out.println("||\t\t\t\t ID DO ENDEREÇO INVALIDO \t\t\t\t||");
        System.out.println("||\t\t\t ESCOLHA UM ENDEREÇO VINCULADO COM O CLIENTE \t\t\t||");
        System.out.println("=====================================================================\n");
        mostrarEnderecoCliente(idclientes);
        System.out.println("Selecione o ID do endereço que deseja alterar:");
        System.out.print("Escolha: ");
        idendereco = sca.nextInt();
        sca.nextLine();
    }
    return idendereco;
}
       public  boolean maisvincularClienteEndereco(int idclientes , int idendereco){
           return  enderecoClienteDAO.verificarEnderecoCliente(idclientes ,idendereco);
       }
    public int escolherEnderecoCliente(int idcliente , int idendereco){
           int id = new EnderecoClienteDAO().getIdEnderecoCliente(idcliente, idendereco);
           return id;
    }
}
