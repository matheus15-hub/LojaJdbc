package entidades;

public class Endereco {
    private int id_endereco;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco (){};
    public Endereco(String rua, String numero , String bairro , String cidade, String cep){
        this.rua = rua.toUpperCase();
        this.numero = numero.toUpperCase();
        this.bairro = bairro.toUpperCase();
        this.cidade = cidade.toUpperCase();
        this.cep = cep.toUpperCase();
    }
    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }
}
