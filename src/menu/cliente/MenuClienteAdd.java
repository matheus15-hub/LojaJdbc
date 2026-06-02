package menu.cliente;

import java.util.Scanner;

import entidades.Clientes;
import entidades.Endereco;
import menu.endereco.MenuEnderecoAdd;
import servicos.ClienteSer;
import servicos.EnderecoClienteSer;
import servicos.EnderecoSer;

public class MenuClienteAdd {
    Scanner sca = new Scanner(System.in);
     public void Clienteadd() {
        ClienteSer clienteServico = new ClienteSer();

        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();
        nome = clienteServico.verificarNome(nome);

        System.out.print("CPF (sem formatação exemplo: 11122233344): ");
        String cpf = sca.nextLine();
        cpf = clienteServico.verificarCPF_clientes(cpf);

        System.out.print("Email: ");
        String email_clientes = sca.nextLine();
        email_clientes = clienteServico.vereficarEmail_clientes(email_clientes);

        Clientes c = new Clientes( nome, cpf, email_clientes);
        System.out.println("Dejesa: 1) Criar um novo endereço  | 2 ) Selecionar um endereço existente");
        System.out.print("Escolha: ");
        int escolhaVinculacaoEndereco = sca.nextInt();
        if(escolhaVinculacaoEndereco == 1) {
            Endereco e = new MenuEnderecoAdd().addEndereco();
            new EnderecoClienteSer().addEnderecoCliente(c, e);
        } else if (escolhaVinculacaoEndereco == 2) {
            int idEndereco =  0;
            idEndereco = new EnderecoSer().escolherEndereco(idEndereco);
            new EnderecoClienteSer().vincularClienteEndereco(c , idEndereco);
        }
    }
}
