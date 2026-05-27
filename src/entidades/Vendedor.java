package entidades;

public class Vendedor {

    private int id_vendedor;
    private String nome_vendedor;
    private String telefone_vendedor;
    private String email_vendedor;
    private double comissao;

    public Vendedor() {
    }

    public Vendedor( int id_vendedor, 
                     String nome_vendedor,
                     String telefone_vendedor,
                     String email_vendedor) {

        this.id_vendedor = id_vendedor;
        this.nome_vendedor = nome_vendedor.toUpperCase();
        this.telefone_vendedor = telefone_vendedor.toUpperCase();
        this.email_vendedor = email_vendedor.toUpperCase();

        this.comissao = 0;
    }

    public int getIdVendedor() {
        return id_vendedor;
    }

    public void setIdVendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNomeVendedor() {
        return nome_vendedor;
    }

    public void setNomeVendedor(String nome_vendedor) {
        this.nome_vendedor = nome_vendedor.toUpperCase();
    }

    public String getTelefoneVendedor() {
        return telefone_vendedor;
    }

    public void setTelefoneVendedor(String telefone_vendedor) {
        this.telefone_vendedor = telefone_vendedor.toUpperCase();
    }

    public String getEmailVendedor() {
        return email_vendedor;
    }

    public void setEmailVendedor(String email_vendedor) {
        this.email_vendedor = email_vendedor.toUpperCase();
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}