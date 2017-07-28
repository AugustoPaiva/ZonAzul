package comviewzonazul.google.httpssites.zonazul.cliente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import comviewzonazul.google.httpssites.zonazul.R;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.PerfisAtivos;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.CadUsuarioActivity;
import comviewzonazul.google.httpssites.zonazul.usuario.gui.EscolhaPerfilActivity;
import util.Mensagem;

public class ClienteDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Cliente cliente;

    public ClienteDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
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
        valores.put(DatabaseHelper.Clientes.SALDO, "0");
        valores.put(DatabaseHelper.Clientes.USUARIO, cliente.getUser_id());
        valores.put(DatabaseHelper.Clientes.EMAIL, cliente.getEmail());
        valores.put(DatabaseHelper.Clientes.CEP, cliente.getEndereco().getCep());
        valores.put(DatabaseHelper.Clientes.COMPLEMENTO, cliente.getEndereco().getComplemento());
        valores.put(DatabaseHelper.Clientes.NUMERO, cliente.getEndereco().getNumero());
        valores.put(DatabaseHelper.Clientes.CIDADE, cliente.getEndereco().getCidade());
        getDatabase().insert(DatabaseHelper.Clientes.TABELA_CLIENTES, null, valores);
        salvarPerfil();
    }

    public void salvarPerfil(){
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Perfis.ID_USUARIO, Integer.toString(cliente.getUser_id()));
        valores.put(DatabaseHelper.Perfis.ID_PERFIL, "1");
        getDatabase().insert(DatabaseHelper.Perfis.TABELA_PERFIS, null, valores);
    }




    public Cliente BuscarClientePorUsuario(int id){
                Cursor cursor = getDatabase().query(DatabaseHelper.Clientes.TABELA_CLIENTES, DatabaseHelper.Clientes.COLUNAS_CLIENTES, "USUARIO = ?", new String[]{Integer.toString(id)}, null, null, null);
                Endereco endereco = new Endereco(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.NUMERO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.COMPLEMENTO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CEP)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CIDADE))
        );
        Cliente cliente = new Cliente(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Clientes._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.EMAIL)),
                endereco
        );
        return cliente;
    }




    /*
    public Usuario AcharLinha() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(cliente.getId())}, null, null, null);
        if (cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    */


}