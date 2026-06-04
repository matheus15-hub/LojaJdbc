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
    public void maisvincularClienteEndereco(Cliente clientes, int id){
        new EnderecoClienteDAO().VincularEnderecoCliente(clientes , id);
    }
       public void vincularClienteEndereco(Cliente clientes, int id){
           int idCliente = new ClienteDAO().addCliente(clientes);
           clientes.setId_clientes(idCliente);
           new EnderecoClienteDAO().VincularEnderecoCliente(clientes , id);
       }

       public  void mostrarEnderecosClientes(int id){
           new EnderecoClienteDAO().mostrarEndeClie(id);
       }
       public int vereficarLigacao( int idclientes , int idendereco) {
           while (!vereficarEnderecosCliente(idclientes, idendereco)) {
                   System.out.println("\n=====================================================================");
                   System.out.println("||\t\t\t\t ID DO ENDEREÇO INVALIDO \t\t\t\t||");
                   System.out.println("||\t\t\t ESCOLHA UM ENDEREÇO VINCULADO COM CLINTE \t\t\t||");
                   System.out.println("=====================================================================\n");
                   mostrarEnderecosClientes(idclientes);
                   System.out.println("Selecione o ID do endereço que deseja alterar:");
                   System.out.print("Escolha: ");
                   idendereco = sca.nextInt();
                   sca.nextLine();
           }
                   return idendereco;
       }
       public  boolean vereficarEnderecosCliente(int idclientes , int idendereco){
           return  enderecoClienteDAO.verificarEndeClie(idclientes ,idendereco);
       }
}
