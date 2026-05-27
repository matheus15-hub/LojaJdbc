package servicos;

import DAO.VendedorDAO;
import entidades.Vendedor;

public class VendedorServico {
    public void adicionar(Vendedor v){
        new VendedorDAO().addVendedor(v);
        System.out.println("Cadastrado!");
    }
     public void mostrar() {
        System.out.println("MOSTRANDO TODOS OS VENDEDORES CADASTRADOS");
        new VendedorDAO().mostrarVendedor();
    }
    public void mostrarFiltro(String nome){
        System.out.println("PESQUISANDO VENDEDOR...");
        new VendedorDAO().mostrarVendedorFiltro(nome);
    }
}