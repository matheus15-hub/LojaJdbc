package servicos;

import java.util.Scanner;

import DAO.ClientesDAO;
import DAO.VendedorDAO;
import entidades.Vendedor;
import menu.Menuprint;

public class VendedorServico {
    Scanner sca = new Scanner(System.in);
    public void adicionar(Vendedor v){
        new VendedorDAO().adicionarVendedor(v);
        System.out.println("Cadastrado!");
    }

    public String verificarNome(String nome){
        while(true){
                nome = nome.trim();
            if(nome.isEmpty()){
                System.out.println("Nome vazio");

    nome = new Scanner(System.in).nextLine();
    }else if(
        nome.length()>100){

System.out.println("Máximo 100 caracteres");
        nome =new Scanner(System.in).nextLine();
        }else{
            return nome;
                }
            }
        }
    public double verificarComissao(double comissao){
        while(true){
            if(comissao<0 || comissao>100000){
                System.out.println("Comissão invalida");

                while(!new Scanner(System.in).hasNextDouble()){
                    System.out.println("Digite apenas número");
                }

                comissao = new Scanner(System.in).nextDouble();
            }else{
                return comissao;
                }
            }
        }
    public String verificarTelefone(String telefone){
        while(true){
            telefone = telefone.trim();
        if(!telefone.matches("\\d{10,11}")){
            System.out.println("Telefone inválido");
            telefone =new Scanner(System.in).nextLine();
        }else{
        return telefone;
                }     
            }
        }
    public String verificarEmail(String email){
        while(true){email = email.trim();
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
        System.out.println("Email inválido");
    email =new Scanner(System.in).nextLine();
    }else{
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
            if(VendedorDAO.vereficarExistencia(id)){
                System.out.println("Vendedor com o ID "+id+" nao encontrado, tente novamente" );
                new Menuprint().printVendedor();
                System.out.print("\nID: ");
                while (!sca.hasNextInt()) {
                    System.out.println("Digite apenas números!");
                    sca.nextLine();
                    System.out.print("Digite o ID do Vendedor escolhido: ");
                }
                id = sca .nextInt();
            }else{
                return id;
            }
        }

    }
}