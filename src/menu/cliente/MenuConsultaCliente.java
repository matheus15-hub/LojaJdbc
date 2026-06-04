package menu.cliente;

import servicos.ClienteService;

import java.util.Scanner;

public class MenuConsultaCliente {

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
        }
    }

    public void printCliente() {
        new ClienteService().mostrar();
    }

    public void printClienteFiltro() {
        System.out.print("Nome do Cliente: ");
        String nome = sca.nextLine();

        nome = new ClienteService().verificarNome(nome);

        new ClienteService().mostrarFiltro(nome);
    }
}