package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.CompraNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class CompraCartaoActivity extends AppCompatActivity {
    String PREFERENCE_NAME = "CompraActivityPreferences";
    String login,senha;
    Usuario usuario;
    Context context;
    int idUsuario;
    ClienteNegocio clienteNegocio;
    UsuarioNegocio usuarioNegocio;
    int credito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        credito             = preferences.getInt("CREDITO", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_cartao);
    }
    //PRECISA RETONAR OS DADOS DO CARTÃO 
    public void montarObjetos(){
        context = getApplicationContext();
        String PREFERENCE_NAME2 = "LoginActivityPreferences";
        SharedPreferences usuarios = getSharedPreferences(PREFERENCE_NAME2,MODE_PRIVATE);
        login = usuarios.getString("LOGIN",null);
        senha = usuarios.getString("SENHA",null);
        usuario = new Usuario(login,senha);
        usuarioNegocio = new UsuarioNegocio(context,usuario);
    }

    /*public void buscarCliente(){
        montarObjetos();
        idUsuario = usuarioNegocio.pegarId();
        clienteNegocio = new ClienteNegocio(context);
        Cliente cliente = clienteNegocio.retornaCliente(idUsuario);
        CompraNegocio creditar = new CompraNegocio(context,credito,cliente);
        creditar.compra(); //Aqui faz a compra

    }*/





}
