package comviewzonazul.google.httpssites.zonazul.compra.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.math.BigDecimal;
import android.widget.Toast;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

public class CompraDAO {
    Cliente cliente;
    Context context;

    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public CompraDAO(Context context_) { // CLIENTE PRECISAR TER O ID NELE
        context = context_;
        databaseHelper = new DatabaseHelper(context_);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void atualizarSaldo(Cliente cliente_,Double credito, Double antigoSaldo) {
        Double novoSaldo = antigoSaldo + credito;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Clientes.SALDO, novoSaldo.toString());
        getDatabase().update(DatabaseHelper.Clientes.TABELA_CLIENTES, cv, "usuario=" + cliente_.getUser_id(), null);
    }

    public long buscarAntigoSaldo(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Clientes.TABELA_CLIENTES,
                DatabaseHelper.Clientes.COLUNAS_CLIENTES, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        return cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Clientes.SALDO));
    }

    public void atualizarBanco(Cliente cliente, Double credito, String data){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Compra.ID_COMPRADOR, cliente.getId());
        valores.put(DatabaseHelper.Compra.DATA, data);
        valores.put(DatabaseHelper.Compra.VALOR, credito);
        valores.put(DatabaseHelper.Compra.TIPO, "credito");
        getDatabase().insert(DatabaseHelper.Compra.TABELA_COMPRA,null,valores);
        getDatabase().close();
    }
}