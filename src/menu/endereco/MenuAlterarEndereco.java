package menu.endereco;

import DAO.EnderecoClienteDAO;
import servicos.EnderecoClienteSer;
import servicos.EnderecoService;

import java.util.Scanner;

public class MenuAlterarEndereco {
    Scanner sca = new Scanner(System.in);
    public void menuAlterarEnderecoCliente(int id){
        int idEndereco = selecionarEndereco(id);
        System.out.println("=================================================================");
        System.out.println("||\t\tO que deseja alterar nesse endereço\t\t||");
        System.out.println("|| 1)RUA | 2)NUMERO 3)BAIRRO | 4)CIDADE | 5)CEP | 6)VOLTAR ||");
        System.out.print("||Escolha: ");
        int escolha = sca.nextInt();
        switch (escolha){
            case 1:
                ruaEndereco(id);
                break;
            case 2:
                numeroEndereco(id);
                break;
            case 3:
                bairroEndereco(id);
                break;
            case 4:
                cidadeEndereco(id);
                break;
            case 5:
                cepEndereco(id);
                break;
            case 6:
                return;
            default:
                System.out.println("Opcão invalida!");
                break;
        }


    }
    public int selecionarEndereco(int id){
        int idendereco;
        while (true) {

            System.out.println("=================================================================");
            System.out.println("|| TODOS OS ENDEREÇOS VINCULADOS AO CLINTE " + id + " :");
            new EnderecoClienteDAO().mostrarEndeClie(id);
            System.out.println("Selecione o ID do endereço que deseja alterar:");
            System.out.print("Escolha: ");
            while (!sca.hasNextInt()) {
                System.out.println("===================================================================");
                System.out.println("||Entrada de dados invalidos | Apenas Numeros inteiros são aceitos");
                System.out.println("||\t\t\tEx: 1 , 10 , 20.....");
                sca.nextLine();
                System.out.println("Digite o ID correspondente ao endereço: ");

            }
            idendereco = sca.nextInt();
            idendereco = new EnderecoClienteSer().vereficarLigacao(id, idendereco);
            System.out.println("Deseja mesmo altera o endereço com ID:" + id+" ?");
            System.out.print("1) Sim | 2)Não :");
            int queralterar = sca.nextInt();
            if(queralterar == 1) break;
            if (queralterar != 1  && queralterar != 2) System.out.println("Opção Invalida!");
        }
        return idendereco;
    }
    public void ruaEndereco(int id_endereco) {
        System.out.println("Alteração de Rua");
        System.out.print("Nova Rua: ");
        String rua = sca.nextLine();
        rua = new EnderecoService().vereficarRua(rua);

        new EnderecoService().alterarRua(id_endereco, rua);
    }
    public void numeroEndereco(int id_endereco) {
        System.out.println("Alteração de Número");

        System.out.print("Novo Número: ");
        String numero = sca.nextLine();
        numero = new EnderecoService().vereficarNumero(numero);

        new EnderecoService().alterarNumero(id_endereco, numero);
    }
    public void bairroEndereco(int id_endereco) {
        System.out.println("Alteração de Bairro");

        System.out.print("Novo Bairro: ");
        String bairro = sca.nextLine();
        bairro = new EnderecoService().vereficarBairro(bairro);

        new EnderecoService().alterarBairro(id_endereco, bairro);
    }
    public void cidadeEndereco(int id_endereco) {
        System.out.println("Alteração de Cidade");



        System.out.print("Nova Cidade: ");
        String cidade = sca.nextLine();
        cidade = new EnderecoService().vereficarCidade(cidade);

        new EnderecoService().alterarCidade(id_endereco, cidade);
    }
    public void cepEndereco(int id_endereco) {
        System.out.println("Alteração de CEP");
        System.out.print("Novo CEP (sem formatação): ");
        String cep = sca.nextLine();
        cep = new EnderecoService().vereficarCep(cep);

        new EnderecoService().alterarCep(id_endereco, cep);
    }
}
