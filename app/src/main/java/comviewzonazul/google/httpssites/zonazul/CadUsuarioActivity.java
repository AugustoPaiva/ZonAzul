package comviewzonazul.google.httpssites.zonazul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
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

        usuarioDAO = new UsuarioDAO(this);

        edtNome = (EditText) findViewById(R.id.usuario_edtNome);
        edtLogin = (EditText) findViewById(R.id.usuario_edtLogin);
        edtSenha = (EditText) findViewById(R.id.usuario_edtSenha);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

          //  }
        //});
    }

    //public void salvar(View view){
      //  cadastrar();

    //}

 /*   @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }*/
    public void sair(View view){
        startActivity(new Intent(this, LoginActivity.class));
        //setContentView(R.layout.activity_login);
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
            /*long resultado = usuarioDAO.salvarUsuario(usuario);
            if (resultado != -1){
                if(idusuario > 0){
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }else{
                    Mensagem.Msg(this, getString(R.string.mensagem_atualizar));
                }

                finish();
                startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensagem.Msg(this, getString(R.string.mensagem_erro));
            }*/
        }
    }
    public void fazerCadastro(String nome,String login,String senha){
        Context context = getApplicationContext();
        UsuarioDAO user = new UsuarioDAO(context);
        Usuario usuario = new Usuario(nome,login,senha);
        Boolean resultado = user.salvarUsuario(usuario);
        //startActivity(new Intent(this, LoginActivity.class));
        if (resultado){
            Mensagem.Msg(this, getString(R.string.mensagem_cadastro));
            finish();
            //setContentView(R.layout.activity_login);
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            Mensagem.Msg(this, getString(R.string.mensagem_erro));
        }
    }
}
