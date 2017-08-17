package comviewzonazul.google.httpssites.zonazul.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

public class UsuarioDAO {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Context context_;
    long id_usuario;
    Usuario usuario_;

    public UsuarioDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
        context_ = context;
    }
    public UsuarioDAO(Context context, Usuario usuario) {
        databaseHelper = new DatabaseHelper(context);
        context_ = context;
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public void salvarUsuario(Usuario usuario) {
        usuario_ = usuario;
        ContentValues valores = new ContentValues();
        ContentValues id = new ContentValues();
        valores.put(DatabaseHelper.Usuarios.NOME, usuario.getNome());
        valores.put(DatabaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DatabaseHelper.Usuarios.SENHA, usuario.getSenha());
        id_usuario = getDatabase().insert(DatabaseHelper.Usuarios.TABELA, null, valores);
        id.put(DatabaseHelper.Perfis.ID_USUARIO,Long.toString(id_usuario));
        id.put(DatabaseHelper.Perfis.ID_PERFIL,0);
        getDatabase().insert(DatabaseHelper.Perfis.TABELA_PERFIS,null,id);
        getDatabase().close();
        }

    public boolean buscarUsuarioPorLogin(String login) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA,
                DatabaseHelper.Usuarios.COLUNAS, "login = ?", new String[]{login}, null, null, null);
        if (!(cursor.moveToFirst())) {
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
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String nome = cursor.getString(1);
            usuario.set_id(id);
            usuario.setNome(nome);
            return usuario;
        }
        return null;
    }

    public boolean existeCliente(int idusuario) {

        Cursor cursor = getDatabase().query(DatabaseHelper.Perfis.TABELA_PERFIS, DatabaseHelper.Perfis.COLUNAS_PERFIS, DatabaseHelper.Perfis.ID_USUARIO + "=?", new String[] { String.valueOf(idusuario) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int cliente = cursor.getInt(2);

            if(cliente == 1){
                return true;
            }
            return false;
        }
        return false;
    }

    public void fechar() {
        databaseHelper.close();
        database = null;
    }

    public  int retornarId(String login_) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA, DatabaseHelper.Usuarios.COLUNAS, DatabaseHelper.Usuarios.LOGIN + "=?", new String[]{ login_ }, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }
    public void editar(Usuario usuario) {
        usuario_ = usuario;
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(usuario.getLogin());
        valores.put(DatabaseHelper.Usuarios.NOME, usuario.getNome());
        valores.put(DatabaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DatabaseHelper.Usuarios.SENHA, usuario.getSenha());
        getDatabase().update(DatabaseHelper.Usuarios.TABELA, valores,"_id="+id_usuario,null);

    }
    public boolean existePontoVenda(int idusuario){

        Cursor cursor = getDatabase().query(DatabaseHelper.Perfis.TABELA_PERFIS, DatabaseHelper.Perfis.COLUNAS_PERFIS, DatabaseHelper.Perfis.ID_USUARIO + "=?", new String[] { String.valueOf(idusuario) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int pontovenda = cursor.getInt(2);

            if(pontovenda == 2){
                return true;
            }
            return false;
        }
        return false;
    }
}
