package comviewzonazul.google.httpssites.zonazul.agentetransito.dominio;

public class Agente {
    public int id;
    public String registro;
    public int agente_id;
    public String municipio;
    public String email;

    public Agente(int id_, int agente_id_, String registro_, String municipio_, String email_) {
        id = id_;
        agente_id = agente_id_;
        registro = registro_;
        municipio = municipio_;
        email = email_;
    }

    public Agente(int id_, String email_, String registro_, String municipio_){
        id = id_;
        email = email_;
        registro = registro_;
        municipio = municipio_;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getAgente_id() {
        return agente_id;
    }

    public void setAgente_id(int agente_id) {
        this.agente_id = agente_id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}