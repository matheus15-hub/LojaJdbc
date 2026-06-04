package menu.pedido;

import java.util.Scanner;
import DAO.PedidoDAO;

public class MenuConsultaPedido {

    private final Scanner sca = new Scanner(System.in);

    public void consultarPedidos() {

        PedidoDAO.imprimirPedidoS();

        System.out.print("\nPressione ENTER para voltar...");
        sca.nextLine();
    }
}