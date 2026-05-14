package servicos;

import DAO.VendedorDAO;
import entidades.Vendedor;

public class VendedorServico {
    public void adicionar(Vendedor v){
        new VendedorDAO().adicionarVendedor(v);
        System.out.println("Cadastrado!");
    }
    public void mostrar(Vendedor v){
        System.out.println("MOSTRANDO TODOS OS VENDEDORES CADASTRADO");
        new VendedorDAO().mostrarVendedor(v);
    }
}