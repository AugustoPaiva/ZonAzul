package comviewzonazul.google.httpssites.zonazul.estacionamento.gui;

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
import comviewzonazul.google.httpssites.zonazul.cliente.gui.ConfiguracaoClienteActivity;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

public class EstacionamentosActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    Cursor cursor;
    SimpleCursorAdapter ad;
    ListView estaciona;
    String login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamentos);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        buscarDados();
        criarListagem();
    }
    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public String getPlaca(){
        SharedPreferences preferences = getSharedPreferences("PLACA", MODE_PRIVATE);
        String placa             = preferences.getString("PLACA", null);
        return placa;
    }

    public void buscarDados(){
        String[] campos =  {DatabaseHelper.Estacionar.HORAENTRADA, DatabaseHelper.Estacionar.DATA, DatabaseHelper.Estacionar.HORASAIDA, DatabaseHelper.Estacionar.PLACA};
        cursor = getDatabase().query(DatabaseHelper.Estacionar.TABELA_ESTACIONAR, DatabaseHelper.Estacionar.COLUNAS_ESTACIONAR, DatabaseHelper.Estacionar.PLACA + "=?", new String[] {getPlaca()}, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
    }

    public void criarListagem(){
        estaciona = (ListView)findViewById(R.id.listView_estacionado);
        String[] from = {DatabaseHelper.Estacionar.DATA, DatabaseHelper.Estacionar.HORAENTRADA, DatabaseHelper.Estacionar.HORASAIDA,DatabaseHelper.Estacionar.PLACA};
        int [] to = {R.id.data, R.id.hora_entrada, R.id.hora_saida, R.id.placa};
        ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.estacionamentos,cursor,from,to);
        estaciona.setAdapter(ad);
    }

    public void voltar(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ConfiguracaoClienteActivity.class);
        startActivity(intent);
        finish();
    }
}


