package menu.vendedor;
import java.util.Scanner;

import servicos.VendedorSer;
import util.Console;

public class MenuRemocaoVendedor {

    Scanner sca = new Scanner(System.in);
    public void vendedorRemover() {
        Console.linha();
        System.out.println("\t\t\tDeletando VENDEDORES");
        Console.linha();
            new MenuConsultaVendedor().metodoBusca();


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
