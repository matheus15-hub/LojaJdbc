package menu.vendedor;

import DAO.EnderecoVendedorDAO;
import entidades.Vendedor;
import menu.endereco.MenuAlterarEndereco;
import menu.endereco.MenuCadastroEndereco;
import servicos.EnderecoService;
import servicos.EnderecoVendedorService;
import servicos.VendedorSer;
import util.Console;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuAlteracaoVendedor {

    Scanner sca = new Scanner(System.in);

    public void menuAlterarVendedor() {

        while (true) {

            Console.linha();
            System.out.println("||            ALTERAÇÃO DE VENDEDORES              ");
            System.out.println(" 1) Alterar Nome");
            System.out.println(" 2) Alterar Telefone");
            System.out.println(" 3) Alterar Email ");
            System.out.println(" 4) Alterar Salário");
            System.out.println(" 5) Alterar Endereço");
            System.out.println(" 6) Adicionar Endereço ");
            System.out.println(" 7) Excluir Endereço");
            System.out.println(" 0) Voltar                              ");
            Console.linhaSimples();
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
                case 7:
                    removerEnderecoVendedor();
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

            Console.linha();
            System.out.println("Escolha o ID correspondente do vendedor:");
            System.out.print("ID: ");

            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O ID deve conter apenas números inteiros.");
                sca.nextLine();
                System.out.print("ID: ");
            }

            int id = sca.nextInt();

            id = new VendedorSer().verificarId(id);

            Console.linhaSimples();
            System.out.println("|| Esse é o vendedor que deseja alterar?");
            System.out.println("|| 1) SIM");
            System.out.println("|| 2) Nao");
            System.out.print("Resposta: ");

            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                System.out.print("Resposta: ");
                sca.nextLine();
            }

            int resposta = sca.nextInt();
            sca.nextLine();

            if (resposta == 1) {
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
    public void removerEnderecoVendedor() {

        Console.linha();
        System.out.println("|| REMOÇÃO DE ENDEREÇO DO VENDEDOR");

        int idVendedor = selecionarVendedor();

        new EnderecoVendedorDAO().mostrarEnderecoVendedor(idVendedor);

        System.out.print("Digite o ID do endereço que deseja remover: ");
        int idEndereco = Integer.parseInt(sca.nextLine());

        System.out.println("\nConfirma a exclusão?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Escolha: ");

        int escolha = Integer.parseInt(sca.nextLine());

        if (escolha == 1) {
            new EnderecoVendedorService()
                    .excluirEnderecoVendedor(idVendedor, idEndereco);
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}