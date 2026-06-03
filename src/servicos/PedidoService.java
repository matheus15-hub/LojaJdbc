package servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entidades.ItemPedido;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;

public class PedidoService {

    private int idClienteSelecionado;
    private int idVendedorSelecionado;
    private List<ItemPedido> carrinhoComponentes;
    private double valorTotalAcumulado;
    private String statusAtualPedido;
    private final Scanner sca = new Scanner(System.in);

    public PedidoService() {
        this.carrinhoComponentes = new ArrayList<>();
        this.valorTotalAcumulado = 0.0;
        this.statusAtualPedido = "ABERTO";
    }

    public void addClientePedido(int idCliente) {
        this.idClienteSelecionado = idCliente;
    }

    public void addVendedorPedido(int idVendedor) {
        this.idVendedorSelecionado = idVendedor;
    }

    public boolean tentarAdicionarProduto(int idProd, int qtd) {
        if (!ProdutoDAO.produtoExiste(idProd)) {
            System.out.println("Produto não encontrado!");
            return false;
        }

        int estoque = ProdutoDAO.buscarEstoque(idProd);
        if (qtd > estoque) {
            System.out.println("Estoque insuficiente! Estoque atual: " + estoque);
            return false;
        }

        double preco = ProdutoDAO.buscarPreco(idProd);
        System.out.println("Preço unitário: R$ " + preco);

        ItemPedido item = new ItemPedido(idProd, qtd, preco);
        carrinhoComponentes.add(item);
        valorTotalAcumulado += item.getSubtotal();
        return true;
    }

    public void finalizarFluxo(int opcaoDecisao) {
        if (opcaoDecisao == 1) {
            this.statusAtualPedido = "FILA";
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, "", this.statusAtualPedido);
            System.out.println("Pedido enviado com sucesso para a fila de processamento!");
        } else if (opcaoDecisao == 2) {
            this.statusAtualPedido = "ABERTO";
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, "", this.statusAtualPedido);
            System.out.println("Pedido salvo e mantido com o status 'ABERTO' para futuras alterações.");
        } else {
            System.out.println("Operação cancelada. A venda não foi registrada.");
        }
    }

    public void validarSePermiteAlteracao(String statusAtual) {
        if (statusAtual == null || !statusAtual.equalsIgnoreCase("ABERTO")) {
            System.out.println("\n[BLOQUEIO DE SEGURANÇA] Este pedido possui o status: " + statusAtual);
            System.out.println("Não é permitido alterar dados de pedidos que não estejam em estado 'ABERTO'.");
            throw new IllegalStateException("Operação negada: Pedido bloqueado para alterações.");
        }
    }

    public int verificarId(int idPedido) {
        while (true) {
            if (!PedidoDAO.pedidoExiste(idPedido)) {
                System.out.println("Pedido com código " + idPedido + " não encontrado.");
                System.out.print("Digite um ID de pedido válido: ");
                while (!sca.hasNextInt()) {
                    System.out.println("Entrada inválida! Digite apenas números inteiros.");
                    sca.next();
                }
                idPedido = sca.nextInt();
                sca.nextLine();
            } else {
                return idPedido;
            }
        }
    }

    public void processarCancelamento(int idPedido) {
        String status = PedidoDAO.buscarStatusPedido(idPedido);
        try {
            validarSePermiteAlteracao(status);
            PedidoDAO.cancelarPedido(idPedido);
        } catch (IllegalStateException e) {
        }
    }

    public List<ItemPedido> getCarrinhoComponentes() { return carrinhoComponentes; }
    public double getValorTotalAcumulado() { return valorTotalAcumulado; }
}