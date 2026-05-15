package menu;

import entidades.Clientes;
import entidades.Produto;
import entidades.Vendedor;
import servicos.Clienteser;
import servicos.Produtoser;
import servicos.VendedorServico;
import java.util.Scanner;

public class Menuprint {
    Produto p = new Produto();
    Clientes c = new Clientes();
    Vendedor v = new Vendedor();
    Scanner sca = new Scanner(System.in);

    public void printProduto(){new Produtoser().mostrar(p);}

    public void printCliente(){new Clienteser().mostrar(c);}
    public void printClienteFiltro(Clientes c){
        System.out.println("Digite o nome do cliente: ");
        c.setNome_clientes(sca.nextLine());
        
        new Clienteser().mostarFiltro(c);}

    public void printVendedor(){new VendedorServico().mostrar(); }

    public void printVendedorFiltro() {

        System.out.println("Digite o nome do vendedor: ");
        String nome = sca.nextLine();

        new VendedorServico().mostrarFiltro(nome);
    }
    }

