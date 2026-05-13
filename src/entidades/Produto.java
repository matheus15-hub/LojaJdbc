package entidades;

public class Produto {

    private  int id_produtos;
    private String nome_produtos;
    private  float preco;
    private  int estoque;

    public Produto(int id_produtos, String nome_produtos, float preco, int estoque) {
        this.id_produtos = id_produtos;
        this.nome_produtos = nome_produtos;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId_Produtos() {
        return id_produtos;
    }

    public void setId_Produtos(int id_produtos) {
        this.id_produtos = id_produtos;
    }

    public String getNome_Produtos() {
        return nome_produtos;
    }

    public void setNome_Produtos(String nome_produtos) {
        this.nome_produtos = nome_produtos;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
