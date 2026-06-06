package servicos;

import java.util.ArrayList;
import java.util.List;
import entidades.ItemPedido;
import enums.StatusPedido;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;

public class PedidoService {

    private int idClienteSelecionado;
    private int idVendedorSelecionado;
    private List<ItemPedido> carrinhoComponentes;
    private double valorTotalAcumulado;
    private StatusPedido statusAtualPedido;

    public PedidoService() {
        this.carrinhoComponentes = new ArrayList<>();
        this.valorTotalAcumulado = 0.0;
        this.statusAtualPedido = StatusPedido.ABERTO;
    }

    public void addClientePedido(int idCliente) {
        this.idClienteSelecionado = idCliente;
    }

    public void addVendedorPedido(int idVendedor) {
        this.idVendedorSelecionado = idVendedor;
    }

    public boolean tentarAdicionarProduto(int idProd, int qtd) {
        if (!ProdutoDAO.produtoExiste(idProd)) {
            throw new IllegalArgumentException("Produto não encontrado!");
        }

        int estoque = ProdutoDAO.buscarEstoque(idProd);
        if (qtd > estoque) {
            throw new IllegalArgumentException("Estoque insuficiente! Estoque atual: " + estoque);
        }

        double preco = ProdutoDAO.buscarPreco(idProd);
        ItemPedido item = new ItemPedido(idProd, qtd, preco);
        carrinhoComponentes.add(item);
        valorTotalAcumulado += item.getSubtotal();
        return true;
    }

    public void finalizarFluxo(int opcaoDecisao) throws Exception {
        if (opcaoDecisao == 1) {
            statusAtualPedido = StatusPedido.FILA;
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, "", statusAtualPedido);
        } else if (opcaoDecisao == 2) {
            statusAtualPedido = StatusPedido.ABERTO;
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, "", statusAtualPedido);
        } else {
            throw new IllegalArgumentException("Operação cancelada.");
        }
    }

    public void validarSePermiteAlteracao(String statusAtual) {
        if (statusAtual == null || !statusAtual.equalsIgnoreCase("ABERTO")) {
            throw new IllegalStateException("Este pedido possui o status: " + statusAtual + ". Não é permitido alterar dados fora do estado 'ABERTO'.");
        }
    }

    public boolean verificarSePedidoExiste(int idPedido) {
        return PedidoDAO.pedidoExiste(idPedido);
    }

    public List<ItemPedido> getCarrinhoComponentes() {
        return carrinhoComponentes;
    }

    public double getValorTotalAcumulado() {
        return valorTotalAcumulado;
    }
}