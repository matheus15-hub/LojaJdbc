package menu.endereco;

import java.util.Scanner;

import entidades.Endereco;
import servicos.EnderecoService;
import util.Console;

public class MenuCadastroEndereco {
    Scanner sca = new Scanner(System.in);
    public Endereco addEndereco(){
            Console.linha();
            System.out.println("\t\t\t\t NOVO ENDEREÇO \t\t\t\t");
            Console.linha();
            System.out.print("Rua: ");
            String rua = sca.nextLine();
            rua = new EnderecoService().verificarRua(rua);
            System.out.print("Numero: ");
            String numero = sca.nextLine();
            numero = new EnderecoService().verificarNumero(numero);
            System.out.print("Bairro: ");
            String bairro = sca.nextLine();
            bairro = new EnderecoService().verificarBairro(bairro);
            System.out.print("Cidade: ");
            String cidade = sca.nextLine();
            cidade = new EnderecoService().verificarCidade(cidade);
            System.out.print("Cep: ");
            String cep = sca.nextLine();
            cep = new EnderecoService().verificarCep(cep);
            return new Endereco(rua, numero, bairro, cidade, cep);
    }

}
