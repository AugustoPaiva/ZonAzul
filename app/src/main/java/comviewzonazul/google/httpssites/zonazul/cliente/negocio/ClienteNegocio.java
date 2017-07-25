package comviewzonazul.google.httpssites.zonazul.cliente.negocio;

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.cliente.dao.ClienteDAO;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;

/**
 * Created by Augusto on 24/07/17.
 */

public class ClienteNegocio {
    public ClienteDAO clienteDAO;
    public ClienteNegocio(Context context, Cliente cliente){
        clienteDAO = new ClienteDAO(context,cliente);
    }
    public boolean retornarClienteEmail(){
        return clienteDAO.buscarClienteEmail();
    }
    public void cadastro(){
        clienteDAO.salvarCliente();
    }







}
