package comviewzonazul.google.httpssites.zonazul.cliente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.CadUsuarioActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import util.Mensagem;

/**
 * Created by Augusto on 24/07/17.
 */

public class ClienteDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

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
        String email = cliente.getEmail();

        Cursor cursor = getDatabase().query("clientes", new String[]{"*"}, "email=?", new String[]{email}, null, null, null, null);
        if (cursor.moveToNext()) {

            cursor.close();
            return true;
        }
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
