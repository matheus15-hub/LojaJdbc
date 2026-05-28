package menu.MenuAlterar;

import menu.Menuprint;
import servicos.Clienteser;

import java.math.BigDecimal;
import java.util.Scanner;

public class AlterarCliente {
    Scanner sca = new Scanner(System.in);
    int id_clientes;
    String certezadoClientes;

    public void nomeCliente(){

        while (true) {
            System.out.println("Alteração de Nome de Cliente");
            new Menuprint().printCliente();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id_clientes = sca.nextInt();
            id_clientes = new Clienteser().vereficarId_clientes(id_clientes);
            new Clienteser().mostrarId(id_clientes);
            System.out.println("Esse é o cliente que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            certezadoClientes = sca.nextLine();
            if (certezadoClientes.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.print("Novo nome: ");
        String nome_clientes = sca.nextLine();
        nome_clientes = new Clienteser().verificarNome(nome_clientes);
        new Clienteser().alterarNome(id_clientes, nome_clientes);
    }

    public void cpfCliente(){

        while (true) {
            System.out.println("Alteração de Nome de Cliente");
            new Menuprint().printCliente();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id_clientes = sca.nextInt();
            id_clientes = new Clienteser().vereficarId_clientes(id_clientes);
            new Clienteser().mostrarId(id_clientes);
            System.out.println("Esse é o cliente que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            certezadoClientes = sca.nextLine();
            if (certezadoClientes.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.print("Novo cpf: ");
        String cpf = sca.nextLine();
        cpf = new Clienteser().verificarCPF_clientes(cpf);
        new Clienteser().alterarCPF(id_clientes, cpf);
    }

    public void emailCliente(){

        while (true) {
            System.out.println("Alteração de Nome de Cliente");
            new Menuprint().printCliente();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id_clientes = sca.nextInt();
            id_clientes = new Clienteser().vereficarId_clientes(id_clientes);
            new Clienteser().mostrarId(id_clientes);
            System.out.println("Esse é o cliente que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            certezadoClientes = sca.nextLine();
            if (certezadoClientes.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.print("Novo email: ");
        String email_clientes = sca.nextLine();
        email_clientes = new Clienteser().vereficarEmail_clientes(email_clientes);
        new Clienteser().alterarCPF(id_clientes, email_clientes);
    }
}
