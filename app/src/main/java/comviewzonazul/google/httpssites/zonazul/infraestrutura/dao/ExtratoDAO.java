/*package comviewzonazul.google.httpssites.zonazul.infraestrutura.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

/**
 * Created by root on 03/08/17.
 */
/*
public class ExtratoDAO {
    Context context;
    private ListView lista;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ExtratoDAO(Context context_){
        context = context_;
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getReadableDatabase();
        }
        return database;
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {DatabaseHelper.Compras.ID, DatabaseHelper.Compras.DATA,DatabaseHelper.Compras.VALOR, DatabaseHelper.Compras.TIPO, DatabaseHelper.Compras.ID_COMPRADOR};
        cursor = getDatabase().query(DatabaseHelper.Compras.TABELA_COMPRA, campos, null, null, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        getDatabase().close();
        return cursor;
    }

    public void gerarInformacao(){
        Cursor cursor = carregaDados();

        String[] nomeCampos = new String[] {DatabaseHelper.Compras.DATA,DatabaseHelper.Compras.ID_COMPRADOR,DatabaseHelper.Compras.TIPO,DatabaseHelper.Compras.VALOR};
        int[] idViews = new int[] {R.id.data, R.id.comprador, R.id.tipo, R.id.valor};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(context,
                R.layout.activity_extrato,cursor,nomeCampos,idViews, 0);

        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }

}
*/