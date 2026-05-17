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

    public void mostrar(Produto produto) {
        new ProdutoDAO().mostrarProduts(produto);
    }

    public String verificarNome(String nome) {

        while (true) {

            if (nome == null || nome.trim().isEmpty()) {

                System.out.println("Nome nao pode ser vazio!");
                System.out.print("Nome: ");
                nome = sca.nextLine();

            } else if (nome.length() > 100) {

                System.out.println("Nome nao pode ter mais de 100 caracteres!");
                System.out.print("Nome: ");
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

                    System.out.println("Digite apenas números!");
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

                    System.out.println("Digite apenas numeros inteiros!");
                    sca.nextLine();
                    System.out.print("Estoque: ");
                }

                estoque = sca.nextInt();
                sca.nextLine();

            } else {
                return estoque;
            }
        }
    }public String verificarUnidade(String unidade) {

        while (true) {

            if (!unidade.equalsIgnoreCase("uni") &&
                    !unidade.equalsIgnoreCase("m") &&
                    !unidade.equalsIgnoreCase("m2") &&
                    !unidade.equalsIgnoreCase("m3") &&
                    !unidade.equalsIgnoreCase("kg")) {

                System.out.println("Unidade inválida!");
                System.out.println("UNI, M, M2, M3, KG");

                System.out.print("Medida: ");
                unidade = sca.nextLine();

            } else {
                return unidade;
            }
        }
    }

    }

