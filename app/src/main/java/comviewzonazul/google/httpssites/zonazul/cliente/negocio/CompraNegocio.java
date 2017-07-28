package comviewzonazul.google.httpssites.zonazul.cliente.negocio;

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.cliente.dao.CompraDAO;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;

/**
 * Created by root on 27/07/17.
 */

public class CompraNegocio {
    long antigoSaldo;
    int idCliente;
    long credito;

    CompraDAO compraDAO;

    public CompraNegocio(Context context, long credito_, Cliente cliente){
        compraDAO = new CompraDAO(context);
        idCliente = cliente.getId();
        credito = credito_;
    }
    public void compra(){
        antigoSaldo = compraDAO.buscarAntigoSaldo(idCliente);
        compraDAO.atualizarSaldo(credito,antigoSaldo);

    }
}
