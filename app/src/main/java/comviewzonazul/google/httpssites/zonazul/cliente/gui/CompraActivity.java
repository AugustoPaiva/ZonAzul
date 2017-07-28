package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
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
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;

public class CompraActivity extends AppCompatActivity {
    EditText creditotxt;
    int credito;
    Context context = getApplicationContext();
    String PREFERENCE_NAME = "CompraActivityPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        creditotxt = (EditText)findViewById(R.id.editText);
        int credito =  Integer.parseInt(creditotxt.getText().toString());
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor     = sharedPreferences.edit();
        editor.putInt("CREDITO", credito);
    }

    public void cartao(View view){
        startActivity(new Intent(this, CompraCartaoActivity.class));
        finish();
    }






}
