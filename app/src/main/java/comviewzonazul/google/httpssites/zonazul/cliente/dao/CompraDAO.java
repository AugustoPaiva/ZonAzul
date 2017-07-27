package comviewzonazul.google.httpssites.zonazul.cliente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.math.BigDecimal;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

/**
 * Created by Augusto on 27/07/17.
 */

public class CompraDAO {
    Cliente cliente;
    Context context;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public CompraDAO(Context context_){ // CLIENTE PRECISAR TER O ID NELE

        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void atualizarSaldo(Long credito, Long antigoSaldo){

        antigoSaldo = antigoSaldo + credito;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Clientes.SALDO,antigoSaldo.toString()); //These Fields should be your String values of actual column names
        getDatabase().update(DatabaseHelper.Perfis.TABELA_PERFIS, cv, "usuario="+cliente.getUser_id(), null);
    }

    public long buscarAntigoSaldo(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Clientes.TABELA_CLIENTES,
                DatabaseHelper.Clientes.COLUNAS_CLIENTES, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        return cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Clientes.SALDO));
    }


}