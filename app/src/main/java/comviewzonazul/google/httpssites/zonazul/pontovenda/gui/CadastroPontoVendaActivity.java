package comviewzonazul.google.httpssites.zonazul.pontovenda.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.pontovenda.dominio.PontoVenda;
import comviewzonazul.google.httpssites.zonazul.pontovenda.negocio.PontoVendaNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class CadastroPontoVendaActivity extends AppCompatActivity {

    private static final String PREFERENCE_NAME = "LoginActivityPreferences";
    EditText editnome_estabelecimento, editCNPJ, txt_cep, txt_numero, txt_complemeto, txt_cidade, txt_email;
    String login_usuario,senha_usuario;
    Context context;
    Endereco endereco;
    PontoVenda pontoVenda;
    PontoVendaNegocio pontoVendaNegocio;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ponto_venda);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        context = getApplicationContext();
        login_usuario = preferences.getString("LOGIN", null);
        senha_usuario = preferences.getString("SENHA", null);
        editnome_estabelecimento = (EditText) findViewById(R.id.editnome_estabelecimento);
        editCNPJ = (EditText) findViewById(R.id.editCNPJ);
        txt_cep = (EditText) findViewById(R.id.txt_cep);
        txt_numero = (EditText) findViewById(R.id.txt_numero);
        txt_complemeto = (EditText) findViewById(R.id.txt_complemento);
        txt_cidade = (EditText) findViewById(R.id.txt_cidade);
        txt_email = (EditText) findViewById(R.id.txt_email);
    }
    public void chamarID(){
        Usuario usuario = new Usuario(login_usuario,senha_usuario);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        id = usuarioNegocio.pegarId();
    }
    public void montarObjetos() {
        chamarID();
        String nome_estabelecimento = editnome_estabelecimento.getText().toString();
        String CNPJ = editCNPJ.getText().toString();
        String cep = txt_cep.getText().toString();
        String numero = txt_numero.getText().toString();
        String complemento = txt_complemeto.getText().toString();
        String cidade = txt_cidade.getText().toString();
        String email = txt_email.getText().toString();
        endereco = new Endereco(numero,complemento,cep,cidade);
        pontoVenda = new PontoVenda(nome_estabelecimento,CNPJ,email,endereco);
    }
    public void voltar(View view){
        startActivity(new Intent(this, EscolhaPerfilActivity.class));
        finish();
    }
    public void cadastroPontoVenda(View view){
        montarObjetos();
        pontoVendaNegocio = new PontoVendaNegocio(context,pontoVenda);
        pontoVendaNegocio.cadastro();
        Intent intent = new Intent();
        intent.setClass(this, EscolhaPerfilActivity.class);
        startActivity(intent);
        finish();
    }
}
