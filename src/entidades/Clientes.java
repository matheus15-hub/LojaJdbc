package entidades;

public class Clientes {
    private int id_clientes;
    private String nome_clientes;
    private String cpf;
    private String email;
    private String endereco;
    public Clientes(){};
    public Clientes(int id_clientes, String nome_clientes, String cpf, String email, String endereco){
        this.cpf = cpf;
        this.id_clientes = id_clientes;
        this.nome_clientes = nome_clientes;
        this.email = email;
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }
    public int getId_clientes() {
        return id_clientes;
    }
    public String getNome_clientes() {
        return nome_clientes;
    }

    public void setNome_clientes(String nome_clientes) {
        this.nome_clientes = nome_clientes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
