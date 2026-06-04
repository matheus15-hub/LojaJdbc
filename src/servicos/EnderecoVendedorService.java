package servicos;

import java.util.Scanner;

import DAO.EnderecoDAO;
import DAO.EnderecoVendedorDAO;
import DAO.VendedorDAO;
import entidades.Endereco;
import entidades.Vendedor;

public class EnderecoVendedorService {
    Scanner sca = new Scanner(System.in);
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
    public void maisEnderecoVendedor(int idVendedor, Endereco endereco) {

    Vendedor vendedor = new Vendedor();

    int idEndereco = new EnderecoDAO().addEndereco(endereco);

    endereco.setId_endereco(idEndereco);

    vendedor.setIdVendedor(idVendedor);

    System.out.println("ID ENDERECO: " + endereco.getId_endereco());
    System.out.println("ID VENDEDOR: " + vendedor.getIdVendedor());

    new EnderecoVendedorDAO().novoEnderecoVendedor(vendedor, endereco);
}public void maisvincularVendedorEndereco(Vendedor vendedor, int idEndereco) {

    new EnderecoVendedorDAO().vincularEnderecoVendedor(vendedor, idEndereco);
}
    public int verificarLigacao(int idVendedor, int idEndereco) {
    while (!EnderecoVendedorDAO.verificarEnderecoVendedor(idVendedor, idEndereco)) {
        System.out.println("\n=====================================================================");
        System.out.println("||\t\t\t\t ID DO ENDEREÇO INVALIDO \t\t\t\t||");
        System.out.println("||\t\t\t ESCOLHA UM ENDEREÇO VINCULADO COM O VENDEDOR \t\t\t||");
        System.out.println("=====================================================================\n");

        EnderecoVendedorDAO.mostrarEnderecoVendedor(idEndereco);

        System.out.println("Selecione o ID do endereço que deseja alterar:");
        System.out.print("Escolha: ");

        idEndereco = sca.nextInt();
        sca.nextLine();
    }
    return idEndereco;
}
public boolean maisVincularVendedorEndereco(int idVendedor, int idEndereco) {
    return EnderecoVendedorDAO.verificarEnderecoVendedor(idVendedor, idEndereco);
}
    }
