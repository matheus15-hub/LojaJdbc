package menu.endereco;

import java.util.Scanner;

import entidades.Endereco;
import servicos.EnderecoSer;

public class MenuEnderecoAdd {
    Scanner sca = new Scanner(System.in);
    public Endereco addEndereco(){
               System.out.println("========================= NOVO ENDEREÇO==========================");
            System.out.print("Rua: ");
            String rua = sca.nextLine();
            rua = new EnderecoSer().vereficarRua(rua);
            System.out.print("Numero: ");
            String numero = sca.nextLine();
            numero = new EnderecoSer().vereficarNumero(numero);
            System.out.print("Bairro: ");
            String bairro = sca.nextLine();
            bairro = new EnderecoSer().vereficarBairro(bairro);
            System.out.print("Cidade: ");
            String cidade = sca.nextLine();
            cidade = new EnderecoSer().vereficarCidade(cidade);
            System.out.print("Cep: ");
            String cep = sca.nextLine();
            cep = new EnderecoSer().vereficarCep(cep);
            return new Endereco(rua, numero, bairro, cidade, cep);
    }

}
