package servicos;

import DAO.ClientesDAO;
import entidades.Clientes;

public class Clienteser {

    public void adicionarCli(Clientes clientes){
        if (clientes.getNome_clientes() != null) {
            ClientesDAO.addCliente(clientes);
            System.out.println("Cliente cadastrado");
        }
    }
    public void mostrarCli(Clientes clientes){
         ClientesDAO.addCliente(clientes);
    }
}
