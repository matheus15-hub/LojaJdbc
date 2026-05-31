package servicos;

import java.math.BigDecimal;
import java.util.Scanner;
import DAO.VendedorDAO;
import entidades.Vendedor;

import menu.vendedor.MenuVendedorPrint;

public class VendedorServico {
    Scanner sca = new Scanner(System.in);


    public String verificarNome(String nome){
        while(true){
            nome = nome.trim();
            if(nome.isEmpty()){
                System.out.println("Nome vazio");
                nome = new Scanner(System.in).nextLine();
            } else if(nome.length() > 100){
                System.out.println("Máximo 100 caracteres");
                nome = new Scanner(System.in).nextLine();
            } else {
                return nome;
            }
        }
    }

    public double verificarComissao(double comissao){
        while(true){
            if(comissao < 0 || comissao > 100){
                System.out.println("Comissão deve ser entre 0% e 100%");
                while(!sca.hasNextDouble()){
                    System.out.println("Digite número");
                    sca.next();
                }
                comissao = sca.nextDouble();
                sca.nextLine();
            } else {
                return comissao;
            }
        }
    }

    public String verificarTelefone(String telefone){
        while(true){
            telefone = telefone.trim();
            if(!telefone.matches("\\d{10,11}")){
                System.out.println("Telefone inválido");
                telefone = new Scanner(System.in).nextLine();
            } else {
                return telefone;
            }  
        }
    }

    public String verificarEmail(String email){
        while(true){
            email = email.trim();
            if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                System.out.println("Email inválido");
                email = new Scanner(System.in).nextLine();
            } else {
                return email;
            }
        }
    }

    public void mostrar() {
        System.out.println("MOSTRANDO TODOS OS VENDEDORES CADASTRADOS");
        new VendedorDAO().mostrarVendedor();
    }

    public void mostrarFiltro(String nome){
        System.out.println("PESQUISANDO VENDEDOR...");
        new VendedorDAO().mostrarVendedorFiltro(nome);
    }

    public int vereficarId(int id){
        while (true){
            if (!VendedorDAO.verificarExistencia(id)) { 
                System.out.println("Vendedor com o ID " + id + " nao encontrado, tente novamente");
                new MenuVendedorPrint();
                System.out.print("\nID: ");
                while (!sca.hasNextInt()) {
                    System.out.println("Digite apenas números!");
                    sca.nextLine();
                    System.out.print("Digite o ID do Vendedor escolhido: ");
                }
                id = sca.nextInt();
            } else {
                return id;
            }
        }
    }

    public void alterarNome(int idVendedor, String nome){
        nome = nome.toUpperCase();
            new VendedorDAO().alterarNome(idVendedor, nome);
            new VendedorDAO().buscarPorId(idVendedor);
            System.out.println("Nome alterado com sucesso!");
    }

    public void alterarTelefone(int idVendedor, String telefone){
            new VendedorDAO().alterarTelefone(idVendedor, telefone);
            System.out.println("Telefone alterado com sucesso!");
    }

    public void alterarEmail(int idVendedor, String email){
        email = email.toUpperCase();
            new VendedorDAO().alterarEmail(idVendedor, email);
            System.out.println("Email alterado com sucesso!");
    }

    public void alterarSalario(int idVendedor, BigDecimal salario){
        new VendedorDAO().alterarSalario(idVendedor, salario);
        System.out.println("Salário alterado com sucesso!");
}
}