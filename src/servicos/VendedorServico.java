package servicos;

import java.util.Scanner;
import DAO.VendedorDAO;
import entidades.Vendedor;
import menu.Menuprint;

public class VendedorServico {
    Scanner sca = new Scanner(System.in);
    
    public boolean adicionarNovo(String nome, String tel, String email, double salario) {

    Vendedor vendedor = new Vendedor();
    vendedor.setNomeVendedor(verificarNome(nome).toUpperCase());
    vendedor.setTelefoneVendedor(verificarTelefone(tel).toUpperCase());
    vendedor.setEmailVendedor(verificarEmail(email).toUpperCase());
    vendedor.setSalario(java.math.BigDecimal.valueOf(salario));

    return new VendedorDAO().addVendedor(vendedor);
}

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
                new Menuprint().printVendedor();
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
}