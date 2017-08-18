package comviewzonazul.google.httpssites.zonazul.usuario.negocio;

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

public class UsuarioNegocio {

    public UsuarioDAO usuarioDAO;
    public Usuario usuario;

    public UsuarioNegocio(Context context, Usuario usuario_){
        usuarioDAO = new UsuarioDAO(context,usuario_);
        usuario = usuario_;
    }

    public UsuarioNegocio(Context context){
        usuarioDAO = new UsuarioDAO(context);
    }

    public boolean retornarUsuario(String login, String senha){
        if(usuarioDAO.existeUsuario(login,senha) == null){
            return false;
        }
        else{
            return true;
        }
    }

    public Usuario pegaUsuario(String login, String senha){
        return usuarioDAO.existeUsuario(login,senha);
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

    public int pegarId(String login){
        int id = usuarioDAO.retornarId(login);
        return id;
    }

    public void cadastro(Usuario usuario){
        usuarioDAO.salvarUsuario(usuario);
    }

    public void editar(Usuario usuario){
        usuarioDAO.editar(usuario);
    }

    public boolean verificaCliente(){
        String login = usuario.getLogin();
        int id = usuarioDAO.retornarId(login);
        usuario.set_id(id);
        if(usuarioDAO.existeCliente(id)){
            return true;
        }else{
            return false;
        }
    }

    public boolean verificaPontoVenda(){
        String login = usuario.getLogin();
        int id = usuarioDAO.retornarId(login);
        usuario.set_id(id);
        if (usuarioDAO.existePontoVenda(id)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verificaAgente(){
        String login = usuario.getLogin();
        int id = usuarioDAO.retornarId(login);
        usuario.set_id(id);
        if(usuarioDAO.existeAgente(id)){
            return true;
        }else{
            return false;
        }
    }

}

