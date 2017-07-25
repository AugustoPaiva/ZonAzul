package comviewzonazul.google.httpssites.zonazul.infraestrutura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.dominio.Session;

/**
 * Created by Augusto on 24/07/17.
 */

/*
public class SessionDAO {
    public SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;

    public SessionDAO(){
        context = Session.getContext();
        dbHelper = new DatabaseHelper(context, null);
        db = dbHelper.getWritableDatabase();
    }

    public int idLogado(){
        int id=0;
        db = dbHelper.getReadableDatabase();
        Cursor cursor=db.query("SESSION", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex("LogedUserId"));
        }
        db.close();
        return id;
    }

    public void cadastrarIdLogado(int id){
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("LogedUserId", id);
        db.insert("SESSION", null, newValues);
        db.close();
    }

    public int excluirUsuarioLogado(int Id){
        db = dbHelper.getWritableDatabase();
        int numeroDeEntradasDeletadas = db.delete("SESSION", null, null);
        db.close();
        return numeroDeEntradasDeletadas;
    }

}
*/