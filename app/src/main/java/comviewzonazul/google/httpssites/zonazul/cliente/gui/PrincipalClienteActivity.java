 package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

 public class PrincipalClienteActivity extends AppCompatActivity {
     private static final String MANTER_CONECTADO = "manter_conectado";
     private static final String PREFERENCE_NAME = "LoginActivityPreferences";
     Usuario usuario = new Usuario();
     Usuario user = new Usuario();
     Cliente cliente = new Cliente();
     TextView nomeUser, saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        usuario.setLogin(preferences.getString("LOGIN", null));
        usuario.setSenha(preferences.getString("SENHA", null));
        encontraCliente();
        //encontrarItens();
        //editarItens();
        setContentView(R.layout.activity_principal_cliente);
    }

    public void encontraCliente(){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext(), usuario);
        usuario = usuarioNegocio.pegaUsuario(usuario.getLogin(), usuario.getSenha());
        ClienteNegocio clientenegocio = new ClienteNegocio(getApplicationContext());
        cliente = clientenegocio.retornaCliente(usuario.get_id());
    }

    public void editarItens(){
        nomeUser.setText(user.getNome()+"");
        saldo.setText(cliente.getSaldo()+"");
    }

     public void encontrarItens() {
         nomeUser = (TextView)findViewById(R.id.textViewNome);
         saldo = (TextView)findViewById(R.id.textViewSaldo);
     }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }
}



