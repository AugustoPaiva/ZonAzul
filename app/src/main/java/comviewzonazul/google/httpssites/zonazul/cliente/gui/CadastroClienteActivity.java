package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import util.Mensagem;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Augusto on 23/07/17.
 */

public class CadastroClienteActivity {

    EditText txt_email, txt_cep, txt_numero, txt_complemeto, txt_cidade;
    Endereco endereco;
    ClienteNegocio clienteNegocio;
    Cliente cliente;
    Context context;
    String login_usuario,senha_usuario;
    int id;
    public class cadastroCliente extends AppCompatActivity {
        private static final String PREFERENCE_NAME = "LoginActivityPreferences";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro_cliente);

            SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
            login_usuario = preferences.getString("LOGIN", null);
            senha_usuario = preferences.getString("SENHA", null);


            context = getApplicationContext();
            txt_email = (EditText) findViewById(R.id.txt_email);
            txt_cep = (EditText) findViewById(R.id.txt_cep);
            txt_numero = (EditText) findViewById(R.id.txt_numero);
            txt_complemeto = (EditText) findViewById(R.id.txt_complemento);
            txt_cidade = (EditText) findViewById(R.id.txt_cidade);
        }
    }

    public void chamarID(){
        Usuario usuario = new Usuario(login_usuario,senha_usuario);
        UsuarioDAO usuarioDAO = new UsuarioDAO(context,usuario);
        id = usuarioDAO.retornarId(login_usuario);
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

    public void validacoes(){
        cadastroCliente();
    }

    public void cadastroCliente(){
        montarObjetos();
        clienteNegocio = new ClienteNegocio(context,cliente);
        if((clienteNegocio.retornarClienteEmail())){
            clienteNegocio.cadastro();
        }
    }
}
