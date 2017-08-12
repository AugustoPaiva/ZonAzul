package comviewzonazul.google.httpssites.zonazul.compra.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;

public class CompraActivity extends AppCompatActivity {
    EditText creditotxt;
    int credito;
    Context context;
    String PREFERENCE_NAME = "CompraActivityPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        context = getApplicationContext();
        creditotxt = (EditText)findViewById(R.id.editText);
    }

    public boolean verificacao(){
        return true; //ADIONAR AS VERIFICAÇÕES AQUI
    }

    public void editarItens(){
        int credito =  Integer.parseInt(creditotxt.getText().toString());
        SharedPreferences sharedPreferences = context.getSharedPreferences("CompraActivityPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor     = sharedPreferences.edit();
        editor.putInt("credito", credito);
        editor.commit();
        int cliente  = sharedPreferences.getInt("credito", 0);

    }
    public void cartao(View view){
        if(verificacao()){
            editarItens();
            startActivity(new Intent(this, CompraCartaoActivity.class));
            finish();
        }
    }

    public void voltar(View view){
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
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
