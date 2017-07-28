package comviewzonazul.google.httpssites.zonazul.usuario.negocio;

import android.content.ContentValues;
import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

/**
 * Created by Augusto on 22/07/17.
 */

public class UsuarioNegocio {

    public UsuarioDAO usuarioDAO;
    public Usuario usuario;

    public UsuarioNegocio(Context context,Usuario usuario_){
        usuarioDAO = new UsuarioDAO(context,usuario_);
        usuario = usuario_;
    }

    public boolean retornarUsuario(String login, String senha){ // FAZER COM QUE ESSA FUNÇÃO RETORNE TUDO INCLUSIVE O ID
        if(usuarioDAO.existeUsuario(login,senha) == null){ //nao existe usuario
            return false;
        }
        else{
            return true;
        }
    }


    public boolean retornarUsuarioLogin(Usuario usuario){
        String login = usuario.getLogin();
        Boolean retorno = usuarioDAO.buscarUsuarioPorLogin(login);
        return retorno;
    }

    public int pegarId(){
        int id = usuarioDAO.retornarId(usuario.getLogin());
        return id;
    }

    public void cadastro(Usuario usuario){
        usuarioDAO.salvarUsuario(usuario);
    }

    public boolean verificaCliente(){
        usuario.set_id(usuarioDAO.retornarId(usuario.getLogin()));
        if(usuarioDAO.existeCliente(usuario.get_id())){
            return true;
        }else{
            return false;
        }
    }
}

