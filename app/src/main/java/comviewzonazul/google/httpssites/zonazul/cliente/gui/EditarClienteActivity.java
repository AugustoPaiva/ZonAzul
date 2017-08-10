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
import comviewzonazul.google.httpssites.zonazul.usuario.gui.LoginActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;
import util.Mensagem;

public class EditarClienteActivity extends AppCompatActivity {

    private static final String PREFERENCE_NAME = "LoginActivityPreferences";
    Usuario usuario = new Usuario();
    Endereco endereco;
    Cliente cliente;
    EditText editnome, editcidade, editcomplemento, editnumero, editcep, editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        usuario.setLogin(preferences.getString("LOGIN", null));
        usuario.setSenha(preferences.getString("SENHA", null));
        editnome = (EditText) findViewById(R.id.editnome);
        editcidade = (EditText) findViewById(R.id.editcidade);
        editcomplemento = (EditText) findViewById(R.id.editcomplemento);
        editnumero = (EditText) findViewById(R.id.editnumero);
        editcep = (EditText) findViewById(R.id.editcep);
        editemail = (EditText) findViewById(R.id.editemail);
    }

    public void alterarcadastro(View view){
        Context context = getApplicationContext();
        String editarnome = editnome.getText().toString();
        usuario.setNome(editarnome);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);
        usuarioNegocio.editar(usuario);
        String editaremail = editemail.getText().toString();
        String editarcep = editcep.getText().toString();
        String editarnumero = editnumero.getText().toString();
        String editarcomplemento = editcomplemento.getText().toString();
        String editarcidade = editcidade.getText().toString();
        endereco = new Endereco(editarnumero,editarcomplemento,editarcep,editarcidade);
        cliente = new Cliente(editaremail,endereco,usuario.get_id());
        ClienteNegocio clienteNegocio = new ClienteNegocio(context, cliente);
        clienteNegocio.editar(cliente);
        startActivity(new Intent(this, PrincipalClienteActivity.class));
        Mensagem.Msg(this, getString(R.string.msg_Editado));
        finish();

    }

    public void voltar(View view){
        startActivity(new Intent(this, PrincipalClienteActivity.class));
        finish();
    }

    public void onBackPressed(){
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
        startActivity(intent);
        finish();
    }
}
