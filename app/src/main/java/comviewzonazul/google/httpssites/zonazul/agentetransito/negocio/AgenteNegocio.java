package comviewzonazul.google.httpssites.zonazul.agentetransito.negocio;

import android.content.Context;
import comviewzonazul.google.httpssites.zonazul.agentetransito.dao.AgenteDAO;
import comviewzonazul.google.httpssites.zonazul.agentetransito.dominio.Agente;

public class AgenteNegocio {

    public AgenteDAO agenteDAO;
    Agente agente;

    public AgenteNegocio(Context context){
        agenteDAO = new AgenteDAO(context);
    }

    public AgenteNegocio(Context context, Agente agente_){
        agenteDAO = new AgenteDAO(context, agente_);
        agente = agente_;
    }

    public boolean retornarAgenteMunicipio(){
        return agenteDAO.buscarAgenteMunicipio();
    }

    public boolean retornarAgenteEmail(){
        return agenteDAO.buscarAgenteEmail();
    }

    public Agente retornarAgente(int idUsuario){
        return agenteDAO.BuscarAgentePorUsuario(idUsuario);
    }

    public void cadastro(){
        agenteDAO.salvarAgente();
    }

    public void editar(Agente agente){
        agenteDAO.editar(agente);
    }

}


