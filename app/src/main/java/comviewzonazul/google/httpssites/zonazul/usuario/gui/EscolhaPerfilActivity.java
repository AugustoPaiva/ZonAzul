package comviewzonazul.google.httpssites.zonazul.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.CadastroClienteActivity2;


public class EscolhaPerfilActivity extends AppCompatActivity {
    Button bt_sair;
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
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}