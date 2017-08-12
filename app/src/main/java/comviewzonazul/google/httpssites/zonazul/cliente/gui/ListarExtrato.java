package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.negocio.ClienteNegocio;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.negocio.UsuarioNegocio;

public class ListarExtrato extends AppCompatActivity {
    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter ad;
    ListView extrato;
    String login;
    boolean check;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarextrato);
        SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", MODE_PRIVATE);
        login             = preferences.getString("LOGIN", null);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        buscarDados();
        criarListagem();
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
           String[] campos =  {DatabaseHelper.Compra.ID , DatabaseHelper.Compra.DATA,DatabaseHelper.Compra.ID_COMPRADOR,DatabaseHelper.Compra.TIPO,DatabaseHelper.Compra.VALOR};
           cursor = getDatabase().query(DatabaseHelper.Compra.TABELA_COMPRA, campos, DatabaseHelper.Compra.ID_COMPRADOR + "=?", new String[] {Integer.toString(buscarIdCliente())}, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
    }

    public void criarListagem(){
        extrato = (ListView)findViewById(R.id.listView_extrat);
        String[] from = {DatabaseHelper.Compra.DATA, DatabaseHelper.Compra.ID_COMPRADOR,DatabaseHelper.Compra.TIPO, DatabaseHelper.Compra.VALOR};
        int [] to = {R.id.data, R.id.comprador, R.id.tipo, R.id.valor};
        ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.extrato,cursor,from,to);
        extrato.setAdapter(ad);
    }

    public void voltar(View view) {
        Intent intent = new Intent();
        intent.setClass(this, PrincipalClienteActivity.class);
        startActivity(intent);
        finish();
    }
}
