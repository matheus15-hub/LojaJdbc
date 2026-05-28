package servicos;

import DAO.EnderecoVendedorDAO;

public class EnderecoVendedorSer {
    public boolean vincular(int idVendedor, int idEndereco){
        return new EnderecoVendedorDAO().vincularEnderecoVendedor(idVendedor, idEndereco);
    }
}
