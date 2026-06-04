package servicos;

import DAO.ClasseDAO;

import java.util.Scanner;

public class ClasseService {
    Scanner sca = new Scanner(System.in);
    public static void mostrar(){
        ClasseDAO.mostrar();
    }
    public int verificarid(int id){
        while (true){
            if (!ClasseDAO.verificarId(id)) {

                System.out.println("Código " + id + " não cadastrado em nosso banco de dados.");

                mostrar();

                System.out.println("Digite um dos códigos cadastrados acima:");
                System.out.print("CÓDIGO: ");

                while (!sca.hasNextInt()) {

                    sca.nextLine();

                    System.out.println("Apenas números inteiros são permitidos. Ex: 1, 2, 3...");
                    System.out.print("Digite um código cadastrado: ");
                }

                id = sca.nextInt();
                sca.nextLine();

            } else {

                return id;
            }
        }
    }
}
