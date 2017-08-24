package comviewzonazul.google.httpssites.zonazul.estacionamento.dominio;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by root on 23/08/17.
 */

public class Vaga {
    private LatLng localização;
    private int disponivel;

    public Vaga(LatLng localização, int disponivel){
        this.localização = localização;
        this.disponivel = disponivel;
    }

    public LatLng getLocalização() {
        return localização;
    }

    public void setLocalização(LatLng localização) {
        this.localização = localização;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }
}
