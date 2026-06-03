package menu.cliente;

import java.util.Scanner;

import servicos.ClienteService;

public class MenuAlteracaoCliente {
    Scanner sca = new Scanner(System.in);
    public void menuAlterarCliente() {

    while (true) {

        System.out.println("\n================ ALTERAÇÃO DE CLIENTES ================");
        System.out.println("1) Alterar Nome | 2) Alterar CPF | 3) Alterar Email | 0) Voltar");
        System.out.print("Escolha: ");

        while (!sca.hasNextInt()) {
            System.out.println("Opção inválida! Digite apenas números.");
            sca.nextLine();
            System.out.print("Escolha: ");
        }

        int opcao = sca.nextInt();
        sca.nextLine();

        switch (opcao) {

            case 1:
                nomeCliente();
                break;

            case 2:
                cpfCliente();
                break;

            case 3:
                emailCliente();
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
        }
    }
}
    private int selecionarCliente() {
        while (true) {
            new MenuRemocaoCliente().metodoBusca();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID correspondente do cliente:");
            System.out.print("ID: ");

            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O ID deve conter apenas números inteiros.");
                sca.nextLine();
                System.out.print("ID: ");
            }

            int id = sca.nextInt();
            id = new ClienteService().vereficarId(id);

            new ClienteService().mostrarId(id);

            System.out.println("Esse é o cliente que deseja alterar? SIM = s , NÃO = n");
            System.out.print("Resposta: ");

            sca.nextLine();
            String resposta = sca.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                return id;
            }
        }
    }

    public void nomeCliente() {
        System.out.println("Alteração de Nome de Cliente");

        int id_cliente = selecionarCliente();

        System.out.print("Novo Nome: ");
        String nome = sca.nextLine();
        nome = new ClienteService().verificarNome(nome);

        new ClienteService().alterarNome(id_cliente, nome);
    }

    public void cpfCliente() {
        System.out.println("Alteração de CPF de Cliente");

        int id_cliente = selecionarCliente();

        System.out.print("Novo CPF (sem formatação): ");
        String cpf = sca.nextLine();
        cpf = new ClienteService().verificarCPF_clientes(cpf);

        new ClienteService().alterarCpf(id_cliente, cpf);
    }

    public void emailCliente() {
        System.out.println("Alteração de Email de Cliente");

        int id_cliente = selecionarCliente();

        System.out.print("Novo Email: ");
        String email = sca.nextLine();
        email = new ClienteService().vereficarEmail_clientes(email);

        new ClienteService().alterarEmail(id_cliente, email);
    }
}

