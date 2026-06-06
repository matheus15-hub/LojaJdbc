package menu.vendedor;

import java.util.Scanner;

import servicos.ClienteService;
import servicos.VendedorSer;
import util.Console;

public class MenuConsultaVendedor {
    
    Scanner sca = new Scanner(System.in);

    public void metodoBusca() {
        while (true) {
            Console.linha();
            System.out.println("||\t\t\t! Metodo de Busca !");
            System.out.println("|| Buscar:");
            System.out.println("|| 1) Por Nome de Cliente");
            System.out.println("|| 2) Por Todos os clientes cadastrados");
            Console.linhaSimples();
            System.out.print("||Escolha: ");

            while (!sca.hasNextInt()) {
                Console.linha();
                System.out.println("Digite apenas números, 1 ou 2!");
                sca.nextLine();
                System.out.print("Escolha: ");
            }

            int busca = sca.nextInt();
            sca.nextLine();
            if (busca == 2) {
                printVendedor();
                break;
            } else if (busca == 1) {
                printVendedorFiltro();
                break;
            } else {
                System.out.println("=========================== Escolha inválida! ===========================");
                System.out.println("=========================== Tente novamente ===========================");
            }
        }
    }

    public void printVendedor() {
        Console.linha();
        new VendedorSer().mostrar();
    }

    public void printVendedorFiltro() {
        Console.linha();
        System.out.print("|| Nome do Vendedor: ");
        String nome = sca.nextLine();
        nome = new VendedorSer().verificarNome(nome);
        new VendedorSer().mostrarFiltro(nome);
    }
}
