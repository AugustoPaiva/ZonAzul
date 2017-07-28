package comviewzonazul.google.httpssites.zonazul.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

public class UsuarioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context, Usuario usuario) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Usuario criarUsuario(Cursor cursor) {
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.SENHA))
        );
        return model;
    }

    public List<Usuario> listarUsuarios() {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, null, null, null, null, null);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public void salvarUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        ContentValues id = new ContentValues();

        valores.put(DatabaseHelper.Usuarios.NOME, usuario.getNome());
        valores.put(DatabaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DatabaseHelper.Usuarios.SENHA, usuario.getSenha());
        getDatabase().insert(DatabaseHelper.Usuarios.TABELA, null, valores);
        id.put(DatabaseHelper.Perfis.ID_USUARIO,this.retornarId(usuario.getLogin()));
        getDatabase().insert(DatabaseHelper.Perfis.TABELA_PERFIS,null,id);
    }


    public boolean removerUsuario(int id) {
        return getDatabase().delete(DatabaseHelper.Usuarios.TABELA,
                "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Usuario buscarUsuarioPorId(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "_id = ?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor.moveToNext()) {
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public boolean buscarUsuarioPorLogin(String login) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "login = ?", new String[]{login}, null, null, null);
        if (!(cursor.moveToFirst())) { //quer dizer que não ha nada dentro do cursor
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }


    public Usuario existeUsuario(String login, String senha) {  //antes se chamava "logar"
        Usuario usuario = new Usuario(login, senha);
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                null, "LOGIN = ? AND SENHA = ?", new String[]{login, senha}, null, null, null);
        if (cursor.moveToFirst()) {
            return usuario;
        }
        return null;
    }


    public boolean existeCliente(int idusuario) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Perfis.TABELA_PERFIS,
                null, "usuario = ? AND perfil = ?", new String[]{Integer.toString(idusuario),"1"}, null, null, null);
        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public void fechar() {
        databaseHelper.close();
        database = null;
    }

    public int retornarId(String login) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "login = ?", new String[]{login}, null, null, null);
        if (!(cursor.moveToFirst())) { //quer dizer que não ha nada dentro do cursor
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            cursor.close();
            return id;
        }
        cursor.close();
        return 0;
    }
}
