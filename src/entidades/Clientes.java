package entidades;

public class Clientes {
    private int id_clientes;
    private String nome_clientes;
    private String cpf;
    private String email_clientes;


    public Clientes(){};
    public Clientes(int id_clientes, String nome_clientes, String cpf, String email_clientes){
        this.cpf = cpf;
        this.id_clientes = id_clientes;
        this.nome_clientes = nome_clientes;
        this.email_clientes = email_clientes;
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

    public String getemail_clientes() {
        return email_clientes;
    }

    public void setEmail(String email_clientes) {
        this.email_clientes = email_clientes;
    }

}
