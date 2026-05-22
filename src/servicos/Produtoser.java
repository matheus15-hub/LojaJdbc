package servicos;

import DAO.ProdutoDAO;
import entidades.Produto;

import java.util.Scanner;

public class Produtoser {
    Scanner sca = new Scanner(System.in);


    public void adicionar(Produto produto) {
            ProdutoDAO.addProduto(produto);
            System.out.println("Produto cadastrado");
    }

    public void mostrar() {
        new ProdutoDAO().mostrarProduts();
    }

    public  void filtro(String n){
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


    public float verificarValor(float valor) {

        while (true) {

            if (valor <= 0) {

                System.out.println("Preço inválido!");
                System.out.print("Preço: ");

                while (!sca.hasNextFloat()) {

                    System.out.println("Valor inválido! Digite apenas números. Ex: 10 ou 15.99");
                    sca.nextLine();
                    System.out.print("Preço: ");
                }

                valor = sca.nextFloat();
                sca.nextLine();

            } else {
                return valor;
            }
        }
    }public int verificarEstoque(int estoque) {

        while (true) {

            if (estoque < 0) {

                System.out.println("Estoque nao pode ser negativo!");
                System.out.print("Estoque: ");

                while (!sca.hasNextInt()) {

                    System.out.println("Entrada inválida! O estoque deve conter apenas números inteiros. Ex: 1, 5, 20...");
                    sca.nextLine();
                    System.out.print("Estoque: ");
                }

                estoque = sca.nextInt();
                sca.nextLine();

            } else {
                return estoque;
            }
        }
    }
    public int verificarId(int id) {

        while (true) {

            if (!ProdutoDAO.produtoExiste(id)) {

                System.out.println("Produto com código " + id + " não encontrado.");

                new ProdutoDAO().mostrarProduts();

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


    }

