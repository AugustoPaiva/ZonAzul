package comviewzonazul.google.httpssites.zonazul.pontovenda.dominio;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;

public class PontoVenda {
    public int id;
    public int user_id;
    private String nomeEstabelecimento;
    private String CNPJ;
    private String Email;
    Endereco endereco;

    public PontoVenda(String nomeEstabelecimento_, String CNPJ_, String email, Endereco endereco_){
        nomeEstabelecimento = nomeEstabelecimento_;
        CNPJ = CNPJ_;
        endereco = endereco_;
        Email = email;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}