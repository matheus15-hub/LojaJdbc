package servicos;

import DAO.MedidaDAO;

import java.util.Scanner;

public class MedidaSer {
     Scanner sca = new Scanner(System.in);
    public static void mostrar(){
        MedidaDAO.mostrar();
    }
    public int vereficadorId(int id){
        while(true){
        if (!MedidaDAO.vereficarid(id)){
            System.out.println("Codigo " + id+ " não cadastrado em nosso banco de dados.");
            mostrar();
            System.out.println("Digite um dos Codigos Cadastrados Acima:");
            System.out.print("CODIGO: ");
            while (!sca.hasNextInt()){
               sca.nextLine();
               System.out.println("Apenas numeros interiros Ex( 1 , 2 .... 9). Letras, Simbolos ou Numeros decimais não dão certo:");
               System.out.print("Digite um Codigo Cadastrado: ");
            }
            id = sca.nextInt();
        }
        else {return id;}

        }
    }
}
