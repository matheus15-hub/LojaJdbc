package menu.cliente;

import java.util.Scanner;

import entidades.Cliente;
import entidades.Endereco;
import menu.endereco.MenuCadastroEndereco;
import servicos.ClienteService;
import servicos.EnderecoClienteSer;
import servicos.EnderecoService;
import util.Console;

public class MenuCadastroCliente {
    Scanner sca = new Scanner(System.in);
     public void Clienteadd() {
        ClienteService clienteServico = new ClienteService();

        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();
        nome = clienteServico.verificarNome(nome);

        System.out.print("CPF (sem formatação exemplo: 11122233344): ");
        String cpf = sca.nextLine();
        cpf = clienteServico.verificarCPF_clientes(cpf);

        System.out.print("Email: ");
        String email_clientes = sca.nextLine();
        email_clientes = clienteServico.verificarEmail_clientes(email_clientes);

        Cliente c = new Cliente( nome, cpf, email_clientes);
        try {
        System.out.println("Dejesa: 1) Criar um novo endereço  | 2 ) Selecionar um endereço existente");
        System.out.print("Escolha: ");
        int escolhaVinculacaoEndereco = Integer.parseInt(sca.nextLine());
        if(escolhaVinculacaoEndereco == 1) {
            Endereco e = new MenuCadastroEndereco().addEndereco();
            new EnderecoClienteSer().addEnderecoCliente(c, e);
        } else if (escolhaVinculacaoEndereco == 2) {
            int idEndereco =  0;
            idEndereco = new EnderecoService().escolherEndereco(idEndereco);
            new EnderecoClienteSer().vincularClienteEndereco(c , idEndereco);
        }
        }catch (NumberFormatException e){
            Console.linha();
            System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
            System.out.print("\t\t\t\t\tTENTE NOVAMENTE");
            Console.linha();
        }
    }
}
