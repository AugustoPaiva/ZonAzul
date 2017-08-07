package comviewzonazul.google.httpssites.zonazul.compra.negocio;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import java.util.Date;

import comviewzonazul.google.httpssites.zonazul.compra.dao.CompraDAO;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;

public class CompraNegocio {
    double antigoSaldo;
    int idCliente;
    double credito;
    Cliente cliente;
    CompraDAO compraDAO;

    public CompraNegocio(Context context, double credito_, Cliente cliente_){
        compraDAO = new CompraDAO(context);
        idCliente = cliente_.getId();
        credito = credito_;
        cliente = cliente_;
    }

    public String data(){
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        String txtDate = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(currentDate);
        return txtDate;
    }
    public void compra(){
        antigoSaldo = cliente.getSaldo();
        compraDAO.atualizarSaldo(cliente,credito,antigoSaldo);
        adicionarHistorico();
    }
    public void adicionarHistorico(){
        compraDAO.atualizarBanco(cliente, credito, this.data());
    }
}
