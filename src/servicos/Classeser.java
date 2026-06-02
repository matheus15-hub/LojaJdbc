package servicos;

import DAO.ClasseDao;

import java.util.Scanner;

public class ClasseSer {
    Scanner sca = new Scanner(System.in);
    public static void mostrar(){
        ClasseDao.mostrar();
    }
    public int vereficarid(int id){
        while (true){
            if (!ClasseDao.vereficarId(id)) {

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
