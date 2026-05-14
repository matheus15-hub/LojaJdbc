package entidades;

public class ItemPedido {
    private int idProdutos;
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;
    private double subtotal;

    public ItemPedido(int idProdutos, int quantidade, double precoUnitario) {
        this.idProdutos = idProdutos;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = quantidade * precoUnitario;
    }

    public int getIdProdutos() {
        return idProdutos;
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