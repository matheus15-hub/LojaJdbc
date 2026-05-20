package servicos;

import DAO.PedidoDAO;

public class Pedidoser {
    public void mostrarTodosPedidos(){
        new PedidoDAO().imprimirPedidoS();
    }
}
