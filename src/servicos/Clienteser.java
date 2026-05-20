package servicos;

import java.util.Scanner;

import DAO.ClientesDAO;
import entidades.Clientes;
import menu.Menuprint;

public class Clienteser {

    Scanner sca = new Scanner(System.in);

    public void adicionarCli(Clientes c){

        ClientesDAO.addCliente(c);

        System.out.println("Cliente cadastrado com sucesso!");
    }


    public void removerCli(int c){
        ClientesDAO.removerCliente(c);
        System.out.println("Cliente removido com sucesso!");
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
                System.out.print("CPF: ");
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

    public int vereficarId(int id){

        while (true){

            if(ClientesDAO.vereficarExistencia(id)){

                System.out.println("Cliente com o ID " + id + " nao encontrado!");

                new Menuprint().printCliente();

                System.out.print("\nID: ");

                while (!sca.hasNextInt()) {

                    System.out.println("Digite apenas números!");
                    sca.nextLine();
                    System.out.print("Digite o ID do cliente escolhido: ");
                }

                id = sca.nextInt();

            } else {

                return id;
            }
        }
    }
}