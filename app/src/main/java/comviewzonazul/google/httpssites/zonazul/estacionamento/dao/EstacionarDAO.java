package comviewzonazul.google.httpssites.zonazul.estacionamento.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

/**
 * Created by root on 15/08/17.
 */

public class EstacionarDAO {
    private final Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public EstacionarDAO(Context context){
        this.context = context;
    }


    public String[] ultimoEstacionamento(String placa){
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(DatabaseHelper.Estacionar.TABELA_ESTACIONAR,DatabaseHelper.Estacionar.COLUNAS_ESTACIONAR,DatabaseHelper.Estacionar.PLACA+"=?",new String[]{placa},null,null,null);
        if(cursor.getCount() ==0){//carro nunca foi estacionado
            return null;
        }
        cursor.moveToLast();
        String[] resultado = new String[]{cursor.getString(cursor.getColumnIndex(DatabaseHelper.Estacionar.DATA)),cursor.getString(cursor.getColumnIndex(DatabaseHelper.Estacionar.HORAENTRADA)),cursor.getString(cursor.getColumnIndex(DatabaseHelper.Estacionar.HORASAIDA))};
        return resultado;
    }

    public void estacionar(String placa, String data, String horaEntrada, String horaSaida){
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Estacionar.PLACA, placa);
        values.put(DatabaseHelper.Estacionar.DATA, data);
        values.put(DatabaseHelper.Estacionar.HORAENTRADA, horaEntrada);
        values.put(DatabaseHelper.Estacionar.HORASAIDA, horaSaida);
        database.insert(DatabaseHelper.Estacionar.TABELA_ESTACIONAR,null,values);
    }
}
