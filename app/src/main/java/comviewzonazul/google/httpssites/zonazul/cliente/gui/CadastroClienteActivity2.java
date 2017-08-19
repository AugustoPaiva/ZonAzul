package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;
import util.Mask;
import util.Mensagem;

public class CadastroClienteActivity2 extends AppCompatActivity {
    EditText txt_email, txt_cep, txt_numero, txt_complemeto, txt_cidade;
    Endereco endereco;
    ClienteNegocio clienteNegocio;
    Cliente cliente;
    Context context;
    String login_usuario,senha_usuario,email;
    int id;
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente2);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        login_usuario = preferences.getString("LOGIN", null);
        senha_usuario = preferences.getString("SENHA", null);
        context = getApplicationContext();
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_cep = (EditText) findViewById(R.id.txt_cep);
        txt_cep.addTextChangedListener(Mask.insert(Mask.CEP_MASK, txt_cep));
        txt_numero = (EditText) findViewById(R.id.txt_numero);
        txt_complemeto = (EditText) findViewById(R.id.txt_complemento);
        txt_cidade = (EditText) findViewById(R.id.txt_cidade);
    }

    public void chamarID(){
        Usuario usuario = new Usuario(login_usuario,senha_usuario);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        id = usuarioNegocio.pegarId();
    }

    public boolean validacoes(){
        if (cadastroCliente()){
            return true;
        }else{
            return false;
        }
    }

    public boolean cadastroCliente() {
        montarObjetos();
        clienteNegocio = new ClienteNegocio(context, cliente);
        if ((!clienteNegocio.retornarClienteEmail())) {
            clienteNegocio.cadastro();
            return true;
        }else{
            return false;
        }
    }

    public void montarObjetos(){
        chamarID();
        String email = txt_email.getText().toString();
        String cep = txt_cep.getText().toString();
        String numero = txt_numero.getText().toString();
        String complemento = txt_complemeto.getText().toString();
        String cidade = txt_cidade.getText().toString();
        endereco = new Endereco(numero,complemento,cep,cidade);
        cliente = new Cliente(email,endereco,id);
    }

    public void cadastro(View view){
        if (validacoes()){
            cadastroCliente();
            Intent intent = new Intent();
            intent.setClass(this, EscolhaPerfilActivity.class);
            startActivity(intent);
            Mensagem.Msg(this, getString(R.string.msg_Cadastrado));
            finish();
        }
    }

    public void voltar(View view){
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
}

