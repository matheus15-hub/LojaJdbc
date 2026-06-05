package menu.cliente;
import java.util.Scanner;
import DAO.Relatorio.RelatorioClienteDAO;
import menu.MenuRelatorio;
import servicos.ClienteService;
import util.Console;

public class MenuRelatorioCliente {
    
    Scanner sca = new Scanner(System.in);
     public void metodoBusca() {
        try {
            Console.linha();
            System.out.println("===========================Metodo de Busca===========================");
            System.out.println("|| Buscar Por:");
            System.out.println("|| 1) Cliente por Bairro");
            System.out.println("|| 2) Cliente por Cidade");
            System.out.println("|| 3) Cliente com Mais Compras");
            System.out.println("|| 4) Cliente com Menos Compras");
            System.out.println("|| 5) Cliente com o Produto Mais Caro");
            System.out.println("|| 6) Valor Médio das Compras");
            System.out.println("|| 7) Voltar");
            Console.linhaSimples();
                System.out.print("Escolha:  ");
                int busca = Integer.parseInt(sca.nextLine());;
                switch (busca) {
                    case 1:
                        new RelatorioClienteDAO().ClientePorBairro();   
                    break;
                    case 2:
                        new RelatorioClienteDAO().ClientePorCidade();
                    break;
                     case 3:
                        new RelatorioClienteDAO().MaisComprasCliente();
                    break;
                     case 4:
                        new RelatorioClienteDAO().MenosComprasCliente();
                    break;
                    case 5:
                        new RelatorioClienteDAO().MaisCaraCliente();
                      break;
                    case 6:
                        new RelatorioClienteDAO().MediaCompraCliente();
                    break;
                    case 7:
                        new MenuRelatorio().metodoBusca();
                    default:
                    System.out.println("===========================Escolha invalida!===========================");
                    System.out.println("===========================Tente novamente===========================");
              }
          } catch (NumberFormatException e) {
            sca.next();
            Console.linhaSimples();
            System.out.println("\t Entrada de Dados Invalidas! Apenas Numeros Inteiros Como 1,2,3..\n\t\t\t Tente Novamente");
          }
    }
}
