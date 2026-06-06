package servicos;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.Relatorio.RelatorioVendedorDAO;
import DAO.VendedorDAO;
import entidades.Vendedor;

import menu.vendedor.MenuAlteracaoVendedor;
import menu.vendedor.MenuConsultaVendedor;
import util.Console;

public class VendedorSer {
    Scanner sca = new Scanner(System.in);


    public String verificarNome(String nome){
        while(true){
            nome = nome.trim();
            if(nome.isEmpty()){
                System.out.println("Nome vazio");
                nome = new Scanner(System.in).nextLine();
            } else if(nome.length() > 100){
                System.out.println("Máximo 100 caracteres");
                nome = new Scanner(System.in).nextLine();
            } else {
                return nome;
            }
        }
    }



    public String verificarTelefone(String telefone){
        while(true){
            telefone = telefone.trim();
            if(!telefone.matches("\\d{10,11}")){
                System.out.println("Telefone inválido");
                telefone = new Scanner(System.in).nextLine();
            } else {
                return telefone;
            }  
        }
    }

    public String verificarEmail(String email){
        while(true){
            email = email.trim();
            if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                System.out.println("Email inválido");
                email = new Scanner(System.in).nextLine();
            } else {
                return email;
            }
        }
    }

    public void mostrar() {
        System.out.println("MOSTRANDO TODOS OS VENDEDORES CADASTRADOS");
        new VendedorDAO().mostrarVendedor();
    }

    public void mostrarFiltro(String nome){
        System.out.println("PESQUISANDO VENDEDOR...");
        new VendedorDAO().mostrarVendedorPorFiltro(nome);
    }

    public int verificarId(int id) {
        while (true) {
            if (!VendedorDAO.verificarExistencia(id)) {
                System.out.println("Vendedor com o ID " + id + " não encontrado!");
                new MenuConsultaVendedor().metodoBusca();
                System.out.print("\nDigite um ID válido da lista acima: ");
                try {
                    id = Integer.parseInt(sca.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite apenas números!");
                }
            } else {
                return id;
            }
        }
    }

    public void alterarNome(int idVendedor, String nome){
        nome = nome.toUpperCase();
            new VendedorDAO().alterarNome(idVendedor, nome);
            new VendedorDAO().buscarPorId(idVendedor);
            System.out.println("Nome alterado com sucesso!");
    }

    public void alterarTelefone(int idVendedor, String telefone){
            new VendedorDAO().alterarTelefone(idVendedor, telefone);
            System.out.println("Telefone alterado com sucesso!");
    }

    public void alterarEmail(int idVendedor, String email){
        email = email.toUpperCase();
            new VendedorDAO().alterarEmail(idVendedor, email);
            System.out.println("Email alterado com sucesso!");
    }

    public void alterarSalario(int idVendedor, BigDecimal salario){
        new VendedorDAO().alterarSalario(idVendedor, salario);
        System.out.println("Salário alterado com sucesso!");
}
    public void comissaoVendedor(){
        Console.linha();
        System.out.println("||\t\t\tSELECIONADO PEDIODO DA COMISSAO");
        System.out.print("DATA INICIO (EX 01/01/2001): ");
        String inicio = sca.nextLine();
        LocalDate dataInicio = vereficarData(inicio);
        System.out.print("DATA FINAL (EX 01/01/2001): ");
        String finall = sca.nextLine();
        LocalDate dataFinal = vereficarData(finall);
        dataFinal = vereficarInicioMenor(dataFinal , dataInicio);

        LocalDateTime periodoInicial = dataInicio.atStartOfDay();
        LocalDateTime periodoFinal = dataFinal.atTime(23, 59, 59);

        escolhaRelatorioComissao(periodoInicial , periodoFinal);

    }
    public LocalDate vereficarData(String data){
        while (true){
            DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(data, formato1);
            } catch (DateTimeException e) {
                System.out.print("Data inválida.Tente DIgitar no padrão 01/01/2001 (dd/MM/aaaa)  \nDigite novamente: ");
                data = sca.nextLine();
            }
        }
    }
    public LocalDate vereficarInicioMenor(LocalDate f, LocalDate i) {
        while (f.isBefore(i)) {

            System.out.println("A data final não pode ser menor que a inicial.");

            System.out.print("Digite a data final novamente: ");
            f = vereficarData(sca.nextLine());
        }

        return f;
    }
    public void escolhaRelatorioComissao(LocalDateTime ini, LocalDateTime fim){
        Console.linhaSimples();
        System.out.println("Deseja ver a comissão:");
        System.out.println("1)Todos os Vendedores");
        System.out.println("2)Vendedor Especifico");
        try {
            System.out.print("Escolha: ");
            Integer escolha = sca.nextInt();
            if (escolha == 1){
                new RelatorioVendedorDAO().comissaoTodosVendedor(ini , fim);
            }
            else if (escolha ==2){
                int idVendedor = new MenuAlteracaoVendedor().selecionarVendedor();
                new RelatorioVendedorDAO().comissaoPorVendedor(idVendedor , ini, fim);
            }
        } catch (InputMismatchException e) {
            sca.next();
            Console.linhaSimples();
            System.out.println("Entrada inválida.");
            System.out.println("Digite 1 para mostrar todos os vendedores");
            System.out.println("Digite 2 para um vendedor específico");
        }
    }

    public void remover(int x){
        new VendedorDAO().excluirVendedor(x);
        System.out.println("Vendedor com o id " + x + " removido com sucesso!");
    }
    public int selecionarVendedorPedido() {
        try {
            while (true) {
                Console.linha();
                System.out.println("||\t\tSELECIONANDO VENDEDOR");
                System.out.println("|| 1) Buscar por Nome");
                System.out.println("|| 2) Mostrar Todos");
                System.out.print("|| ESCOLHA: ");

                int escolha = Integer.parseInt(sca.nextLine());

                switch (escolha) {
                    case 1:
                        System.out.print("|| Nome: ");
                        String nome = sca.nextLine();
                        new VendedorDAO().listarParaPedidoFiltro(nome);
                        break;

                    case 2:
                        new VendedorDAO().listarParaPedido();
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        continue;
                }

                System.out.print("Digite o ID do vendedor: ");
                int id = Integer.parseInt(sca.nextLine());

                return verificarId(id);

            }

        } catch (NumberFormatException e) {
            Console.linha();
            System.out.println("\t\tENTRADA DE DADOS INVALIDA, APENAS NUMEROS INTEIROS. EX: 1,2...5");
            System.out.print("\t\t\t\tTENTE NOVAMENTE");
            Console.linha();
            return -1;
        }
    }
}