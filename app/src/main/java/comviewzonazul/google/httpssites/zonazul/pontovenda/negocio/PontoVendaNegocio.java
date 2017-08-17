package comviewzonazul.google.httpssites.zonazul.pontovenda.negocio;

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.pontovenda.dao.PontoVendaDAO;
import comviewzonazul.google.httpssites.zonazul.pontovenda.dominio.PontoVenda;

public class PontoVendaNegocio {
    public PontoVendaDAO pontoVendaDAO;
    PontoVenda pontoVenda;

    public PontoVendaNegocio(Context context){
        pontoVendaDAO = new PontoVendaDAO(context);
    }

    public PontoVendaNegocio(Context context, PontoVenda pontoVenda_){
        pontoVendaDAO = new PontoVendaDAO(context, pontoVenda_);
        pontoVenda = pontoVenda_;
    }
    public void cadastro(){
        pontoVendaDAO.salvarPontoVenda();
    }
}
