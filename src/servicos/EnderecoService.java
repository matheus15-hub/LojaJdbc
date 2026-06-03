package servicos;

import DAO.EnderecoDAO;
import entidades.Endereco;
import util.Console;

import java.util.Scanner;

public class EnderecoService {

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
    public void mostrarTudo(){
        Console.linhaEndereco();
        System.out.println("||\t\t\t\t\t MOSTRANDO ENDEREÇOS \t\t\t\t\t||");
        new EnderecoDAO().mostrarEnderecos();
    }
    public void filtrarRua(){
        Console.linhaEndereco();
        System.out.print("Rua: ");
        String rua = sca.nextLine();
        Console.linhaEndereco();
        System.out.println("||\t\t\t\t\t BUSACANDO POR RUA \t\t\t\t\t||");
        new EnderecoDAO().filtrarEnderecosRua(rua);
    }
    public void filtrarCep(){
        Console.linhaEndereco();
        System.out.print("CEP: ");
        String cep = sca.nextLine();
        Console.linhaEndereco();
        System.out.println("||\t\t\t\t\t BUSACANDO POR CEP \t\t\t\t\t||");
        new EnderecoDAO().filtrarEnderecosCep(cep);
    }
    public void metodoBusca(){
        while (true) {
            Console.linhaEndereco();
            System.out.println("||\t\t\t\tBuscar por: 1) Rua  2) Cep 3) Todos\t\t\t\t||");
            System.out.print("Escolha: ");
            int escolhaFiltro = sca.nextInt();
            switch (escolhaFiltro) {
                case 1:
                    filtrarRua();
                    break;
                case 2:
                    filtrarCep();
                    break;
                case 3:
                    mostrarTudo();
                    break;
                default:
                    System.out.println("Escolha invalida!");
            }
            System.out.println("\nGostaria de bucar novamente: 1) Sim 2)Nao");
            System.out.print("Escolha:");
            int buscarNovamente = sca.nextInt();
            if (buscarNovamente == 2) {
                break;
            }
        }
    }
    public int escolherEndereco(int id){

        while (true) {
            Console.linhaEndereco();
            System.out.println("||\t\t\t\t\tESCOLHA DE ENDEREÇO\t\t\t\t\t||");
            metodoBusca();
            Console.linhaEndereco();
            System.out.println("||\t\tDigite o Id do endereço que deseja buscar\t\t||");
            System.out.print("ID: ");
            id = sca.nextInt();
            boolean vereficandoId = new EnderecoDAO().vereficarId(id);
            if (vereficandoId == true){
                break;
            }
        }
        return id;
    }
    public void alterarRua(int id, String rua){
        rua = rua.toUpperCase();
        new EnderecoDAO().alterarRua(id, rua);
        System.out.println("Rua alterada!");
    }
    public void alterarNumero(int id, String numero){
        new EnderecoDAO().alterarNumero(id, numero);
        System.out.println("Número alterado!");
    }
    public void alterarBairro(int id, String bairro){
        bairro = bairro.toUpperCase();
        new EnderecoDAO().alterarBairro(id, bairro);

        System.out.println("Bairro alterado!");
    }
    public void alterarCidade(int id, String cidade){
        cidade = cidade.toUpperCase();
        new EnderecoDAO().alterarCidade(id, cidade);

        System.out.println("Cidade alterada!");
    }
    public void alterarCep(int id, String cep){
        new EnderecoDAO().alterarCep(id, cep);

        System.out.println("CEP alterado!");
    }

}