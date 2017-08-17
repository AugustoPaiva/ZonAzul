package comviewzonazul.google.httpssites.zonazul.pontovenda.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.LoginActivity;

public class PontoVendaActivity extends AppCompatActivity {

    EditText edit_cliente, edit_valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto_venda);
        edit_cliente = (EditText) findViewById(R.id.edit_cliente);
        edit_valor = (EditText) findViewById(R.id.edit_valor);
    }
    public void logout(View view){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
