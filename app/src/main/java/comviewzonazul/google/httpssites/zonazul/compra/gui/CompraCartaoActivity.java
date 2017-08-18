package comviewzonazul.google.httpssites.zonazul.compra.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.compra.dominio.Cartao;
import comviewzonazul.google.httpssites.zonazul.compra.negocio.CompraNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class CompraCartaoActivity extends AppCompatActivity {
    String PREFERENCE_NAME = "CompraActivityPreferences";
    String PREFERENCE_NAME2 = "LoginActivityPreferences";
    String login, senha, nomeTitular,numeroCartao, mes, ano, codSeguranca;
    EditText txt_nomeTitular, txt_numero, txt_mes, txt_ano, txt_codSeguranca;
    Usuario usuario;
    Context context;
    Cartao cartao;
    Cliente cliente;
    int idUsuario;
    ClienteNegocio clienteNegocio;
    UsuarioNegocio usuarioNegocio;
    int credito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        credito = preferences.getInt("credito", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_cartao);
    }

    public void findItens(){
        txt_nomeTitular = (EditText)findViewById(R.id.txt_nome_titular);
        txt_numero = (EditText)findViewById(R.id.txt_numero_cartao);
        txt_mes = (EditText)findViewById(R.id.txt_mes);
        txt_ano = (EditText)findViewById(R.id.txt_ano);
        txt_codSeguranca = (EditText)findViewById(R.id.txt_codSegurança);
    }

    public void findCliente(){ //
        montarObjetos();
        idUsuario = usuarioNegocio.pegarId();
        ClienteNegocio clienteNegocio = new ClienteNegocio(context);
        cliente = clienteNegocio.retornaCliente(idUsuario);
    }

    public void informacoes(){
        SharedPreferences usuarios = getSharedPreferences(PREFERENCE_NAME2,MODE_PRIVATE);
        login = usuarios.getString("LOGIN",null);
        senha = usuarios.getString("SENHA",null);
        nomeTitular = txt_nomeTitular.getText().toString();
        numeroCartao = txt_numero.getText().toString();
        mes = txt_mes.getText().toString();
        ano = txt_ano.getText().toString();
        codSeguranca = txt_codSeguranca.getText().toString();
    }

    public void montarObjetos(){
        cartao = new Cartao(nomeTitular,numeroCartao,mes,ano,codSeguranca);
        context = getApplicationContext();
        usuario = new Usuario(login,senha);
        usuarioNegocio = new UsuarioNegocio(context,usuario);
    }

    public void confirmar(View view){
        findItens();
        informacoes();
        montarObjetos();
        findCliente();
        CompraNegocio compra = new CompraNegocio(context,credito,cliente);
        compra.compra("Credito");
        Toast.makeText(context,"Compra confirmada", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, PrincipalClienteActivity.class));
        finish();
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, CompraActivity.class);
        startActivity(intent);
        finish();
    }

    public void voltar(View view){
        Intent intent = new Intent();
        intent.setClass(this, CompraActivity.class);
        startActivity(intent);
        finish();
    }
}
