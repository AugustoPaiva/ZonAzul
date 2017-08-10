package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class perfil_cliente extends AppCompatActivity {
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";
    Usuario usuario = new Usuario();
    Usuario user = new Usuario();
    Cliente cliente = new Cliente();
    TextView nomeUser, nomeEmail, nomeCep, nomeComplemento, nomeCidade, nomeNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);
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
        ClienteNegocio clientenegocio = new ClienteNegocio(getApplicationContext());
        cliente = clientenegocio.retornaCliente(user.get_id());
    }
    public void encontrarItens() {
        nomeUser = (TextView) findViewById(R.id.textViewNome);
        nomeCep = (TextView) findViewById(R.id.textViewCep);
        nomeCidade = (TextView) findViewById(R.id.textViewCidade);
        nomeComplemento = (TextView) findViewById(R.id.textViewComplemento);
        nomeNumero = (TextView) findViewById(R.id.textViewNumero);
        nomeEmail = (TextView) findViewById(R.id.textViewEmail);

    }
    public void editarItens(){
        nomeUser.setText(user.getNome().toUpperCase()+"");
        nomeCidade.setText(cliente.getEndereco().cidade);
        nomeCep.setText(cliente.getEndereco().cep);
        nomeComplemento.setText(cliente.getEndereco().complemento);
        nomeNumero.setText(cliente.getEndereco().numero);
        nomeEmail.setText(cliente.getEmail());
    }
}
