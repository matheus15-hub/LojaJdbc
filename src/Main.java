import menu.MenuPrincipal;
import threads.ProcessadorPedido;

public class Main {
    public static void main(String[] args) {

        ProcessadorPedido fila = new ProcessadorPedido();
        fila.setDaemon(true);
        fila.start();

        new MenuPrincipal().iniciar();
    }
}