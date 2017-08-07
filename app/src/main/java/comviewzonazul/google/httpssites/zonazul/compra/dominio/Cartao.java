package comviewzonazul.google.httpssites.zonazul.compra.dominio;

public class Cartao {
    String nomeTitular,numero,mes,ano,codSeguranca;

    public Cartao(){
    }

    public Cartao(String nomeTitular_, String numero_, String mes_, String ano_, String codSeguranca_){
        nomeTitular = nomeTitular_;
        numero = numero_;
        mes = mes_;
        ano = ano_;
        codSeguranca = codSeguranca_;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }
    public String getNumero() {
        return numero;
    }
    public String getMes() {
        return mes;
    }
    public String getAno() {
        return ano;
    }
    public String getCodSeguranca() {
        return codSeguranca;
    }
}
