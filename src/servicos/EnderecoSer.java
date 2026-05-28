package servicos;

import DAO.EnderecoDAO;
import entidades.Endereco;

import java.util.Scanner;

public class EnderecoSer {

    Scanner sca = new Scanner(System.in);

    public void salvarEndereco(Endereco e) {
         new EnderecoDAO().addEndereco(e);
    }

    public String vereficarRua(String rua) {

        while (true) {

            if (rua == null || rua.trim().isEmpty()) {

                System.out.println("===========================================");
                System.out.println("Entrada Invalida: Rua Nao Pode Ser Vazio");
                System.out.println("============Digite novamente===============");
                System.out.print("Rua: ");

                rua = sca.nextLine();

            }
            else if (rua.length() > 100) {

                System.out.println("===========================================");
                System.out.println("    Maximo de caracter permitido é 100");
                System.out.println("============Digite novamente===============");
                System.out.print("Rua: ");

                rua = sca.nextLine();

            }
            else {
                return rua.toUpperCase();
            }
        }
    }

    public String vereficarNumero(String numero) {

        while (true) {

            if (numero == null || numero.trim().isEmpty()) {

                System.out.println("=============================================");
                System.out.println("Entrada Invalida: Numero Nao Pode Ser Vazio");
                System.out.println("===============Digite novamente=============");
                System.out.print("Numero: ");

                numero = sca.nextLine();

            }
            else if (!numero.matches(".*\\d.*")) {

                System.out.println("=================================================");
                System.out.println("Entrada Invalida: Numero Deve Conter Algum Numero");
                System.out.println("================Digite novamente=================");
                System.out.print("Numero: ");

                numero = sca.nextLine();

            }
            else if (numero.length() > 10) {

                System.out.println("===========================================");
                System.out.println("    Maximo de caracter permitido é 10");
                System.out.println("============Digite novamente===============");
                System.out.print("Numero: ");

                numero = sca.nextLine();

            }
            else {
                return numero.toUpperCase();
            }
        }
    }

    public String vereficarBairro(String bairro) {

        while (true) {

            if (bairro == null || bairro.trim().isEmpty()) {

                System.out.println("==============================================");
                System.out.println("Entrada Invalida: Bairro Nao Pode Ser Vazio");
                System.out.println("===============Digite novamente==============");
                System.out.print("Bairro: ");

                bairro = sca.nextLine();

            }
            else if (bairro.length() > 100) {

                System.out.println("===========================================");
                System.out.println("    Maximo de caracter permitido é 100");
                System.out.println("============Digite novamente===============");
                System.out.print("Bairro: ");

                bairro = sca.nextLine();

            }
            else {
                return bairro.toUpperCase();
            }
        }
    }

    public String vereficarCidade(String cidade) {

        while (true) {

            if (cidade == null || cidade.trim().isEmpty()) {

                System.out.println("==============================================");
                System.out.println("Entrada Invalida: Cidade Nao Pode Ser Vazio");
                System.out.println("===============Digite novamente==============");
                System.out.print("Cidade: ");

                cidade = sca.nextLine();

            }
            else if (cidade.length() > 100) {

                System.out.println("===========================================");
                System.out.println("    Maximo de caracter permitido é 100");
                System.out.println("============Digite novamente===============");
                System.out.print("Cidade: ");

                cidade = sca.nextLine();

            }
            else {
                return cidade.toUpperCase();
            }
        }
    }

    public String vereficarCep(String cep) {

        while (true) {

            if (cep == null || cep.trim().isEmpty()) {

                cep = "0000000";

            }

            else if (cep.length() > 8) {

                System.out.println("===========================================");
                System.out.println("    Maximo de caracter permitido é 9");
                System.out.println("============Digite novamente===============");
                System.out.print("CEP: ");

                cep = sca.nextLine();

            }
            else {
                return cep.toUpperCase();
            }
        }
    }
}