package comviewzonazul.google.httpssites.zonazul.carro.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.carro.dominio.Carro;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;
import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;

public class CarroDAO {
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public boolean retornarPlaca(String placa){ //Informa se exites ou não uma placa ja cadastrada
        Cursor cursor = getDatabase().query(DatabaseHelper.Carro.TABELA_CARROS, DatabaseHelper.Carro.COLUNAS_CARRO, "placa = ?", new String[]{placa}, null, null, null);
        if(cursor.moveToFirst()){
            return true;

        }
        return false;
    }

    public String retornarCursorCarro(String placa){ //Informa se exites ou não uma placa ja cadastrada
        Cursor cursor = getDatabase().query(DatabaseHelper.Carro.TABELA_CARROS, DatabaseHelper.Carro.COLUNAS_CARRO, "placa = ?", new String[]{placa}, null, null, null);
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.PLACA));

        }
        return null;
    }

    public Carro retornarCarro(Cursor cursor){ //Retorna um carro na qual a placa ja exista no Banco de dados
        Carro carro = new Carro();
        carro.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Carro.ID)));
        carro.setAno(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.ANO)));
        carro.setCor(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.COR)));
        carro.setFabricante(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.FABRICANTE)));
        carro.setModelo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.MODELO)));
        carro.setPlaca(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carro.PLACA)));
        return carro;

    }
}
