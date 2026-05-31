package menu.MenuAlterar;

import DAO.VendedorDAO;
import entidades.Vendedor;
import servicos.VendedorServico;

import java.math.BigDecimal;
import java.util.Scanner;

public class AlterarVendedor {

    Scanner sca = new Scanner(System.in);

    private int selecionarVendedor(){

        VendedorServico vendedorServico = new VendedorServico();

        vendedorServico.mostrar();

        System.out.print("Digite o ID do vendedor: ");

        while(!sca.hasNextInt()){
            System.out.println("Digite apenas números.");
            sca.nextLine();
        }

        int idVendedor = sca.nextInt();
        sca.nextLine();

        idVendedor = vendedorServico.vereficarId(idVendedor);

        Vendedor vendedor = new VendedorDAO().buscarPorId(idVendedor);

        System.out.println("\nVendedor Selecionado:");
        System.out.println("ID: " + vendedor.getIdVendedor());
        System.out.println("Nome: " + vendedor.getNomeVendedor());
        System.out.println("Telefone: " + vendedor.getTelefoneVendedor());
        System.out.println("Email: " + vendedor.getEmailVendedor());

        System.out.print("\nÉ este vendedor? (S/N): ");

        String confirmacao = sca.nextLine();

        if(!confirmacao.equalsIgnoreCase("S")){
            return selecionarVendedor();
        }

        return idVendedor;
    }

    public void alterarNome(){

        int idVendedor = selecionarVendedor();

        System.out.print("Novo nome: ");

        String novoNome = sca.nextLine();

        novoNome =
                new VendedorServico().verificarNome(novoNome);

        new VendedorServico()
                .alterarNome(idVendedor, novoNome);
    }

    public void alterarTelefone(){

        int idVendedor = selecionarVendedor();

        System.out.print("Novo telefone: ");

        String novoTelefone = sca.nextLine();

        novoTelefone =
                new VendedorServico().verificarTelefone(novoTelefone);

        new VendedorServico()
                .alterarTelefone(idVendedor, novoTelefone);
    }

    public void alterarEmail(){

        int idVendedor = selecionarVendedor();

        System.out.print("Novo email: ");

        String novoEmail = sca.nextLine();

        novoEmail =
                new VendedorServico().verificarEmail(novoEmail);

        new VendedorServico()
                .alterarEmail(idVendedor, novoEmail);
    }

    public void alterarSalario(){

        int idVendedor = selecionarVendedor();

        System.out.print("Novo salário: ");

        BigDecimal novoSalario =
                new BigDecimal(sca.nextLine().replace(",", "."));

        new VendedorServico()
                .alterarSalario(idVendedor, novoSalario);
    }
}