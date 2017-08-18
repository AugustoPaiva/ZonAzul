package comviewzonazul.google.httpssites.zonazul.cliente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Endereco;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;



public class ClienteDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Cliente cliente;
    UsuarioDAO usuarioDAO;
    String login;
    Context context_;
    String PREFERENCE_NAME = "LoginActivityPreferences";

    public ClienteDAO(Context context){
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public ClienteDAO(Context context, Cliente cliente_){
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        cliente = cliente_;
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void retornoLogin(){
        SharedPreferences prefs = context_.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        login= prefs.getString("LOGIN", null);
    }

    public boolean buscarClienteEmail(){
        String email = cliente.getEmail();
        Cursor cursor = getDatabase().query("clientes", new String[]{"*"}, "email=?", new String[]{email}, null, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        return true;
    }

    public void salvarCliente(){
        retornoLogin();
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(login);
        valores.put(DatabaseHelper.Clientes.SALDO, "0");
        valores.put(DatabaseHelper.Clientes.EMAIL, cliente.getEmail());
        valores.put(DatabaseHelper.Clientes.CEP, cliente.getEndereco().getCep());
        valores.put(DatabaseHelper.Clientes.COMPLEMENTO, cliente.getEndereco().getComplemento());
        valores.put(DatabaseHelper.Clientes.NUMERO, cliente.getEndereco().getNumero());
        valores.put(DatabaseHelper.Clientes.CIDADE, cliente.getEndereco().getCidade());
        valores.put(DatabaseHelper.Clientes.USUARIO, id_usuario);
        valores.put(DatabaseHelper.Clientes.IMAGEM, (byte[]) null);
        getDatabase().insert(DatabaseHelper.Clientes.TABELA_CLIENTES, null, valores);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Perfis.ID_USUARIO,id_usuario);
        cv.put(DatabaseHelper.Perfis.ID_PERFIL,"1");
        getDatabase().insert(DatabaseHelper.Perfis.TABELA_PERFIS,null, cv);
        getDatabase().close();
    }

    public Cliente buscarClientePorUsuario(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Clientes.TABELA_CLIENTES, DatabaseHelper.Clientes.COLUNAS_CLIENTES, DatabaseHelper.Clientes.USUARIO + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            Endereco endereco = new Endereco(
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.NUMERO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.COMPLEMENTO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CEP)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CIDADE))
            );
            Cliente cliente = new Cliente(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Clientes._ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Clientes.USUARIO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.EMAIL)),
                    cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Clientes.SALDO)),
                    endereco, cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.Clientes.IMAGEM))

            );

            return cliente;
        }
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public byte[] retonarByte(int id){
        Cliente cliente = buscarClientePorUsuario(id);
        return cliente.getImage();

    }
    public  int retornarId(String login_) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA, DatabaseHelper.Usuarios.COLUNAS, DatabaseHelper.Usuarios.LOGIN + "=?", new String[]{ login_ }, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }

    public Cliente buscarClienteEmail(String email){
        Cursor cursor = getDatabase().query("clientes", new String[]{"*"}, "email=?", new String[]{email}, null, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        return constroeCliente(cursor);
    }

    public Cliente constroeCliente(Cursor cursor){
        Endereco endereco = new Endereco(
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.NUMERO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.COMPLEMENTO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CEP)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.CIDADE))
        );
        Cliente cliente = new Cliente(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Clientes._ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Clientes.USUARIO)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Clientes.EMAIL)),
                cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.Clientes.SALDO)),
                endereco, cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.Clientes.IMAGEM))

        );
        return cliente;

    }
    public void editar(Cliente cliente, byte[] image){
        retornoLogin();
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(login);
        valores.put(DatabaseHelper.Clientes.SALDO, cliente.getSaldo());
        valores.put(DatabaseHelper.Clientes.EMAIL, cliente.getEmail());
        valores.put(DatabaseHelper.Clientes.CEP, cliente.getEndereco().getCep());
        valores.put(DatabaseHelper.Clientes.COMPLEMENTO, cliente.getEndereco().getComplemento());
        valores.put(DatabaseHelper.Clientes.NUMERO, cliente.getEndereco().getNumero());
        valores.put(DatabaseHelper.Clientes.CIDADE, cliente.getEndereco().getCidade());
        valores.put(DatabaseHelper.Clientes.USUARIO, id_usuario);
        valores.put(DatabaseHelper.Clientes.IMAGEM, image);
        getDatabase().update(DatabaseHelper.Clientes.TABELA_CLIENTES,valores,"usuario="+id_usuario,null);
    }
}

