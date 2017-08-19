package comviewzonazul.google.httpssites.zonazul.agentetransito.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.estacionamento.gui.EstacionamentosActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;

public class PrincipalAgenteActivity extends AppCompatActivity {
    EditText txt_placa;
    String placa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_agente);

    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }

    public void pesquisar(View view){
        txt_placa = (EditText)findViewById(R.id.plac);
        placa = txt_placa.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("PLACA", MODE_PRIVATE);
        SharedPreferences.Editor editor     = sharedPreferences.edit();
        editor.putString("PLACA",placa);
        startActivity(new Intent(this, EstacionamentosActivity.class));
        finish();


    }

    public void voltar(View view){
        startActivity(new Intent(this, EscolhaPerfilActivity.class));
        finish();
    }
}


