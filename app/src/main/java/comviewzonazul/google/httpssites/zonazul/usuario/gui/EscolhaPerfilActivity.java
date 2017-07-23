package comviewzonazul.google.httpssites.zonazul.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.CadastroClienteActivity;

public class EscolhaPerfilActivity extends AppCompatActivity {
    Button btCliente, btSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_perfil);
    }

    public void findItens(){
        btCliente = (Button)findViewById(R.id.bt_cliente);
        btSair = (Button)findViewById(R.id.bt_sair);
    }
    public void cliente(){ //LEMBRAR DE COLOCAR UMA VERIFICAÇÃO DE SE JA TEM UM CLIENTE CADASTRADO.
        Intent it = new Intent(EscolhaPerfilActivity.this, CadastroClienteActivity.class);
        startActivity(it);

    }
    public void logout(){
        Intent it = new Intent(EscolhaPerfilActivity.this, LoginActivity.class);
        startActivity(it);

    }
}
