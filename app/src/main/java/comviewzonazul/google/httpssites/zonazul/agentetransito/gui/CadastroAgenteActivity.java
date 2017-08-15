package comviewzonazul.google.httpssites.zonazul.agentetransito.gui;

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
import comviewzonazul.google.httpssites.zonazul.agentetransito.dominio.Agente;
import comviewzonazul.google.httpssites.zonazul.agentetransito.negocio.AgenteNegocio;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class CadastroAgenteActivity extends AppCompatActivity {
    EditText txt_email, txt_registro;
    Agente agente;
    Context context;
    AgenteNegocio agenteNegocio;
    String login_usuario,senha_usuario;
    int id;
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agente);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        login_usuario = preferences.getString("LOGIN", null);
        senha_usuario = preferences.getString("SENHA", null);
        context = getApplicationContext();
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_registro = (EditText) findViewById(R.id.txt_registro);
    }

    public void chamarID(){
        Usuario usuario = new Usuario(login_usuario,senha_usuario);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        id = usuarioNegocio.pegarId();
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }

    public void voltar(View view){
        startActivity(new Intent(this, EscolhaPerfilActivity.class));
        finish();
    }
}
