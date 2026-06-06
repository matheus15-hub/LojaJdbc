package menu.cliente;

import java.util.Scanner;

import servicos.ClienteService;
import util.Console;

public class MenuRemocaoCliente {

    Scanner sca = new Scanner(System.in);

    public void clienteRemover() {

        try {
            Console.linha();
        System.out.println("\t\tDeletando CLIENTES");
            Console.linha();

            new MenuConsultaCliente().metodoBusca();

        System.out.println("SELECIONE O ID DO CLIENTE QUE DESEJA EXCLUIR");
        System.out.print("ID: ");
        int codigo_cliente = Integer.parseInt(sca.nextLine());
        codigo_cliente = new ClienteService().verificarId(codigo_cliente);

        new ClienteService().removerCliente(codigo_cliente);
        } catch (NumberFormatException e) {
            Console.linha();
            System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
            System.out.println("\t\t\t\t\tTENTE NOVAMENTE");
            Console.linha();
        }
    }
}
