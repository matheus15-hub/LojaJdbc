package menu.cliente;

import java.util.Scanner;

import servicos.ClienteSer;

public class MenuClienteRemover {

    Scanner sca = new Scanner(System.in);

    public void clienteRemover() {

        System.out.println("\t\tDeletando CLIENTES");
        System.out.println("Buscar Clientes 1) Sim 2) Não");
        System.out.print("Escolha: ");

        int resposta = sca.nextInt();

        if (resposta == 1) {
            sca.nextLine();
            new MenuClientePrint().metodoBusca();
        }

        System.out.println("SELECIONE O ID DO CLIENTE QUE DESEJA EXCLUIR");
        System.out.print("ID: ");

        while (!sca.hasNextInt()) {
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes aos clientes cadastrados.");
            sca.nextLine();
            System.out.print("Digite um Código Cadastrado: ");
        }

        int codigo_cliente = sca.nextInt();
        codigo_cliente = new ClienteSer().vereficarId(codigo_cliente);

        new ClienteSer().removerCli(codigo_cliente);
    }
}