package comviewzonazul.google.httpssites.zonazul.pontovenda.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.compra.negocio.CompraNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.LoginActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class PontoVendaActivity extends AppCompatActivity {

    EditText edit_login, edit_valor;
    String login;
    int valor;
    int id_usuario;
    Cliente cliente;
    ClienteNegocio clienteNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto_venda);
        edit_login = (EditText) findViewById(R.id.edit_cliente);
        edit_valor = (EditText) findViewById(R.id.edit_valor);

    }
    public void findIntens(){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext());
        clienteNegocio = new ClienteNegocio(getApplicationContext());
        login = edit_login.getText().toString();
        id_usuario = usuarioNegocio.pegarId(login);
        valor = Integer.parseInt(edit_valor.getText().toString());
        cliente = clienteNegocio.retornaCliente(id_usuario);

    }
    public boolean verificacao(){
        return true;
    }

    public void vender(View view){
        findIntens();
        if(verificacao()){
            CompraNegocio compra = new CompraNegocio(getApplicationContext(),valor,cliente);
            compra.compra("Ponto fisico");
            Toast.makeText(getApplicationContext(),"Compra confirmada", Toast.LENGTH_LONG).show();

        }
    }
    public void logout(View view){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
