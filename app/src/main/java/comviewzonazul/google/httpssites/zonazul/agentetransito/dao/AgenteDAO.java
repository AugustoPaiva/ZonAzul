package comviewzonazul.google.httpssites.zonazul.agentetransito.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import comviewzonazul.google.httpssites.zonazul.agentetransito.dominio.Agente;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dao.UsuarioDAO;

public class AgenteDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Agente agente;
    UsuarioDAO usuarioDAO;
    String login;
    Context context_;
    String PREFERENCE_NAME = "LoginActivityPreferences";

    public AgenteDAO(Context context){
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public AgenteDAO(Context context, Agente agente_){
        context_ = context;
        databaseHelper = new DatabaseHelper(context);
        agente = agente_;
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

    public void salvarAgente(){
        retornoLogin();
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(login);
        valores.put(DatabaseHelper.Agentes.REGISTRO, agente.getRegistro());
        valores.put(DatabaseHelper.Agentes.MUNICIPIO, agente.getMunicipio());
        valores.put(DatabaseHelper.Agentes.USUARIO, id_usuario);
        getDatabase().insert(DatabaseHelper.Agentes.TABELA_AGENTES, null, valores);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Perfis.ID_USUARIO, id_usuario);
        cv.put(DatabaseHelper.Perfis.ID_PERFIL,"3");
        getDatabase().insert(DatabaseHelper.Perfis.TABELA_PERFIS,null ,cv);
        //getDatabase().update(DatabaseHelper.Perfis.TABELA_PERFIS, cv, "usuario="+id_usuario, null);
        getDatabase().close();
    }

    public boolean buscarAgenteMunicipio(){
        String municipio = agente.getMunicipio();
        Cursor cursor = getDatabase().query("agentes", new String[]{"*"}, "municipio=?", new String[]{municipio}, null, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        return true;
    }

    public boolean buscarAgenteEmail(){
        String email = agente.getEmail();
        Cursor cursor = getDatabase().query("agentes", new String[]{"*"}, "email=?", new String[]{email}, null, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        return true;
    }

    public Agente BuscarAgentePorUsuario(int id){
        Cursor cursor = getDatabase().query(DatabaseHelper.Agentes.TABELA_AGENTES, DatabaseHelper.Agentes.COLUNAS_AGENTES, DatabaseHelper.Agentes.USUARIO + "=? AND " + DatabaseHelper.Perfis.ID_PERFIL, new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            Agente agente = new Agente(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Agentes._ID)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Agentes.USUARIO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Agentes.EMAIL)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Agentes.REGISTRO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.Agentes.MUNICIPIO))
            );
            return agente;
        }
        return null;
    }

    public  int retornarId(String login_) {
        Cursor cursor = getDatabase().query(DatabaseHelper.Usuarios.TABELA, DatabaseHelper.Usuarios.COLUNAS, DatabaseHelper.Usuarios.LOGIN + "=?", new String[]{ login_ }, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        int id = Integer.parseInt(cursor.getString(0));
        return id;
    }

    public Agente getAgente(){
        return agente;
    }

    public void editar(Agente agente){
        retornoLogin();
        ContentValues valores = new ContentValues();
        int id_usuario = retornarId(login);
        valores.put(DatabaseHelper.Agentes.REGISTRO, agente.getRegistro());
        valores.put(DatabaseHelper.Agentes.MUNICIPIO, agente.getMunicipio());
        getDatabase().update(DatabaseHelper.Agentes.TABELA_AGENTES, valores, "usuario="+id_usuario, null);
    }
}
