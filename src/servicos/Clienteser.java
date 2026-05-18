package servicos;

import java.util.Scanner;

import DAO.ClientesDAO;
import entidades.Clientes;

public class Clienteser {

    public void adicionarCli(Clientes clientes){
        boolean verificador = true;
        Scanner sca = new Scanner(System.in);
        // O trim() remove os espaço inuteis, ja o isEmpty() ve se realmente está vazio.
        if(clientes.getNome_clientes() !=null && !clientes.getNome_clientes().trim().isEmpty()){
        ClientesDAO.addCliente(clientes);
        System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro: O nome do cliente não pode estar vazio!");
        }
    }

    public void mostrar(Clientes clientes){
        ClientesDAO.mostrarClient(clientes);
    }
    public void mostarFiltro(Clientes clientes){
        ClientesDAO.mostrarClientFiltro(clientes);
    }

}
