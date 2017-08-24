package comviewzonazul.google.httpssites.zonazul.infraestrutura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.estacionamento.dominio.Vaga;
import comviewzonazul.google.httpssites.zonazul.estacionamento.gui.EstacionamentosActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Vaga[] ceagriVagas;
    Vaga[] cegoeVagas;
    Vaga[] centralVagas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Recife
        LatLng recife = new LatLng(-8.013986, -34.948212);
        mMap.addMarker(new MarkerOptions().position(recife).title("Área de ZonAzul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(recife, 18.0f));
        ceagriVagas = estacionamentoCEAGRI();
        cegoeVagas = estacionamentoCEGOE();
        centralVagas = estacionamentoCentral();

    }
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
        startActivity(intent);
        finish();
    }

    public Vaga[] estacionamentoCEAGRI(){
        LatLng ceagri = new LatLng(-8.018071, -34.944485);
        LatLng ceagri2 = new LatLng(-8.018104, -34.944474);
        LatLng ceagri3 = new LatLng(-8.018139, -34.944464);
        LatLng ceagri4 = new LatLng(-8.018178, -34.944453);
        LatLng ceagri5 = new LatLng(-8.018227, -34.944438);
        LatLng ceagri6 = new LatLng(-8.018049, -34.944428);
        LatLng ceagri7 = new LatLng(-8.018133, -34.944402);
        LatLng ceagri8 = new LatLng(-8.018177, -34.944386);
        LatLng ceagri9 = new LatLng(-8.018216, -34.944374);
        LatLng ceagri10 = new LatLng(-8.018254, -34.944367);
        mMap.addMarker(new MarkerOptions().position(ceagri).title("Vaga CEAGRI 1"));
        mMap.addMarker(new MarkerOptions().position(ceagri2).title("Vaga CEAGRI 2"));
        mMap.addMarker(new MarkerOptions().position(ceagri3).title("Vaga CEAGRI 3"));
        mMap.addMarker(new MarkerOptions().position(ceagri4).title("Vaga CEAGRI 4"));
        mMap.addMarker(new MarkerOptions().position(ceagri5).title("Vaga CEAGRI 5"));
        mMap.addMarker(new MarkerOptions().position(ceagri6).title("Vaga CEAGRI 6"));
        mMap.addMarker(new MarkerOptions().position(ceagri7).title("Vaga CEAGRI 7"));
        mMap.addMarker(new MarkerOptions().position(ceagri8).title("Vaga CEAGRI 8"));
        mMap.addMarker(new MarkerOptions().position(ceagri9).title("Vaga CEAGRI 9"));
        mMap.addMarker(new MarkerOptions().position(ceagri10).title("Vaga CEAGRI 10"));
        Vaga[] lista = {new Vaga(ceagri,0), new Vaga(ceagri2,0),new Vaga(ceagri3,0),new Vaga(ceagri4,0),new Vaga(ceagri5,0),new Vaga(ceagri6,0),new Vaga(ceagri7,0),new Vaga(ceagri8,0),new Vaga(ceagri9,0),new Vaga(ceagri10,0)};
        return lista;
    }

    public Vaga[] estacionamentoCEGOE(){
        LatLng cegoe = new LatLng(-8.017714, -34.949983);
        LatLng cegoe2 = new LatLng(-8.017749, -34.949942);
        LatLng cegoe3 = new LatLng(-8.017790, -34.949902);
        LatLng cegoe4 = new LatLng(-8.017826, -34.949870);
        LatLng cegoe5 = new LatLng(-8.017872, -34.949828);
        LatLng cegoe6 = new LatLng(-8.017918, -34.949786);
        LatLng cegoe7 = new LatLng(-8.017976, -34.949735);
        LatLng cegoe8 = new LatLng(-8.018027, -34.949690);
        LatLng cegoe9 = new LatLng(-8.018084, -34.949631);
        LatLng cegoe10 = new LatLng(-8.018145, -34.949578);
        mMap.addMarker(new MarkerOptions().position(cegoe).title("Vaga CEGOE 1"));
        mMap.addMarker(new MarkerOptions().position(cegoe2).title("Vaga CEGOE 2"));
        mMap.addMarker(new MarkerOptions().position(cegoe3).title("Vaga CEGOE 3"));
        mMap.addMarker(new MarkerOptions().position(cegoe4).title("Vaga CEGOE 4"));
        mMap.addMarker(new MarkerOptions().position(cegoe5).title("Vaga CEGOE 5"));
        mMap.addMarker(new MarkerOptions().position(cegoe6).title("Vaga CEGOE 6"));
        mMap.addMarker(new MarkerOptions().position(cegoe7).title("Vaga CEGOE 7"));
        mMap.addMarker(new MarkerOptions().position(cegoe8).title("Vaga CEGOE 8"));
        mMap.addMarker(new MarkerOptions().position(cegoe9).title("Vaga CEGOE 9"));
        mMap.addMarker(new MarkerOptions().position(cegoe10).title("Vaga CEGOE 10"));
        Vaga[] lista = {new Vaga(cegoe,0), new Vaga(cegoe2,0),new Vaga(cegoe3,0),new Vaga(cegoe4,0),new Vaga(cegoe5,0),new Vaga(cegoe6,0),new Vaga(cegoe7,0),new Vaga(cegoe8,0),new Vaga(cegoe9,0),new Vaga(cegoe10,0)};
        return lista;
    }

    public Vaga[] estacionamentoCentral(){
        LatLng central = new LatLng(-8.013484, -34.950579);
        LatLng central2 = new LatLng(-8.013551, -34.950552);
        LatLng central3 = new LatLng(-8.013511, -34.950620);
        LatLng central4 = new LatLng(-8.013577, -34.950601);
        LatLng central5 = new LatLng(-8.013560, -34.950695);
        LatLng central6 = new LatLng(-8.013626, -34.950674);
        LatLng central7 = new LatLng(-8.013586, -34.950735);
        LatLng central8 = new LatLng(-8.013646, -34.950703);
        LatLng central9 = new LatLng(-8.013612, -34.950778);
        LatLng central10 = new LatLng(-8.013669, -34.950740);
        mMap.addMarker(new MarkerOptions().position(central).title("Vaga central 1"));
        mMap.addMarker(new MarkerOptions().position(central2).title("Vaga central 2"));
        mMap.addMarker(new MarkerOptions().position(central3).title("Vaga central 3"));
        mMap.addMarker(new MarkerOptions().position(central4).title("Vaga central 4"));
        mMap.addMarker(new MarkerOptions().position(central5).title("Vaga central 5"));
        mMap.addMarker(new MarkerOptions().position(central6).title("Vaga central 6"));
        mMap.addMarker(new MarkerOptions().position(central7).title("Vaga central 7"));
        mMap.addMarker(new MarkerOptions().position(central8).title("Vaga central 8"));
        mMap.addMarker(new MarkerOptions().position(central9).title("Vaga central 9"));
        mMap.addMarker(new MarkerOptions().position(central10).title("Vaga central 10"));
        Vaga[] lista = {new Vaga(central,0), new Vaga(central2,0),new Vaga(central3,0),new Vaga(central4,0),new Vaga(central5,0),new Vaga(central6,0),new Vaga(central7,0),new Vaga(central8,0),new Vaga(central9,0),new Vaga(central10,0)};
        return lista;
    }


}
