package entidades;

public class Produto {

    private  int id_produtos;
    private String nome_produtos;
    private  float preco;
    private  int estoque;
    private  int idClasse;
    private  int idUnidade;

    public Produto(){};

    public Produto(String nome_produtos, float preco, int estoque, int idClasse , int idUnidade) {
        nome_produtos = nome_produtos.toUpperCase();
        this.nome_produtos = nome_produtos;
        this.preco = preco;
        this.estoque = estoque;
        this.idClasse = idClasse;
        this.idUnidade = idUnidade;
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

    public int getIdClasse() {
        return idClasse;
    }
    public void setId_produtos(int id_produtos) {
        this.id_produtos = id_produtos;
    }
    public int getIdUnidade() {
        return idUnidade;
    }
    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }
}
