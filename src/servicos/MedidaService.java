package servicos;

import DAO.MedidaDAO;
import util.Console;

import java.util.Scanner;

public class MedidaService {
     Scanner sca = new Scanner(System.in);
    public static void mostrar(){
        MedidaDAO.mostrar();
    }
    public int verificadorId(int id){
        while(true){
        if (!MedidaDAO.verificarid(id)){
            try {

            System.out.println("Codigo " + id+ " não cadastrado em nosso banco de dados.");
            mostrar();
            System.out.println("Digite um dos Codigos Cadastrados Acima:");
            System.out.print("CODIGO: ");

            id = Integer.parseInt(sca.nextLine());
            }catch (NumberFormatException e){
                Console.linha();
                System.out.println(" ENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
                System.out.print("\t\t\t\t\tTENTE NOVAMENTE");
                Console.linha();
            }
        }
        else {return id;}
        }
    }
}
