package comviewzonazul.google.httpssites.zonazul.infraestrutura;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

/**
 * Created by Augusto on 25/07/17.
 */

public class Sessao {
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    String login, senha;
    Usuario usuariologado = null;

    public Sessao() {
        usuariologado = pegarUsuario();
    }

    public Sessao(Usuario usuario) {
        usuariologado = usuario;
    }

    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public void setUsuariologado(Usuario usuariologado) {
        this.usuariologado = usuariologado;
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
           database = databaseHelper.getReadableDatabase();
        }
        return database;
    }

    public void findInformacao() {
        senha = usuariologado.getSenha();
        login = usuariologado.getLogin();
    }

    

    public void salvaUsuario() {
        findInformacao();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.Sessao.LOGIN_LOGADO, login);
        valores.put(DatabaseHelper.Sessao.SENHA_LOGADO, senha);
        database.insert(DatabaseHelper.Sessao.TABELA_SESSAO, null, valores);

    }

    public Usuario pegarUsuario() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if (exiteUsuario()) {
            Cursor cur = database.query(DatabaseHelper.Clientes.TABELA_CLIENTES, DatabaseHelper.Clientes.COLUNAS_CLIENTES, "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
            String login = cur.getString(cur.getColumnIndex("login_logado"));
            String senha = cur.getString(cur.getColumnIndex("login_senha"));
            usuariologado = new Usuario(login, senha);
            return usuariologado;
        } else {
            usuariologado = null;
            return usuariologado;
        }

    }

    public boolean exiteUsuario() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cur = database.rawQuery("SELECT COUNT(*) FROM TABELA_SESSAO", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                return false; // Tabela esta vazia, preencha com seus dados iniciais
            } else {
                return true;// Tabela ja contem dados.
            }
        }
        return false;
    }

}



