 package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.MapsActivity;
import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.carro.gui.GerenciarCarroActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.compra.gui.CompraActivity;
import comviewzonazul.google.httpssites.zonazul.estacionamento.gui.Estacionar;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.LoginActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

 public class PrincipalClienteActivity extends AppCompatActivity {

     private static final String PREFERENCE_NAME = "LoginActivityPreferences";
     Usuario usuario = new Usuario();
     Usuario user = new Usuario();
     Cliente cliente = new Cliente();
     TextView nomeUser, saldoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_cliente);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        usuario.setLogin(preferences.getString("LOGIN", null));
        usuario.setSenha(preferences.getString("SENHA", null));
        encontraCliente();
        encontrarItens();
        editarItens();
    }

    public void encontraCliente(){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext(), usuario);
        user = usuarioNegocio.pegaUsuario(usuario.getLogin(), usuario.getSenha());
        Context context = getApplicationContext();
        ClienteNegocio clientenegocio = new ClienteNegocio(getApplicationContext());
        cliente = clientenegocio.retornaCliente(user.get_id());
    }

    public void editarItens(){
        nomeUser.setText(user.getNome().toUpperCase()+"");
        saldoUsuario.setText("R$" +cliente.getSaldo() +"");
    }

     public void encontrarItens() {
         nomeUser = (TextView) findViewById(R.id.textViewNome);
         saldoUsuario = (TextView)findViewById(R.id.textViewSaldo);
     }

     public void comprar(View view){
         Intent intent = new Intent();
         intent.setClass(this, CompraActivity.class);
         startActivity(intent);
         finish();
     }

     public void extrato(View view){
         Intent intent = new Intent();
         intent.setClass(this, ExtratoActivity.class);
         startActivity(intent);
         finish();
     }

     public void gerenciar(View view){
         Intent intent = new Intent();
         intent.setClass(this, GerenciarCarroActivity.class);
         startActivity(intent);
         finish();

     }
     public void estacionar(View view){
         Intent intent = new Intent();
         intent.setClass(this, Estacionar.class);
         startActivity(intent);
         finish();
     }

    public void logout(View view){
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }

     public void editar(View view){
         Intent intent = new Intent();
         intent.setClass(this, EditarClienteActivity.class);
         startActivity(intent);
         finish();
     }
     public void pontos_de_venda(View view){
         Intent intent = new Intent();
         intent.setClass(this, MapsActivity.class);
         startActivity(intent);
         finish();
     }
}



