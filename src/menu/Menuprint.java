package menu;

import entidades.Clientes;
import entidades.Produto;
import entidades.Vendedor;
import servicos.Clienteser;
import servicos.Produtoser;
import servicos.VendedorServico;

public class Menuprint {
    Produto p = new Produto();
    Clientes c = new Clientes();
    Vendedor v = new Vendedor();

    public void printProduto(){new Produtoser().mostrar(p);}

    public void printCliente(){new Clienteser().mostrar(c);}

    public void printVendedor(){new VendedorServico().mostrar(v);}
    }

