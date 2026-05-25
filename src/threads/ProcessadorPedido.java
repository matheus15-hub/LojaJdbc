package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProcessadorPedido extends Thread {

    @Override
    public void run() {
        System.out.println("[THREAD] Monitor de pedidos iniciado em segundo plano.");

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(3000); 

                try (Connection conn = conexao.Conexao.criarNovaConexao()) {
                    if (conn == null || conn.isClosed()) {
                        continue;
                    }

                    int idPedido = -1;
                    String buscarPedido = "SELECT id_pedido FROM pedido WHERE status_pedido = 'FILA' LIMIT 1";
                    
                    try (PreparedStatement psBuscar = conn.prepareStatement(buscarPedido);
                         ResultSet rs = psBuscar.executeQuery()) {
                        if (rs.next()) {
                            idPedido = rs.getInt("id_pedido");
                        }
                    }

                    if (idPedido != -1) {
                        String atualizar = "UPDATE pedido SET status_pedido = 'PROCESSANDO' WHERE id_pedido = ?";
                        try (PreparedStatement psUpdate = conn.prepareStatement(atualizar)) {
                            psUpdate.setInt(1, idPedido);
                            psUpdate.executeUpdate();
                        }

                        System.out.println("\n[THREAD] Pedido #" + idPedido + " capturado e alterado para PROCESSANDO...");

                        Thread.sleep(5000);

                        String finalizar = "UPDATE pedido SET status_pedido = 'FINALIZADO' WHERE id_pedido = ?";
                        try (PreparedStatement psFinalizar = conn.prepareStatement(finalizar)) {
                            psFinalizar.setInt(1, idPedido);
                            psFinalizar.executeUpdate();
                        }

                        System.out.println("\n[THREAD] Sucesso! Pedido #" + idPedido + " foi FINALIZADO.");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("[THREAD] Processador parado.");
                break;
            } catch (Exception e) {
                System.out.println("[THREAD] Aguardando conexão estável... " + e.getMessage());
            }
        }
    }
}