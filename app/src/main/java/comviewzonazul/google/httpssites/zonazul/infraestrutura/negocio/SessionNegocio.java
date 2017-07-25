package comviewzonazul.google.httpssites.zonazul.infraestrutura.negocio;
/*
import comviewzonazul.google.httpssites.zonazul.infraestrutura.dao.SessionDAO;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.dominio.Session;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;


 Created by root on 24/07/17.




public class SessionNegocio {

    public Usuario retornarUsuarioLogado(){
        Usuario usuario;
        SessionDAO sessionDAO = new SessionDAO();
        int id = sessionDAO.idLogado();
        if(id==0) {
            usuario = null;
        }else{
            UsuarioDAO UsuarioDAO = new UsuarioDAO();
            usuario =UsuarioDAO.retornarUsuario(id);
        }
        return usuario;
    }

    public void cadastrarUsuarioLogado(Usuario usuario){
        int id = usuario.getId();
        SessionDAO sessionDAO = new SessionDAO();
        sessionDAO.cadastrarIdLogado(id);
    }

    public void deslogarUsuario(){
        SessionDAO sessionDAO = new SessionDAO();
        sessionDAO.excluirUsuarioLogado(Session.getUsuario().get_id()));
    }
*/