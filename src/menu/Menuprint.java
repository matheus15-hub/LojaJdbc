package menu;

import DAO.PedidoDAO;
import entidades.Clientes;
import entidades.Pedido;
import entidades.Produto;
import entidades.Vendedor;
import servicos.Clienteser;
import servicos.Pedidoser;
import servicos.Produtoser;
import servicos.VendedorServico;
import java.util.Scanner;

public class Menuprint {
    Produto p = new Produto();
    Clientes c = new Clientes();
    Vendedor v = new Vendedor();
    Scanner sca = new Scanner(System.in);

    public void metodoBusca() {
        System.out.println("===Metodo de Busca===");
        System.out.println("Caso Deseje buscar todos os itens listados apenas de Enter");
        System.out.println("Caso Deseje buscar um produto especifico digite o nome dele");
        System.out.print("Busca: ");
        String busca = sca.nextLine();
        if (busca.trim().isEmpty()) {
            printProduto();
        } else {
            printProdutoFiltro(busca);
        }
    }

    public void printProduto() {
        new Produtoser().mostrar();
    }

    public void printProdutoFiltro(String nome) {
        new Produtoser().filtro(nome);
    }

    public void printCliente() {
        new Clienteser().mostrar();
    }

    public void printClienteFiltro() {
        System.out.println("Digite o nome do cliente: ");
        String nome_clientes = sca.nextLine();
        new Clienteser().mostrarFiltro(nome_clientes);
    }

    public void printVendedor() {
        new VendedorServico().mostrar();
    }

    public void printVendedorFiltro() {

        System.out.println("Digite o nome do vendedor: ");
        String nome = sca.nextLine();

        new VendedorServico().mostrarFiltro(nome);
    }

    public void printPedido() {
        new Pedidoser().mostrarTodosPedidos();
    }
}
