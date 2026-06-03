package menu.pedido;

import java.util.Scanner;
import DAO.PedidoDAO;

public class MenuConsultaPedido {
    private Scanner sca = new Scanner(System.in);

    public void exibirMenuPrint() {
        System.out.println("\n--- CONSULTA DETALHADA DE PEDIDOS ---");
        
        PedidoDAO.imprimirPedidoS();
        
        System.out.print("\nPressione ENTER para voltar ao menu anterior...");
        sca.nextLine();
    }
}