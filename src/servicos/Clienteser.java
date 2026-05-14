package servicos;

import DAO.ClientesDAO;
import entidades.Clientes;

public class Clienteser {

    public void adicionarCli(Clientes clientes){
        // O trim() remove os espaço inuteis, ja o isEmpty() ve se realmente está vazio.
        if(clientes.getNome_clientes() !=null && !clientes.getNome_clientes().trim().isEmpty()){
            ClientesDAO.addCliente(clientes);
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro: O nome do cliente não pode estar vazio!");
        }
    }


    /* Codigo antigo com alterações, para garantir que o niguém cadastre um cliente com nome vazio ou só com espaços em branco.
    public void adicionarCli(Clientes clientes){
        if (clientes.getNome_clientes() != null) {
            ClientesDAO.addCliente(clientes);
            System.out.println("Cliente cadastrado");
        }
    }
    public void mostrarCli(Clientes clientes){
         ClientesDAO.addCliente(clientes);
    }
    */
}
