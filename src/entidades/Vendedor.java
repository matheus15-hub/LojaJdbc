package entidades;

public class Vendedor {
    
    private int idVendedor;
    private String nomeVendedor;
    private String telefoneVendedor;
    private String emailVendedor;
    private double comissao;

    public Vendedor(){};
        public Vendedor(int idVendedor, String nomeVendedor, String telefoneVendedor, String emailVendedor, double comissao){
            this.idVendedor = idVendedor;
            this.nomeVendedor = nomeVendedor;
            this.telefoneVendedor = telefoneVendedor;
            this.emailVendedor = emailVendedor;
            this.comissao = comissao;
        }

        public int getIdVendedor() {
            return idVendedor;
        }

        public void setIdVendedor(int idVendedor) {
            this.idVendedor = idVendedor;
        }

                public String getNomeVendedor() {
            return nomeVendedor;
        }

                public void setNomeVendedor(String nomeVendedor) {
            this.nomeVendedor = nomeVendedor;
        }   

                public String getTelefoneVendedor() {
            return telefoneVendedor;
        }

                public void setTelefoneVendedor(String telefoneVendedor) {
            this.telefoneVendedor = telefoneVendedor;
        }

                public String getEmailVendedor() {
            return emailVendedor;
        }

                public void setEmailVendedor(String emailVendedor) {
            this.emailVendedor = emailVendedor;
        }

                public double getComissao() {
            return comissao;
        }

                public void setComissao(double comissao) {
            this.comissao = comissao;
        }
}
