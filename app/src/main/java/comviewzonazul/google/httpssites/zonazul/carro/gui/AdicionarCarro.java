package comviewzonazul.google.httpssites.zonazul.carro.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.carro.dominio.Carro;
import comviewzonazul.google.httpssites.zonazul.carro.negocio.CarroNegocio;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.CadUsuarioActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;
import util.Mensagem;

public class AdicionarCarro extends AppCompatActivity {
    private  String modelo, placa, cor, ano, fabricante;
    private EditText txt_modelo, txt_placa, txt_cor, txt_ano, txt_fabricante;
    private Carro carro;
    private int idCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_carro);
    }

    public void findItens(){
        SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", MODE_PRIVATE);
        String login = preferences.getString("LOGIN", null);
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext());
        ClienteNegocio clienteNegocio = new ClienteNegocio(getApplicationContext());
        int idUsuario = usuarioNegocio.pegarId(login);
        Cliente cliente = clienteNegocio.retornaCliente(idUsuario);
        idCliente = cliente.getId();
        txt_ano = (EditText)findViewById(R.id.txt_ano);
        txt_cor = (EditText)findViewById(R.id.cor);
        txt_fabricante = (EditText)findViewById(R.id.fabricante);
        txt_modelo = (EditText)findViewById(R.id.txt_modelo);
        txt_placa = (EditText)findViewById(R.id.placa);
    }

    public void montarItens(){
        modelo = txt_modelo.getText().toString();
        placa = txt_placa.getText().toString();
        cor = txt_cor.getText().toString();
        ano = txt_ano.getText().toString();
        fabricante = txt_fabricante.getText().toString();
        carro = new Carro(fabricante,modelo,cor,ano,placa);
    }

    public boolean verificacao(){
        return true;
    }

    public void adicionar(View view){
        findItens();
        montarItens();
        if(verificacao()){
            CarroNegocio carroNegocio = new CarroNegocio(getApplicationContext());
            if(carroNegocio.existePlaca(carro.getPlaca())){;
                if(carroNegocio.carrosIguais(carro)){
                    Carro carroBanco = carroNegocio.contruirCarro(placa);
                    carroNegocio.fazerLigacao(Integer.toString(idCliente),Integer.toString(carroBanco.getId()));//preciso pegar o id do cliente
                    Toast.makeText(getApplicationContext(),"Carro inserido com sucesso", Toast.LENGTH_SHORT);
                    startActivity(new Intent(this, GerenciarCarroActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Ja exites um carro cadastro com esta" +
                            "Placa, e os dados são diferentes.Tente novamente.", Toast.LENGTH_SHORT);
                }
            }
            else{
                Long idCarro = carroNegocio.adicionarCarro(carro);
                carroNegocio.fazerLigacao(Integer.toString(idCliente),Long.toString(idCarro));//preciso pegar o id do cliente
                Toast.makeText(getApplicationContext(),"Carro inserido com sucesso", Toast.LENGTH_SHORT);
                startActivity(new Intent(this, GerenciarCarroActivity.class));
                finish();
            }
        }
    }

    public void voltar(View view){
        Intent intent = new Intent();
        intent.setClass(this, GerenciarCarroActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed(View view){
        Intent intent = new Intent();
        intent.setClass(this, GerenciarCarroActivity.class);
        startActivity(intent);
        finish();
    }


}
