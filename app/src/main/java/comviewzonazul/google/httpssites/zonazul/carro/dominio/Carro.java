package comviewzonazul.google.httpssites.zonazul.carro.dominio;

public class Carro {
    String fabricante,modelo,cor,ano,placa;
    int id;

    public Carro(){
    }

    public Carro(int id_, String fabricante_,String modelo_, String cor_, String ano_, String placa_){
        id = id_;
        fabricante = fabricante_;
        modelo = modelo_;
        cor = cor_;
        ano = ano_;
        placa = placa_;
    }

    public Carro(String fabricante_,String modelo_, String cor_, String ano_, String placa_){
        fabricante = fabricante_;
        modelo = modelo_;
        cor = cor_;
        ano = ano_;
        placa = placa_;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
