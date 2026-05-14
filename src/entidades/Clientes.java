package entidades;

public class Clientes {
    private int id_clientes;
    private String nome_clientes;
    private String cpf;

    public Clientes(int id_clientes, String nome_clientes, String cpf){
        this.cpf = cpf;
        this.id_clientes = id_clientes;
        this.nome_clientes = nome_clientes;
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

}
