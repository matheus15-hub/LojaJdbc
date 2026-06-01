package menu.pedido;

import java.util.Scanner;
import DAO.PedidoDAO;

public class MenuPedidoRemover {
    private Scanner sca = new Scanner(System.in);

    public void removerPedido() {
        System.out.println("\n--- CANCELAR / REMOVER PEDIDO ---");
        System.out.print("Digite o ID do pedido que deseja cancelar: ");
        
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
            System.out.println("Não é permitido remover/cancelar pedidos que não estejam em estado 'ABERTO'.");
            return;
        }

        System.out.print("Tem certeza que deseja cancelar o pedido #" + idPedido + "? (s/n): ");
        String confirmacao = sca.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            PedidoDAO.cancelarPedido(idPedido);
        } else {
            System.out.println("Operação cancelada pelo usuário.");
        }
    }
}