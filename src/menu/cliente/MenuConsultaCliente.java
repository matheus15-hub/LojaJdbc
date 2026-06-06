package menu.cliente;

import servicos.ClienteService;
import util.Console;

import java.util.Scanner;

public class MenuConsultaCliente {

    Scanner sca = new Scanner(System.in);

    public void metodoBusca() {
        while (true) {
            try {
            Console.linha();
            System.out.println("||\t\t\t! Metodo de Busca !");
            System.out.println("|| Buscar:");
            System.out.println("|| 1) Por Nome de Cliente");
            System.out.println("|| 2) Por Todos os clientes cadastrados");
            Console.linhaSimples();
            System.out.print("Escolha: ");

            int busca = Integer.parseInt(sca.nextLine());

            if (busca == 2) {
                printCliente();
                break;
            } else if (busca == 1) {
                printClienteFiltro();
                break;
            } else {
                System.out.println("=========================== Escolha inválida! ===========================");
                System.out.println("=========================== Tente novamente ===========================");
            }
            }catch (NumberFormatException e){
                Console.linha();
                System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
                System.out.print("\t\t\t\t\tTENTE NOVAMENTE");
                Console.linha();
            }
        }
    }

    public void printCliente() {
        new ClienteService().mostrar();
    }

    public void printClienteFiltro() {
        Console.linhaSimples();
        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();

        nome = new ClienteService().verificarNome(nome);

        new ClienteService().mostrarFiltro(nome);
    }
}