package comviewzonazul.google.httpssites.zonazul.usuario.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;
import util.Mensagem;

public class CadUsuarioActivity extends AppCompatActivity {
    private EditText edtNome, edtLogin, edtSenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        edtNome = (EditText) findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText) findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText) findViewById(R.id.usuario_edtSenha);
    }

    public void sair(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void salvar(View view){

        boolean validacao = true;
        String nome = edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (nome == null || nome.equals("")){
            validacao = false;
            edtNome.setError(getString(R.string.campo_obrigatorio));
        }
        if (login == null || login.equals("")){
            validacao = false;
            edtLogin.setError(getString(R.string.campo_obrigatorio));
        }
        if (senha == null || senha.equals("") || senha.length() < 6){
            validacao = false;
            edtSenha.setError(getString(R.string.campo_obrigatorio));
        }
        if(validacao){
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            fazerCadastro(nome,login,senha);
        }
    }

    public void fazerCadastro(String nome,String login,String senha){
        Context context = getApplicationContext();
        Usuario usuario = new Usuario(nome,login,senha);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(context,usuario);

        if((usuarioNegocio.retornarUsuarioLogin(usuario))){
            usuarioNegocio.cadastro(usuario);
            Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else{
            Mensagem.Msg(this, getString(R.string.mensagem_erro));
        }
    }
}
