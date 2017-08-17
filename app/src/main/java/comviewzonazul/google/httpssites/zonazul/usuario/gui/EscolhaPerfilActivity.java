package comviewzonazul.google.httpssites.zonazul.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.agentetransito.gui.CadastroAgenteActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.CadastroClienteActivity2;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.pontovenda.gui.CadastroPontoVendaActivity;
import comviewzonazul.google.httpssites.zonazul.pontovenda.gui.PontoVendaActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class EscolhaPerfilActivity extends AppCompatActivity {
    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";
    private Usuario usuario = new Usuario() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_perfil);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        usuario.setLogin(preferences.getString("LOGIN", null));
        usuario.setSenha(preferences.getString("SENHA", null));
    }

    public void cliente(View view){
        Context context = getApplicationContext();
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        if ((usuarioNegocio.verificaCliente())){
            Intent intent = new Intent();
            intent.setClass(this, PrincipalClienteActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent();
            intent.setClass(this, CadastroClienteActivity2.class);
            startActivity(intent);
            finish();
        }
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

    public void agentetransito(View view){
        Intent intent = new Intent();
        intent.setClass(this, CadastroAgenteActivity.class);
        startActivity(intent);
        finish();
    }

    public void pontovenda(View view){
        Intent intent = new Intent();
        intent.setClass(this, CadastroPontoVendaActivity.class);
        startActivity(intent);
        finish();
    }

    public void ponto_venda(View view){
        Context context = getApplicationContext();
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context, usuario);
        if ((usuarioNegocio.verificaPontoVenda())){
            Intent intent = new Intent();
            intent.setClass(this, PontoVendaActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent();
            intent.setClass(this, CadastroPontoVendaActivity.class);
            startActivity(intent);
            finish();
        }
    }

}