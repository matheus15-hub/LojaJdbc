package threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProcessadorPedido extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
                try (Connection conn = conexao.Conexao.criarNovaConexao()) {
                    if (conn == null || conn.isClosed()) continue;

                    int idPedido = -1;
                    String buscar = "select id_pedido from pedido where status_pedido = 'FILA' limit 1";
                    try (PreparedStatement psBuscar = conn.prepareStatement(buscar);
                         ResultSet rs = psBuscar.executeQuery()) {
                        if (rs.next()) {
                            idPedido = rs.getInt("id_pedido");
                        }
                    }

                    if (idPedido != -1) {
                        String processando = "update pedido set status_pedido = 'PROCESSANDO' where id_pedido = ? and status_pedido = 'FILA'";
                        try (PreparedStatement psProc = conn.prepareStatement(processando)) {
                            psProc.setInt(1, idPedido);
                            int atualizados = psProc.executeUpdate();
                            if (atualizados == 0) continue;
                        }

                        System.out.println("\n[thread] processando o pedido #" + idPedido);
                        Thread.sleep(5000);

                        String finalizar = "update pedido set status_pedido = 'FINALIZADO' where id_pedido = ?";
                        try (PreparedStatement psFin = conn.prepareStatement(finalizar)) {
                            psFin.setInt(1, idPedido);
                            psFin.executeUpdate();
                        }
                        System.out.println("\n[thread] pedido #" + idPedido + " finalizado com sucesso!");
                    }
                }
            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                
            }
        }
    }
}