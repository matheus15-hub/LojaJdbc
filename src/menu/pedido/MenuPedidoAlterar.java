package menu.pedido;

import java.util.Scanner;
import DAO.PedidoDAO;

public class MenuPedidoAlterar {
    private Scanner sca = new Scanner(System.in);

    public void alterarPedido() {
        System.out.println("\n--- ALTERAR OBSERVAÇÃO DO PEDIDO ---");
        System.out.print("Digite o ID do pedido: ");
        
        while (!sca.hasNextInt()) {
            System.out.println("Digite apenas números inteiros!");
            System.out.print("Digite o ID do pedido: ");
            sca.next();
        }
        int idPedido = sca.nextInt();
        sca.nextLine();

        if (!PedidoDAO.pedidoExiste(idPedido)) {
            System.out.println("Pedido com código " + idPedido + " não encontrado.");
            return;
        }

        String status = PedidoDAO.buscarStatusPedido(idPedido);
        if (!"ABERTO".equalsIgnoreCase(status)) {
            System.out.println("\n[BLOQUEIO DE SEGURANÇA] Este pedido possui o status: " + status);
            System.out.println("Não é permitido alterar dados de pedidos que não estejam em estado 'ABERTO'.");
            return;
        }

        System.out.print("Digite a nova observação para o pedido: ");
        String novaObs = sca.nextLine();

        PedidoDAO.alterarObservacao(idPedido, novaObs);
    }
}