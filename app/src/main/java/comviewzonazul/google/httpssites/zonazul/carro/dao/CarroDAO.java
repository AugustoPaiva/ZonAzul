package comviewzonazul.google.httpssites.zonazul.carro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import comviewzonazul.google.httpssites.zonazul.carro.dominio.Carro;
import comviewzonazul.google.httpssites.zonazul.infraestrutura.DatabaseHelper;

public class CarroDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    Context context;

    public CarroDAO(Context context_){
        this.context = context_;
        databaseHelper = new DatabaseHelper(context_);

    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    public String retornarPlaca(String placa){ //Informa se exites ou não uma placa ja cadastrada
        Cursor cursor = getDatabase().query(DatabaseHelper.Carros.TABELA_CARROS, DatabaseHelper.Carros.COLUNAS_CARRO, DatabaseHelper.Carros.PLACA+"=?", new String[]{placa}, null, null, null);
        if(cursor.getCount() !=0){

            return placa;

        }
        return null;
    }

    public Cursor retornarCursorCarro(String placa){ //Informa se exites ou não uma placa ja cadastrada
        Cursor cursor = getDatabase().query(DatabaseHelper.Carros.TABELA_CARROS, DatabaseHelper.Carros.COLUNAS_CARRO, "placa = ?", new String[]{placa}, null, null, null);
        if(cursor.moveToFirst()){
            return cursor;

        }
        return null;
    }

    public Carro retornarCarro(String placa){ //Retorna um carro na qual a placa ja exista no Banco de dados
        Cursor cursor = retornarCursorCarro(placa);
        Carro carro = new Carro();
        carro.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Carros.ID)));
        carro.setAno(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.ANO)));
        carro.setCor(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.COR)));
        carro.setFabricante(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.FABRICANTE)));
        carro.setModelo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.MODELO)));
        carro.setPlaca(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Carros.PLACA)));
        return carro;
    }

    public Long adicionarCarro(Carro carro){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Carros.ANO, carro.getAno());
        values.put(DatabaseHelper.Carros.FABRICANTE, carro.getFabricante());
        values.put(DatabaseHelper.Carros.COR, carro.getCor());
        values.put(DatabaseHelper.Carros.MODELO, carro.getModelo());
        values.put(DatabaseHelper.Carros.PLACA, carro.getPlaca());
        long idCarro = getDatabase().insert(DatabaseHelper.Carros.TABELA_CARROS,null,values);
        //adicionarCarroExistente
        return idCarro;
    }

    public void ligacaoCarroCliente(String idCliente, String idCarro){ //Faz ligação entre cliente e carro, quando ja existe um carro no banco
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CarroCliente.ID_CARRO,idCarro);
        values.put(DatabaseHelper.CarroCliente.ID_CLIENTE,idCliente);
        getDatabase().insert(DatabaseHelper.CarroCliente.TABELA_CARROCLIENTE,null,values);
    }
}
