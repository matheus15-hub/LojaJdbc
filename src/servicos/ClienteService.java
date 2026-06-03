package servicos;

import java.util.Scanner;

import DAO.ClienteDAO;


import menu.cliente.MenuRemocaoCliente;

public class ClienteService {

    private Scanner sca = new Scanner(System.in);


    public void removerCli(int id) {
        ClienteDAO.removerCliente(id);
        System.out.println("Cliente removido com sucesso!");
    }

    public void mostrar() {
        new ClienteDAO().mostrarClient();
    }

    public void mostrarId(int id) {
        // RESOLVIDO: Agora chama o método real criado na DAO
        new ClienteDAO().mostrarId(id);
    }

    public void mostrarFiltro(String nome) {
        new ClienteDAO().mostrarClientFiltro(nome);
    }

    public String verificarNome(String nome_cliente) {
        while (true) {
            if (nome_cliente == null || nome_cliente.trim().isEmpty()) {
                System.out.println("Nome não pode ser vazio!");
                System.out.print("Nome: ");
                nome_cliente = sca.nextLine();
            } else if (nome_cliente.length() > 100) {
                System.out.println("Nome não pode ter mais de 100 caracteres!");
                System.out.print("Nome: ");
                nome_cliente = sca.nextLine();
            } else {
                return nome_cliente.trim();
            }
        }
    }

    public String verificarCPF_clientes(String cpf) {
        while (true) {
            if (cpf == null || cpf.trim().isEmpty()) {
                System.out.println("O CPF não pode ser vazio!");
                System.out.print("CPF (Ex: 11199955578): ");
                cpf = sca.nextLine();
                continue;
            }

            cpf = cpf.trim();

            if (cpf.length() != 11) {
                System.out.println("CPF inválido! O formato deve conter 11 caracteres.");
                System.out.print("CPF (Ex: 11199955578): ");
                System.out.print("CPF: ");
                cpf = sca.nextLine();
            } else {
                return cpf;
            }
        }
    }

    public int vereficarId(int id) {
        while (true) {
            if (!ClienteDAO.vereficarExistencia(id)) {
                System.out.println("Cliente com o ID " + id + " não encontrado!");
                new MenuRemocaoCliente().metodoBusca();
                System.out.print("\nDigite um ID válido da lista acima: ");

                while (!sca.hasNextInt()) {
                    System.out.println("Digite apenas números!");
                    sca.nextLine();
                    System.out.print("ID: ");
                }
                id = sca.nextInt();
                sca.nextLine(); 
            } else {
                return id;
            }
        }
    }

    public String vereficarEmail_clientes(String email_clientes) {
        while (true) {
            if (email_clientes == null || email_clientes.trim().isEmpty()) {
                System.out.println("O e-mail não pode ser vazio!");
                System.out.print("EMAIL: ");
                email_clientes = sca.nextLine();
                continue;
            }

            email_clientes = email_clientes.trim();

            if (email_clientes.length() > 150) {
                System.out.println("EMAIL não pode ter mais de 150 caracteres!");
                System.out.print("EMAIL: ");
                email_clientes = sca.nextLine();
                continue;
            }

            if (!email_clientes.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Email inválido! Tente novamente.");
                System.out.print("EMAIL: ");
                email_clientes = sca.nextLine();
            } else {
                return email_clientes;
            }
        }
    }

    public void alterarNome(int id, String nome){
    nome = nome.toUpperCase();
    new ClienteDAO().alterarNome(id, nome);
    mostrarId(id);
    System.out.println("Nome alterado!");
}
    public void alterarCpf(int id, String cpf){
    new ClienteDAO().alterarCpf(id, cpf);
    mostrarId(id);
    System.out.println("CPF alterado!");
}
public void alterarEmail(int id, String email){
    new ClienteDAO().alterarEmail(id, email);
    mostrarId(id);
    System.out.println("Email alterado!");
}
}