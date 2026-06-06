package menu.vendedor;
import java.util.Scanner;

import servicos.VendedorSer;

public class MenuRemocaoVendedor {

    Scanner sca = new Scanner(System.in);
    public void vendedorRemover() {

        System.out.println("\t\tDeletando VENDEDORES");
        System.out.println("Buscar Vendedores 1) Sim 2) Não");
        System.out.print("Escolha: ");

        int resposta = sca.nextInt();

        if (resposta == 1) {
            sca.nextLine();
            new MenuConsultaVendedor().metodoBusca();
        }

        System.out.println("SELECIONE O ID DO VENDEDOR QUE DESEJA EXCLUIR");
        System.out.print("ID: ");

        while (!sca.hasNextInt()) {
            System.out.println("Código inválido! Digite apenas números inteiros correspondentes aos vendedores cadastrados.");
            sca.nextLine();
            System.out.print("Digite um Código Cadastrado: ");
        }

        int codigo_vendedor = sca.nextInt();
        codigo_vendedor = new VendedorSer().verificarId(codigo_vendedor);

        new VendedorSer().remover(codigo_vendedor);
    }
    
}
