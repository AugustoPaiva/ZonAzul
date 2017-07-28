package comviewzonazul.google.httpssites.zonazul.infraestrutura;

/**
 * Created by user on 27/07/2017.
 */

public enum PerfisAtivos {


    CLIENTE(1),VENDEDOR(2),AGENTE(3);

    private int perfil;

    PerfisAtivos(int valor) {
        this.setPerfil(valor);
    }

    public int getPerfil(){
        return perfil;
    }

    public void setPerfil(int valor){
        this.perfil = valor;
    }


}
