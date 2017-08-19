package comviewzonazul.google.httpssites.zonazul.agentetransito.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.agentetransito.dominio.Agente;
import comviewzonazul.google.httpssites.zonazul.agentetransito.negocio.AgenteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;
import util.Mask;
import util.Mensagem;

public class CadastroAgenteActivity extends AppCompatActivity {
    EditText txt_email, txt_registro, txt_municipio;
    Agente agente;
    Context context;
    AgenteNegocio agenteNegocio;
    String login_usuario,senha_usuario,email;
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
        txt_registro.addTextChangedListener(Mask.insert(Mask.Agent_Mask, txt_registro));
        txt_municipio = (EditText) findViewById(R.id.txt_municipio);
    }

    public void chamarID(){
        Usuario usuario = new Usuario(login_usuario,senha_usuario);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        id = usuarioNegocio.pegarId();
    }

    public boolean validacoes(){
        if (cadastroAgente()){
            return true;
        }else{
            return false;
        }
    }

    public void montarObjetos(){
        chamarID();
        String email = txt_email.getText().toString();
        String municipio = txt_municipio.getText().toString();
        String registro = txt_registro.getText().toString();
        agente = new Agente(id, email, registro, municipio);
    }

    public boolean cadastroAgente() {
        montarObjetos();
        agenteNegocio = new AgenteNegocio(context, agente);
        if ((!agenteNegocio.retornarAgenteEmail())) {
            agenteNegocio.cadastro();
            return true;
        }else{
            return false;
        }
    }

    public void cadastro(View view){
        if (validacoes()){
            cadastroAgente();
            Intent intent = new Intent();
            intent.setClass(this, EscolhaPerfilActivity.class);
            startActivity(intent);
            Mensagem.Msg(this, getString(R.string.msg_AgCadastrado));
            finish();
        }
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
