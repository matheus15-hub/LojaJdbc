package menu;

import servicos.Produtoser;

import java.util.Scanner;

public class Menualterar {
    Scanner sca = new Scanner(System.in);
    //Produto.
    public void nomeProduto(){
        int id;
        while (true) {
            System.out.println("Alteração de Nome de Produto");
            new Menuprint().metodoBusca();
            System.out.println("======================================================\n");
            System.out.println("Escolha o ID Correspondende que deseja alterar o nome:");
            System.out.print("ID: ");
            while (!sca.hasNextInt()) {
                System.out.println("Entrada inválida! O id deve conter apenas números inteiros. Ex: 1, 5, 20...");
                sca.nextLine();
                System.out.print("ID:");
            }
            id = sca.nextInt();
            id = new Produtoser().verificarId(id);
            new Produtoser().mostrarId(id);
            System.out.println("Esse é o produto que deseja alterar? SIM = s , Não = n");
            System.out.print("Resposta: ");
            sca.nextLine();
            String certezadoProduto = sca.nextLine();
            if (certezadoProduto.equalsIgnoreCase("s")){
                break;
            }
        }
        System.out.print("Novo Nome: ");
        String nome = sca.nextLine();
        nome = new Produtoser().verificarNome(nome);
        new Produtoser().alterarNome(id, nome);
    }



    //Cliente.


    //Vendedor.
}
