package servicos;

import java.util.ArrayList;
import java.util.List;
import entidades.ItemPedido;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;

public class PedidoServico {

    private int idClienteSelecionado;
    private int idVendedorSelecionado;
    private List<ItemPedido> carrinhoComponentes;
    private double valorTotalAcumulado;
    private String observacaoPedido;
    private String statusAtualPedido;

    public PedidoServico() {
        this.carrinhoComponentes = new ArrayList<>();
        this.valorTotalAcumulado = 0.0;
        this.observacaoPedido = "";
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
            System.out.println("Estoque insuficiente!");
            return false;
        }

        double preco = ProdutoDAO.buscarPreco(idProd);
        System.out.println("Preço do produto: R$ " + preco);

        ItemPedido item = new ItemPedido(idProd, qtd, preco);
        carrinhoComponentes.add(item);
        valorTotalAcumulado += item.getSubtotal();
        return true;
    }

    public void definirObservacao(String observacao) {
        this.observacaoPedido = observacao;
    }

    public void finalizarFluxo(int opcaoDecisao) {
    if (opcaoDecisao == 1) {
        this.statusAtualPedido = "EM_FILA";
        PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, observacaoPedido, this.statusAtualPedido);
        System.out.println("Pedido enviado para a fila de processamento!");
    } else if (opcaoDecisao == 2) {
        this.statusAtualPedido = "ABERTO";
        PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, observacaoPedido, this.statusAtualPedido);
        System.out.println("Pedido salvo e mantido sob o status 'ABERTO'.");
    } else {
        System.out.println("Venda cancelada com sucesso.");
    }
}

    public void validarSePermiteAlteracao(String statusAtual) {
    if (!statusAtual.equalsIgnoreCase("ABERTO")) {
        System.out.println("\n[BLOQUEIO DE SEGURANÇA] Este pedido possui o status: " + statusAtual);
        System.out.println("Não é permitido alterar dados de pedidos que não estejam em estado 'ABERTO'.");
        throw new IllegalStateException("Operação negada: Pedido bloqueado para alterações.");
    }
}

    public List<ItemPedido> getCarrinhoComponentes() { return carrinhoComponentes; }
    public double getValorTotalAcumulado() { return valorTotalAcumulado; }
}