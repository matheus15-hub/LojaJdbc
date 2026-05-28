package servicos;

import DAO.EnderecoClienteDAO;

public class EnderecoClienteSer {
        public boolean vincular(int idCliente, int idEndereco) {
            return new EnderecoClienteDAO().VincularEnderecoCliente(idCliente, idEndereco);
        }
}
