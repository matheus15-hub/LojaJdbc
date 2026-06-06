package entidades;

public class ItemPedido {
    private int id_produtos;
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;

    public ItemPedido(int idProdutos, int quantidade, double precoUnitario) {
        this.id_produtos = idProdutos;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = quantidade * precoUnitario;
    }
    public int getIdProdutos() {
        return id_produtos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }
}