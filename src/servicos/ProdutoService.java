package servicos;

import DAO.ClasseDAO;
import DAO.MedidaDAO;
import DAO.ProdutoDAO;
import DAO.Relatorio.RelatorioProdutoDAO;
import entidades.Produto;
import menu.produto.MenuConsultaProduto;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProdutoService {
    Scanner sca = new Scanner(System.in);


    public void adicionarProduto(Produto produto) {
            ProdutoDAO.adicionarProduto(produto);
            System.out.println("Produto cadastrado");
    }

    public void mostrar() {
        new ProdutoDAO().listarProdutos();
    }
    public void mostrarId(int id){
        new ProdutoDAO().filtarProdutosId(id);
    }
    public  void filtrarProdutos(String n){
        new ProdutoDAO().filtarProdutos(n);
    }
    public void remover(int x){
        new ProdutoDAO().excluirProduto(x);
        System.out.println("Produto com o Id "+ x +" foi apagado");
    }


    public String verificarNome(String nome) {

        while (true) {

            if (nome == null || nome.trim().isEmpty()) {

                System.out.println("O nome não pode estar vazio!");
                System.out.print("Digite o nome: ");
                nome = sca.nextLine();

            } else if (nome.length() > 100) {

                System.out.println("O nome ultrapassou o limite permitido de 100 caracteres!");
                System.out.print("Digite o nome novamente: ");
                nome = sca.nextLine();

            } else {
                return nome;
            }
        }
    }

    public BigDecimal verificarValor() {

        while (true) {

            System.out.print("Preço: ");

            String entrada = sca.nextLine().replace(",", ".");

            try {

                BigDecimal valor = new BigDecimal(entrada);

                if (valor.compareTo(new BigDecimal("0.01")) < 0) {

                    System.out.println(
                            "Valor inválido!" +
                            "O preço deve ser maior que 0." +
                            "Ex: 1 | 10,50 | 25.99");

                } else if (valor.scale() > 2) {

                    System.out.println(
                        "Valor inválido!" +
                        "O preço pode ter no máximo 2 casas decimais." +
                        "Ex: 10,50 | 25.99");

                } else {

                    return valor;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Entrada inválida!" +
                    "Digite apenas números." +
                    "Ex: 10 | 15,90 | 25.99");
            }
        }
    }
   public int verificarEstoque() {
        while (true) {
            System.out.print("Estoque: ");
            String entrada = sca.nextLine();
            try {
            Integer estoque = Integer.parseInt(entrada);
                if (estoque < 0) {
                    System.out.println("Estoque nao pode ser negativo!");
                    System.out.print("Estoque: ");
                    estoque = sca.nextInt();
                    sca.nextLine();

                } else {
                    return estoque;
                }

            }catch (NumberFormatException e){
                System.out.println(
                        "Entrada invalida!" +
                        "Digite apenas numeros inteiros:" +
                        "EX: 1 , 10 , 30");
            }
        }
    }
    public int verificarId(int id) {

        while (true) {

            if (!ProdutoDAO.produtoExiste(id)) {

                System.out.println("Produto com código " + id + " não encontrado.");

                new MenuConsultaProduto().metodoBusca();

                System.out.println("Digite um dos códigos cadastrados acima:");
                System.out.print("CÓDIGO: ");

                while (!sca.hasNextInt()) {

                    sca.nextLine();

                    System.out.println("Entrada inválida! Digite apenas números inteiros.");
                    System.out.print("Digite um código válido: ");
                }

                id = sca.nextInt();
                sca.nextLine();

            } else {

                return id;
            }
        }
    }

    public void alterarNome(int id, String nome){
        nome = nome.toUpperCase();
        new ProdutoDAO().alterarNome(id , nome);
        new ProdutoService().mostrarId(id);
    }

    public void alterarPreco(int id , BigDecimal f){
        new ProdutoDAO().alterarPreco(id, f);
        new ProdutoService().mostrarId(id);
    }

    public void alterarEstoque(int id, int estoque){
    new ProdutoDAO().alterarEstoque(id, estoque);
    new ProdutoService().mostrarId(id);
    }
    
    public void alterarCategoria(int id, int categoria){
    new ClasseDAO().alterarCategoria(id, categoria);
    new ProdutoService().mostrarId(id);
    }
    
    public void alterarMedida(int id, int medida){
    new MedidaDAO().alterarMedida(id, medida);
    new ProdutoService().mostrarId(id);
    }

    public void quantidadeProduto(){
        new RelatorioProdutoDAO().MaiorQuantidadeProduto();
    }

    public void precoAltoProduto(){
        new RelatorioProdutoDAO().MaisCaroProduto();
    }

    public void precoBaixoProduto(){
        new RelatorioProdutoDAO().MaisBaratoProduto();
    }

    public void maisVendidos(){
        new RelatorioProdutoDAO().MaisVendidoProduto();
    }

    public void quantidadeCategoria(){
        new RelatorioProdutoDAO().QuantidadeCategoriaProduto();
    }

    public void valorMedio(){
        new RelatorioProdutoDAO().MediaValorProduto();
    }
}

