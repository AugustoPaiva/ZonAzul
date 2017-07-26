package comviewzonazul.google.httpssites.zonazul.infraestrutura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

/**
 * Created by Augusto on 25/07/17.
 */

public class Sessao {

    private static Context context;
    static String login, senha;
    private static Usuario usuariologado = null;
    static DatabaseHelper databaseHelper = new DatabaseHelper(context);
    static SQLiteDatabase database;


    public static void setContext(Context newcontext){
        context = newcontext;
    }
    public Sessao(Context context) {
        usuariologado = pegarUsuario();
    }

    public Sessao(Context context,Usuario usuario) {
        databaseHelper = new DatabaseHelper(context);
        usuariologado = usuario;
        usuariologado = pegarUsuario();
    }

    public static Usuario getUsuariologado() {
        return usuariologado;
    }

    public static void setUsuariologado(Usuario usuariologado_) {
        senha = usuariologado_.getSenha();
        login = usuariologado_.getLogin();
        usuariologado = usuariologado_;
        salvaUsuario();
    }

    private static SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }




    public static void salvaUsuario() {


        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Sessao.LOGIN_LOGADO, login);
        valores.put(DatabaseHelper.Sessao.SENHA_LOGADO, senha);
        getDatabase().insert(DatabaseHelper.Sessao.TABELA_SESSAO, null, valores);
        database.close();

    }

    public Usuario pegarUsuario() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if (existeUsuario()) {
            Cursor cur = database.query(DatabaseHelper.Clientes.TABELA_CLIENTES, DatabaseHelper.Clientes.COLUNAS_CLIENTES, "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
            String login = cur.getString(cur.getColumnIndex("login_logado"));
            String senha = cur.getString(cur.getColumnIndex("login_senha"));
            usuariologado = new Usuario(login, senha);
            database.close();
            return usuariologado;
        } else {
            usuariologado = null;
            return usuariologado;
        }

    }

    public boolean existeUsuario() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cur = database.rawQuery("SELECT COUNT(*) FROM TABELA_SESSAO", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                database.close();
                return false; // Tabela esta vazia, preencha com seus dados iniciais
            } else {
                database.close();
                return true;// Tabela ja contem dados.
            }
        }
        return false;
    }

}
