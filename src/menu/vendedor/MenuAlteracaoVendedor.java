package menu.vendedor;

import entidades.Vendedor;
import menu.endereco.MenuAlterarEndereco;
import menu.endereco.MenuCadastroEndereco;
import servicos.EnderecoService;
import servicos.EnderecoVendedorService;
import servicos.VendedorSer;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuAlteracaoVendedor {

    Scanner sca = new Scanner(System.in);

    public void menuAlterarVendedor() {

        while (true) {

            System.out.println("\n========================== ALTERAÇÃO DE VENDEDORES ================================");
            System.out.println(" 1) Alterar Nome  |  2) Alterar Telefone  |  3) Alterar Email ");
            System.out.println(" 4) Alterar Salário | 5) Alterar Endereço | 6) Adicionar Endereço ");
            System.out.println("                           0) Voltar                              ");
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
                    nomeVendedor();
                    break;

                case 2:
                    telefoneVendedor();
                    break;

                case 3:
                    emailVendedor();
                    break;

                case 4:
                    salarioVendedor();
                    break;

                case 5:
                    int id = selecionarVendedor();
                    new MenuAlterarEndereco().menuAlterarEnderecoVendedor(id);
                    break;

                case 6:
                    adicionarEnderecoVendedor();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public int selecionarVendedor() {

        while (true) {

            new MenuConsultaVendedor().metodoBusca();

            System.out.println("======================================================");
            System.out.println("Escolha o ID correspondente do vendedor:");
            System.out.print("ID: ");

            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O ID deve conter apenas números inteiros.");
                sca.nextLine();
                System.out.print("ID: ");
            }

            int id = sca.nextInt();

            id = new VendedorSer().verificarId(id);

            System.out.println("Esse é o vendedor que deseja alterar? SIM = s , NÃO = n");
            System.out.print("Resposta: ");

            sca.nextLine();
            String resposta = sca.nextLine();

            if (resposta.equalsIgnoreCase("s")) {
                return id;
            }
        }
    }

    public void nomeVendedor() {

        System.out.println("Alteração de Nome de Vendedor");

        int idVendedor = selecionarVendedor();

        System.out.print("Novo Nome: ");

        String nome = sca.nextLine();

        nome = new VendedorSer().verificarNome(nome);

        new VendedorSer().alterarNome(idVendedor, nome);
    }

    public void telefoneVendedor() {

        System.out.println("Alteração de Telefone de Vendedor");

        int idVendedor = selecionarVendedor();

        System.out.print("Novo Telefone: ");

        String telefone = sca.nextLine();

        telefone = new VendedorSer().verificarTelefone(telefone);

        new VendedorSer().alterarTelefone(idVendedor, telefone);
    }

    public void emailVendedor() {

        System.out.println("Alteração de Email de Vendedor");

        int idVendedor = selecionarVendedor();

        System.out.print("Novo Email: ");

        String email = sca.nextLine();

        email = new VendedorSer().verificarEmail(email);

        new VendedorSer().alterarEmail(idVendedor, email);
    }

    public void salarioVendedor() {

        System.out.println("Alteração de Salário de Vendedor");

        int idVendedor = selecionarVendedor();

        System.out.print("Novo Salário: ");

        BigDecimal salario =
                new BigDecimal(sca.nextLine().replace(",", "."));

        new VendedorSer().alterarSalario(idVendedor, salario);
    }

    public void adicionarEnderecoVendedor() {

        Vendedor vendedor = new Vendedor();

        int idVendedor = selecionarVendedor();

        vendedor.setIdVendedor(idVendedor);

        System.out.println("Deseja:");
        System.out.println("1) Criar um novo endereço");
        System.out.println("2) Vincular um endereço existente");
        System.out.print("Escolha: ");

        int opcao = sca.nextInt();
        sca.nextLine();

        switch (opcao) {

            case 1:

                var endereco = new MenuCadastroEndereco().addEndereco();

                new EnderecoVendedorService().maisEnderecoVendedor(idVendedor, endereco);

                System.out.println("Endereço cadastrado e vinculado com sucesso.");
                break;

            case 2:

                int idEndereco =
                        new EnderecoService().escolherEndereco(0);

                new EnderecoVendedorService().maisvincularVendedorEndereco(vendedor, idEndereco);

                System.out.println("Endereço vinculado com sucesso.");
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }
}