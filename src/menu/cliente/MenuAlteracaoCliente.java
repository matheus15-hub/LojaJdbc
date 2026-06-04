package menu.cliente;

import java.util.Scanner;

import entidades.Cliente;
import entidades.Endereco;
import menu.endereco.MenuAlterarEndereco;
import menu.endereco.MenuCadastroEndereco;
import servicos.ClienteService;
import servicos.EnderecoClienteSer;
import servicos.EnderecoService;

public class MenuAlteracaoCliente {
    Scanner sca = new Scanner(System.in);
    public void menuAlterarCliente() {

    while (true) {

        System.out.println("\n========================== ALTERAÇÃO DE CLIENTES ================================");
        System.out.println(" 1) Alterar Nome  |  2) Alterar CPF  |  3) Alterar Email  |  4) Alterar Endereco ");
        System.out.println("              5) Adicionar Endereço  |  0) Voltar                                ");
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
            case 4:
                int id = selecionarCliente();
                new MenuAlterarEndereco().menuAlterarEnderecoCliente(id);
                break;
            case 5:
                adicionarEnderecoCliente();
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
            new MenuConsultaCliente().metodoBusca();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID correspondente do cliente:");
            System.out.print("ID: ");

            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O ID deve conter apenas números inteiros.");
                sca.nextLine();
                System.out.print("ID: ");
            }

            int id = sca.nextInt();
            id = new ClienteService().verificarId(id);

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
        email = new ClienteService().verificarEmail_clientes(email);

        new ClienteService().alterarEmail(id_cliente, email);
    }
    public void adicionarEnderecoCliente() {
        Cliente cliente = new Cliente();
        int idCliente = selecionarCliente();
        cliente.setId_clientes(idCliente);

        System.out.println("Deseja:");
        System.out.println("1) Criar um novo endereço");
        System.out.println("2) Vincular um endereço existente");
        System.out.print("Escolha: ");

        int opcao = sca.nextInt();
        sca.nextLine();

        switch (opcao) {

            case 1:

                Endereco endereco = new MenuCadastroEndereco().addEndereco();

                new EnderecoClienteSer().maisEnderecoCliente(idCliente, endereco);

                System.out.println("Endereço cadastrado e vinculado com sucesso.");
                break;

            case 2:

                int idEndereco = new EnderecoService().escolherEndereco(0);

                new EnderecoClienteSer().maisvincularClienteEndereco(cliente, idEndereco);

                System.out.println("Endereço vinculado com sucesso.");
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }
}

