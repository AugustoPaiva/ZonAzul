package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.carro.gui.GerenciarCarroActivity;

public class ConfiguracaoClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_cliente);
    }

    public void editar(View view){
        Intent intent = new Intent();
        intent.setClass(this, EditarClienteActivity.class);
        startActivity(intent);
        finish();
    }

    public void gerenciar(View view){
        Intent intent = new Intent();
        intent.setClass(this, GerenciarCarroActivity.class);
        startActivity(intent);
        finish();

    }

    public void extrato(View view){
        Intent intent = new Intent();
        intent.setClass(this, ExtratoActivity.class);
        startActivity(intent);
        finish();
    }
}
