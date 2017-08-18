package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import comviewzonazul.google.httpssites.zonazul.R;

public class EscolhaExtrato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_extrato);
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, ConfiguracaoClienteActivity.class);
        startActivity(intent);
        finish();
    }

    public void voltar(View view){
        Intent intent = new Intent();
        intent.setClass(this, ConfiguracaoClienteActivity.class);
        startActivity(intent);
        finish();
    }
}
