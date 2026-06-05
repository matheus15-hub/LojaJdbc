package menu.pedido;

import java.util.Scanner;
import util.Console;
import DAO.Relatorio.RelatorioPedidosDAO;

public class MenuRelatorioPedido {
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        while (true) {
            System.out.println("===========================Metodo de Busca===========================");
            System.out.println("1)Mais Itens");
            System.out.println("2)Valor Mais Alto");
            System.out.println("3)Valor Mais Baixo");
            System.out.println("4)Valor Médio");
            System.out.println("5) Voltar");
            Console.linhaSimples();
                System.out.print("Escolha:  ");
                int busca = sca.nextInt();
                switch (busca) {
                    case 1:
                        new RelatorioPedidosDAO().MaisItensPedido();
                    break;
                    case 2:
                        new RelatorioPedidosDAO().MaisCaroPedido();
                    break;
                    case 3: 
                        new RelatorioPedidosDAO().MaisBaratoPedido();
                    break;
                    case 4: 
                        new RelatorioPedidosDAO().ValorMedioPedido();
                    break;
                    case 5:
                        return;
                    default:
                        System.out.println("===========================Escolha invalida!===========================");
                        System.out.println("===========================Tente novamente===========================");
                }
        }
    }
}
