package menu.endereco;

import java.util.Scanner;

import entidades.Endereco;
import servicos.EnderecoService;

public class MenuCadastroEndereco {
    Scanner sca = new Scanner(System.in);
    public Endereco addEndereco(){
               System.out.println("========================= NOVO ENDEREÇO==========================");
            System.out.print("Rua: ");
            String rua = sca.nextLine();
            rua = new EnderecoService().vereficarRua(rua);
            System.out.print("Numero: ");
            String numero = sca.nextLine();
            numero = new EnderecoService().vereficarNumero(numero);
            System.out.print("Bairro: ");
            String bairro = sca.nextLine();
            bairro = new EnderecoService().vereficarBairro(bairro);
            System.out.print("Cidade: ");
            String cidade = sca.nextLine();
            cidade = new EnderecoService().vereficarCidade(cidade);
            System.out.print("Cep: ");
            String cep = sca.nextLine();
            cep = new EnderecoService().vereficarCep(cep);
            return new Endereco(rua, numero, bairro, cidade, cep);
    }

}
