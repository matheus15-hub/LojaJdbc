package servicos;

import DAO.EnderecoDAO;

public class EnderecoSer {
    public boolean salvarEndereco(String rua, String bairro, String cep, String cidade){
        return new EnderecoDAO().addEndereco(rua, bairro, cep, cidade);
    }
}
