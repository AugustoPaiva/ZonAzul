package comviewzonazul.google.httpssites.zonazul.cliente.dominio;

public class Endereco {

    public String numero,complemento,cep,cidade;

    public Endereco(){
    }

    public Endereco(String numero_, String complemento_, String cep_, String cidade_){
        numero = numero_;
        complemento = complemento_;
        cep = cep_;
        cidade = cidade_;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
