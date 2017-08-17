package comviewzonazul.google.httpssites.zonazul.pontovenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.pontovenda.dominio.PontoVenda;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;

public class PontoVendaDAO {
    PontoVenda pontovenda;
    UsuarioDAO usuarioDAO;
    String login;
    Context context_;
    String PREFERENCE_NAME = "LoginActivityPreferences";
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public PontoVendaDAO(Context context) {
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }
    public PontoVendaDAO(Context context, PontoVenda pontovenda_){
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        pontovenda = pontovenda_;
    }
    public void retornoLogin(){
        SharedPreferences prefs = context_.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        login= prefs.getString("LOGIN", null);
    }
    public  int retornarId(String login_) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA, DatabaseHelper.Usuarios.COLUNAS, DatabaseHelper.Usuarios.LOGIN + "=?", new String[]{ login_ }, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }
    public void salvarPontoVenda(){
        retornoLogin();
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(login);
        valores.put(DatabaseHelper.PontoVenda.CEP, pontovenda.getEndereco().getCep());
        valores.put(DatabaseHelper.PontoVenda.NUMERO, pontovenda.getEndereco().getNumero());
        valores.put(DatabaseHelper.PontoVenda.CIDADE, pontovenda.getEndereco().getCidade());
        valores.put(DatabaseHelper.PontoVenda.COMPLEMENTO, pontovenda.getEndereco().getComplemento());
        valores.put(DatabaseHelper.PontoVenda.EMAIL, pontovenda.getEmail());
        valores.put(DatabaseHelper.PontoVenda.USUARIO, id_usuario);
        valores.put(DatabaseHelper.PontoVenda.NOME_ESTABELECIMENTO, pontovenda.getNomeEstabelecimento());
        valores.put(DatabaseHelper.PontoVenda.CNPJ, pontovenda.getCNPJ());
        getDatabase().insert(DatabaseHelper.PontoVenda.TABELA_PONTOVENDA,null,valores);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Perfis.ID_USUARIO,id_usuario);
        cv.put(DatabaseHelper.Perfis.ID_PERFIL,"2");
        getDatabase().update(DatabaseHelper.Perfis.TABELA_PERFIS, cv, "usuario="+id_usuario, null);
        getDatabase().close();
    }
}
