package comviewzonazul.google.httpssites.zonazul.estacionamento.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.compra.gui.CompraActivity;
import comviewzonazul.google.httpssites.zonazul.estacionamento.negocio.EstacionarNegocio;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class Estacionar extends AppCompatActivity {
    private SQLiteDatabase database;
    private Cursor cursor;
    private SimpleCursorAdapter ad;
    private ListView lista;
    private String login;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionar);
        SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", MODE_PRIVATE);
        login             = preferences.getString("LOGIN", null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        final EstacionarNegocio estacionarNegocio = new EstacionarNegocio(getApplicationContext());
        buscarDados();
        Listagem();


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                cursor.moveToPosition(position);
                String placa = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.PLACA));
                if(buscarCliente().getSaldo() < 3){
                    Toast.makeText(getBaseContext(),"Você não tem creditos suficiente, recarregue.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), CompraActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    if (!estacionarNegocio.estaEstacionado(placa)) {
                        exemplo_simples();
                        estacionarNegocio.estacionar(placa, buscarCliente());
                        Toast.makeText(getBaseContext(), "Seu carro pode fica estacionado até " + estacionarNegocio.horaSaida(), Toast.LENGTH_LONG).show();
                    }
                    else{
                        exemplo_simples();
                        Toast.makeText(getBaseContext(), "Seu carro ja esta estacionado." , Toast.LENGTH_LONG).show();
                    }
                }




            }
        });
    }
    public Cliente buscarCliente(){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext());
        int id_usuario = usuarioNegocio.pegarId(login);
        ClienteNegocio clienteNegocio = new ClienteNegocio(getApplicationContext());
        Cliente cliente = clienteNegocio.retornaCliente(id_usuario);
        return cliente;
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void buscarDados(){
        cursor = getDatabase().rawQuery("SELECT A." + DatabaseHelper.Carros.PLACA + ", A." + DatabaseHelper.Carros.MODELO + ", A." + DatabaseHelper.Compra.ID + ", A."
                + DatabaseHelper.Carros.FABRICANTE + ", A." + DatabaseHelper.Carros.COR +  " FROM " + DatabaseHelper.Carros.TABELA_CARROS
                + " as A INNER JOIN " + DatabaseHelper.CarroCliente.TABELA_CARROCLIENTE +
                " as B ON A." + DatabaseHelper.Carros.ID + " = B." + DatabaseHelper.CarroCliente.ID_CARRO
                + " WHERE B." + DatabaseHelper.CarroCliente.ID_CLIENTE + " = ?", new String[] {Integer.toString(buscarCliente().getId())});

        if(cursor!=null){
            cursor.moveToFirst();

        }
    }

    public void Listagem(){
        lista = (ListView)findViewById(R.id.listView_car);
        String[] from = { DatabaseHelper.Carros.PLACA, DatabaseHelper.Carros.FABRICANTE, DatabaseHelper.Carros.MODELO, DatabaseHelper.Carros.COR};
        int [] to = {R.id.placa, R.id.fabricante, R.id.modelo, R.id.cor};
        ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.listar_carro,cursor,from,to);
        lista.setAdapter(ad);
    }

    public void voltar(View view) {
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
        startActivity(intent);
        finish();
    }

    private void exemplo_simples() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Escolha");
        //define a mensagem
        builder.setMessage("Qual é sua prerefencia em estacionamento.");
        //define um botão como positivo
        builder.setPositiveButton("Minimizar custo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Minimizar distancia", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(), "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        AlertDialog alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

