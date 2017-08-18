package comviewzonazul.google.httpssites.zonazul.cliente.negocio;

import android.content.Context;
import android.graphics.Bitmap;

import comviewzonazul.google.httpssites.zonazul.cliente.dao.ClienteDAO;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.BitmapUtility;

public class ClienteNegocio {

    public ClienteDAO clienteDAO;
    Cliente cliente;

    public ClienteNegocio(Context context){
        clienteDAO = new ClienteDAO(context);
    }

    public ClienteNegocio(Context context, Cliente cliente_){
        clienteDAO = new ClienteDAO(context,cliente_);
        cliente = cliente_;
    }
    public Bitmap pegarImagem(int id){
        BitmapUtility bitImage = new BitmapUtility();
        byte[] imagem = clienteDAO.retonarByte(id);
        if(imagem == null){
            return null;
        }
        Bitmap imagemBitmap = bitImage.getImage(imagem);
        return imagemBitmap;
    }
    public boolean retornarClienteEmail(){
        return clienteDAO.buscarClienteEmail();
    }

    public Cliente retornaCliente(int idUsuario){
        return clienteDAO.buscarClientePorUsuario(idUsuario);
    }

    public void cadastro(){
        clienteDAO.salvarCliente();
    }

    public void editar(Cliente cliente, byte[] image){
        clienteDAO.editar(cliente,image);
    }


}
