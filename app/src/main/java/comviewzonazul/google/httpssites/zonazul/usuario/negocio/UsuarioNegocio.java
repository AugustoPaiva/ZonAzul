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
    public UsuarioNegocio(Context context,Usuario usuario){
        usuarioDAO = new UsuarioDAO(context,usuario);

    }

    public boolean retornarUsuario(String login, String senha){
        if(usuarioDAO.existeUsuario(login,senha) == null){ //nao existe usuario
            return false;
        }
        else{return true;}


    }
    public boolean retornarUsuarioLogin(Usuario usuario){
        String login = usuario.getLogin();
        Boolean retorno = usuarioDAO.buscarUsuarioPorLogin(login);
        return retorno;

    }

    public void cadastro(Usuario usuario){
        usuarioDAO.salvarUsuario(usuario);
    }
}
