package servicos;

import java.util.Scanner;

import DAO.ClientesDAO;
import entidades.Clientes;

public class Clienteser {
    Scanner sca = new Scanner(System.in);

    public void adicionarCli(Clientes clientes){
        ClientesDAO.addCliente(clientes);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void mostrar(){
        new ClientesDAO().mostrarClient();
    }
    public void mostrarFiltro(String c){
        new ClientesDAO().mostrarClientFiltro(c);
    }

    public String verificarNome(String nome_cliente){
        while (true) {

            if (nome_cliente == null || nome_cliente.trim().isEmpty()) {

                System.out.println("Nome nao pode ser vazio!");
                System.out.print("Nome: ");
                nome_cliente = sca.nextLine();

            } else if (nome_cliente.length() > 100) {

                System.out.println("Nome nao pode ter mais de 100 caracteres!");
                System.out.print("Nome: ");
                nome_cliente = sca.nextLine();

            } else {
                return nome_cliente;
            }
        }
    }

    public String verificarCPF(String cpf){
        while (true) {

            if (cpf == null || cpf.trim().isEmpty()) {

                System.out.println("O CPF nao pode ser vazio!");
                System.out.print("PCF: ");
                cpf = sca.nextLine();

            } else if (cpf.length() > 14) {

                System.out.println("CPF nao pode ter mais de 14 caracteres!");
                System.out.print("CPF: ");
                cpf = sca.nextLine();

            } else {
                return cpf;
            }
        }
    }

}
