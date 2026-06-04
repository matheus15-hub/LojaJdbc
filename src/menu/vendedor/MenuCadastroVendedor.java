package menu.vendedor;

import java.math.BigDecimal;
import java.util.Scanner;

import entidades.Endereco;
import entidades.Vendedor;
import menu.endereco.MenuCadastroEndereco;
import servicos.EnderecoService;
import servicos.EnderecoVendedorService;
import servicos.VendedorSer;

public class MenuCadastroVendedor {
    Scanner sca = new  Scanner(System.in);
      public void Vendedoradd() {

        System.out.print("Nome do Vendedor: ");
        String nome = sca.nextLine();
        nome = new VendedorSer().verificarNome(nome);

        System.out.print("Telefone: ");
        String tel = sca.nextLine();
        tel = new VendedorSer().verificarTelefone(tel);

        System.out.print("Email: ");
        String email = sca.nextLine();
        email = new VendedorSer().verificarEmail(email);

        System.out.print("Salário: ");
        while (!sca.hasNextDouble()) {
            System.out.println("Digite um número válido para o salário!");
            sca.next();
            System.out.print("Salário: ");
        }
        BigDecimal salario = sca.nextBigDecimal();
        sca.nextLine();
        Vendedor vendedor = new Vendedor(nome, tel ,email , salario);
        System.out.println("Dejesa: 1) Criar um novo endereço  | 2 ) Selecionar um endereço existente");
        System.out.print("Escolha: ");
        int escolhaVinculacaoEndereco = sca.nextInt();
        if (escolhaVinculacaoEndereco == 1) {
 
       Endereco endereco = new MenuCadastroEndereco().addEndereco();

       new EnderecoVendedorService().addVendedorEndereco(vendedor, endereco);

    } else if (escolhaVinculacaoEndereco == 2) {

    int idEndereco = 0;
    idEndereco = new EnderecoService().escolherEndereco(idEndereco);

    new EnderecoVendedorService().vincularVendedorEndero(vendedor, idEndereco);
}

    }

}
