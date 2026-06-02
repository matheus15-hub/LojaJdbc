package menu.pedido;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import conexao.Conexao;
import DAO.PedidoDAO;

public class MenuPedidoRemover {
    private Scanner sca = new Scanner(System.in);

    public void removerPedido() {
        System.out.println("\n--- CANCELAR / REMOVER PEDIDO ---");
        System.out.println("=======================================================================");
        System.out.println("|| PEDIDOS DISPONÍVEIS PARA REMOÇÃO (STATUS: ABERTO):                 ||");
        System.out.println("=======================================================================");

        String sqlAbertos = "SELECT id_pedido, valor_total FROM pedido WHERE status_pedido = 'ABERTO'";
        int contagem = 0;

        try (Connection conn = Conexao.criarNovaConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlAbertos)) {

            while (rs.next()) {
                contagem++;
                System.out.println("|| -> ID DO PEDIDO: #" + rs.getInt("id_pedido") + " | Valor Total: R$ " + rs.getDouble("valor_total"));
            }

            if (contagem == 0) {
                System.out.println("|| NENHUM PEDIDO EM ESTADO 'ABERTO' ENCONTRADO NO MOMENTO.           ||");
            }

        } catch (Exception e) {
            System.out.println("|| Erro ao carregar lista de abertos: " + e.getMessage());
        }
        System.out.println("=======================================================================\n");

        if (contagem == 0) {
            System.out.println("Retornando ao menu anterior já que não existem pedidos para remover.");
            return;
        }

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