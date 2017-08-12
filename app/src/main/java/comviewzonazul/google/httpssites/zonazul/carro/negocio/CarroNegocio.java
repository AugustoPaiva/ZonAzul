package comviewzonazul.google.httpssites.zonazul.carro.negocio;

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.carro.dao.CarroDAO;
import comviewzonazul.google.httpssites.zonazul.carro.dominio.Carro;


public class CarroNegocio {
    private Context context;
    private CarroDAO carroDAO;

    public CarroNegocio(Context context){
        this.context = context;
         carroDAO = new CarroDAO(context);
    }

    public boolean existePlaca(String placa){
        if(carroDAO.retornarPlaca(placa)!= null){
            return true;
        }
        return false;
    }

    public boolean carrosIguais(Carro carro){

        Carro carroBanco = carroDAO.retornarCarro(carro.getPlaca());
        if(carro.getAno() != carroBanco.getAno() || carro.getCor() != carroBanco.getCor() ||
        carro.getFabricante() != carroBanco.getFabricante() || carro.getModelo() != carroBanco.getModelo()){
            return false;
        }
        return true;
    }
    public Carro contruirCarro(String placa){
        return carroDAO.retornarCarro(placa);
    }
    public Long adicionarCarro(Carro carro){

        Long id_carro = carroDAO.adicionarCarro(carro);
        return id_carro;
    }

    public void fazerLigacao(String idCliente, String idCarro){
        carroDAO.ligacaoCarroCliente(idCliente,idCarro);
    }
}
