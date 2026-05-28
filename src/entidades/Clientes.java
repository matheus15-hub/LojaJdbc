package entidades;

public class Clientes {
    private int id_clientes;
    private String nome_clientes;
    private String cpf;
    private String email_clientes;
    private String bairroClientes;
    private String rua_clientes;


    public Clientes(){

    };
    public Clientes(int id_clientes, String nome_clientes, String cpf, String email_clientes, String bairroClientes, String rua_clientes){
        this.cpf = cpf;
        this.id_clientes = id_clientes;
        this.nome_clientes = nome_clientes;
        this.email_clientes = email_clientes;
        this.bairroClientes = bairroClientes;
        this.rua_clientes = rua_clientes;
    }

    // Getters e Setters
    public int getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(int id_clientes) {
        this.id_clientes = id_clientes;
    }

    public String getNome_clientes() {
        return nome_clientes;
    }

    public void setNome_clientes(String nome_clientes) {
        this.nome_clientes = nome_clientes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getemail_clientes() {
        return email_clientes;
    }

    public void setEmail_clientes(String email_clientes) {
        this.email_clientes = email_clientes;
    }

    public String getBairroClientes() {
        return bairroClientes;
    }

    public void setBairroClientes(String bairroClientes) {
        this.bairroClientes = bairroClientes;
    }

    public String getRua_clientes() {
        return rua_clientes;
    }

    public void setRua_clientes(String rua_clientes) {
        this.rua_clientes = rua_clientes;
    }
}
