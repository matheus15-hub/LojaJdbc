package entidades;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private int idVendedor;
    private double valorTotal;

    public Pedido(int idPedido, int idCliente, int idVendedor, double valorTotal){
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
