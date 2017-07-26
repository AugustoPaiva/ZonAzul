package comviewzonazul.google.httpssites.zonazul.cliente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

/**
 * Created by Augusto on 24/07/17.
 */

public class ClienteDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Cliente cliente;

    public ClienteDAO(Context context, Cliente cliente_){
        databaseHelper = new DatabaseHelper(context);
        cliente = cliente_;
    }
    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }


    public boolean buscarClienteEmail(){ //Arrumar um jeito dessa função retornar usuario e nao bool(para ajudar no negocio)
        Cursor cursor = getDatabase().query(DatabaseHelper.Clientes.TABELA_CLIENTES,
                DatabaseHelper.Clientes.COLUNAS_CLIENTES, "email = ?", new String[]{cliente.getEmail()}, null,null,null);

        if(!(cursor.moveToFirst())){ //quer dizer que não ha nada dentro do cursor
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public void salvarCliente(){
        ContentValues valores = new ContentValues();

        valores.put(DatabaseHelper.Clientes.EMAIL, cliente.getEmail());
        valores.put(DatabaseHelper.Clientes.CEP, cliente.getEndereco().getCep());
        valores.put(DatabaseHelper.Clientes.COMPLEMENTO, cliente.getEndereco().getComplemento());
        valores.put(DatabaseHelper.Clientes.NUMERO, cliente.getEndereco().getNumero());
        valores.put(DatabaseHelper.Clientes.CIDADE, cliente.getEndereco().getCidade());
        valores.put(DatabaseHelper.Clientes.ID_USUARIO, cliente.getUser_id());
        getDatabase().insert(DatabaseHelper.Clientes.TABELA_CLIENTES, null, valores);
    }

}
