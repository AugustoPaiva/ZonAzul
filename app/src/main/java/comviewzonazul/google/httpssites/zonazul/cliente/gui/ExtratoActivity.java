package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import comviewzonazul.google.httpssites.zonazul.R;

public class ExtratoActivity extends AppCompatActivity {
    private CheckBox ckbhistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
    }

    public void historico(View view){
        startActivity(new Intent(this, ListarExtrato.class));
        //finish();
    }

    public void voltar(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ConfiguracaoClienteActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
        startActivity(intent);
        finish();
    }
}
