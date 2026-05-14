package entidades;

public class Produto {

    private  int id_produtos;
    private String nome_produtos;
    private  float preco;
    private  int estoque;
    private  String categoria;
    private String medida_vendas;

    public Produto(){};

    public Produto(String nome_produtos, float preco, int estoque, String categoria , String medida_vendas) {
        this.nome_produtos = nome_produtos;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
        medida_vendas = medida_vendas.toUpperCase();
        this.medida_vendas = medida_vendas;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMedida_vendas() {
        return medida_vendas;
    }

    public void setMedida_vendas(String medida_vendas) {
        this.medida_vendas = medida_vendas;
    }
}
