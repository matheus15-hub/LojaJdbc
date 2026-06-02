package menu.cliente;

import servicos.ClienteSer;

import java.util.Scanner;

public class MenuClientePrint {

    Scanner sca = new Scanner(System.in);

    public void metodoBusca() {
        while (true) {
            System.out.println("=========================== Metodo de Busca ===========================");
            System.out.println("Buscar: 1) Com filtro (caso deseje um cliente específico) 2) Todos os clientes cadastrados");
            System.out.print("Escolha: ");

            while (!sca.hasNextInt()) {
                System.out.println("Digite apenas números!");
                sca.nextLine();
                System.out.print("Escolha: ");
            }

            int busca = sca.nextInt();
            sca.nextLine();

            if (busca == 1) {
                printCliente();
                break;
            } else if (busca == 2) {
                printClienteFiltro();
                break;
            } else {
                System.out.println("=========================== Escolha inválida! ===========================");
                System.out.println("=========================== Tente novamente ===========================");
            }
        }
    }

    public void printCliente() {
        new ClienteSer().mostrar();
    }

    public void printClienteFiltro() {
        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();

        nome = new ClienteSer().verificarNome(nome);

        new ClienteSer().mostrarFiltro(nome);
    }
}