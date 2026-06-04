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
            System.out.println("||\t\t\t\t\t METODO DE BUSCA\t\t\t\t\t||");
            System.out.println("|| Buscar: 1) Com filtro (caso deseje um cliente específico) \n||         2) Todos os clientes cadastrados");
            System.out.print("||Escolha: ");

            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
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
        Console.linhaprinta();
        new VendedorSer().mostrar();
    }

    public void printVendedorFiltro() {
        Console.linhaprinta();
        System.out.print("|| Nome do Vendedor: ");
        String nome = sca.nextLine();
        nome = new VendedorSer().verificarNome(nome);
        new VendedorSer().mostrarFiltro(nome);
    }
}
