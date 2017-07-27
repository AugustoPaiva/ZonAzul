package comviewzonazul.google.httpssites.zonazul.usuario.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.CadastroClienteActivity2;

public class EscolhaPerfilActivity extends AppCompatActivity {
    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_perfil);
    }

    public void cliente(View view){ //LEMBRAR DE COLOCAR UMA VERIFICAÇÃO DE SE JA TEM UM CLIENTE CADASTRADO.

        Intent intent = new Intent();
        intent.setClass(this, CadastroClienteActivity2.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor     = sharedPreferences.edit();
        editor.putBoolean(MANTER_CONECTADO, false);
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        editor.clear();
        editor.commit();
        startActivity(intent);
        finish();
    }


}