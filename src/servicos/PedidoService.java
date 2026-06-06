package servicos;

import java.util.ArrayList;
import java.util.List;
import entidades.ItemPedido;
import enums.StatusPedido;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import util.Console;

public class PedidoService {

    private int idClienteSelecionado;
    private int idVendedorSelecionado;
    private List<ItemPedido> carrinhoComponentes;
    private double valorTotalAcumulado;
    private StatusPedido statusAtualPedido;
    private String observacao; 

    public PedidoService() {
        this.carrinhoComponentes = new ArrayList<>();
        this.valorTotalAcumulado = 0.0;
        this.statusAtualPedido = StatusPedido.ABERTO;
        this.observacao = "Sem observações.";
    }
    public void addClientePedido(int idCliente) {
        this.idClienteSelecionado = idCliente;
    }

    public void addVendedorPedido(int idVendedor) {
        this.idVendedorSelecionado = idVendedor;
    }

    public void addObservacaoPedido(String observacao) {
        this.observacao = observacao;
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
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, this.observacao, statusAtualPedido);
        } else if (opcaoDecisao == 2) {
            statusAtualPedido = StatusPedido.ABERTO;
            PedidoDAO.finalizarVenda(idClienteSelecionado, idVendedorSelecionado, carrinhoComponentes, valorTotalAcumulado, this.observacao, statusAtualPedido);
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
    public void mostrarCarrinho() {

        Console.linha();
        System.out.println("|| ITENS DO CARRINHO");
        Console.linhaSimples();

        for (ItemPedido item : carrinhoComponentes) {

            String nomeProduto = PedidoDAO.buscarNomeProduto(item.getIdProdutos());

            System.out.println("|| ID PRODUTO : " + item.getIdProdutos());
            System.out.println("|| PRODUTO    : " + nomeProduto);
            System.out.println("|| QUANTIDADE : " + item.getQuantidade());
            System.out.println("|| PREÇO UNIT.: R$ "
                    + String.format("%.2f", item.getPrecoUnitario()));
            System.out.println("|| SUBTOTAL   : R$ "
                    + String.format("%.2f", item.getSubtotal()));

            Console.linhaSimples();
        }

        System.out.println("|| TOTAL DO CARRINHO: R$ "
                + String.format("%.2f", valorTotalAcumulado));

        Console.linha();
    }
    public boolean verificarPreRequisitos() {
        boolean tudo = true;

        if (!PedidoDAO.existeProdutoCadastrado()) {
            System.out.println("[AVISO] Nenhum produto cadastrado. Cadastre um produto antes de criar um pedido.");
            tudo = false;
        }

        if (!PedidoDAO.existeClienteCadastrado()) {
            System.out.println("[AVISO] Nenhum cliente cadastrado. Cadastre um cliente antes de criar um pedido.");
            tudo = false;
        }

        if (!PedidoDAO.existeVendedorCadastrado()) {
            System.out.println("[AVISO] Nenhum vendedor cadastrado. Cadastre um vendedor antes de criar um pedido.");
            tudo = false;
        }

        return tudo;
    }
}