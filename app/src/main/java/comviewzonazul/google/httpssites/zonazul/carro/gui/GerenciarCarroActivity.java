package comviewzonazul.google.httpssites.zonazul.carro.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.gui.PrincipalClienteActivity;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class GerenciarCarroActivity extends AppCompatActivity {
    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter ad;
    ListView lista;
    String login;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_carro);
        SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", MODE_PRIVATE);
        login             = preferences.getString("LOGIN", null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        buscarDados();
        Listagem();
    }
    public int buscarIdCliente(){
        UsuarioNegocio usuarioNegocio = new UsuarioNegocio(getApplicationContext());
        int id_usuario = usuarioNegocio.pegarId(login);
        ClienteNegocio clienteNegocio = new ClienteNegocio(getApplicationContext());
        Cliente cliente = clienteNegocio.retornaCliente(id_usuario);
        return cliente.getId();
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
                + " WHERE B." + DatabaseHelper.CarroCliente.ID_CLIENTE + " = ?", new String[] {Integer.toString(buscarIdCliente())});

        if(cursor!=null){
            cursor.moveToFirst();
        }
    }

    public void Listagem(){
        lista = (ListView)findViewById(R.id.listView_car);
        String[] from = { DatabaseHelper.Carros.PLACA, DatabaseHelper.Carros.FABRICANTE,DatabaseHelper.Carros.MODELO, DatabaseHelper.Carros.COR};
        int [] to = {R.id.placa, R.id.fabricante, R.id.txt_modelo, R.id.cor};
        ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.listar_carro,cursor,from,to);
        lista.setAdapter(ad);
    }

    public void remover(View view){
        Toast.makeText(this.getApplicationContext(), DatabaseHelper.Carros.PLACA, Toast.LENGTH_SHORT).show();
    }

    public void adicionar(View view){
        Intent intent = new Intent();
        intent.setClass(this, AdicionarCarro.class);
        startActivity(intent);
        finish();

    }

    public void voltar(View view){
        startActivity(new Intent(this, PrincipalClienteActivity.class));
        finish();
    }
}
