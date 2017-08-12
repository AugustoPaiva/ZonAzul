package comviewzonazul.google.httpssites.zonazul.infraestrutura;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tasks";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    public String getNome(){
        return BANCO_DADOS;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuarios
        db.execSQL("CREATE TABLE Usuarios ( _id INTEGER PRIMARY KEY, "
                + "nome TEXT, login TEXT, senha TEXT)");
        // Tabela de clientes
        db.execSQL("CREATE TABLE Clientes ( _id INTEGER PRIMARY KEY, "
                + "saldo TEXT, usuario TEXT, email TEXT, cep TEXT, complemento TEXT, numero TEXT, cidade TEXT, id_usuario TEXT)");
        //Cadastrar um usuario
        db.execSQL("insert into Usuarios(nome, login, senha) values('Admin', 'admin','123')");
        //Associações
        db.execSQL("CREATE TABLE Perfis ( id INTEGER PRIMARY KEY, "
                + "usuario TEXT, perfil TEXT)");
        //tabela carros
        db.execSQL("CREATE TABLE Carros ( _id INTEGER PRIMARY KEY, "
                + "fabricante TEXT, modelo TEXT, cor TEXT, ano TEXT, placa TEXT)");
        // Tabela de compras
        db.execSQL("CREATE TABLE Compra ( _id INTEGER PRIMARY KEY, "
                + "tipo TEXT, valor TEXT, id_comprador TEXT, data TEXT)");
        // Tabela de carro para cliente
        db.execSQL("CREATE TABLE CarroCliente ( _id INTEGER PRIMARY KEY, "
                + "cliente TEXT, carro TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public static class Usuarios {
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA
        };
    }

    public static class Clientes {
        public static final String TABELA_CLIENTES = "clientes";
        public static final String _ID = "_id";
        public static final String SALDO = "saldo";
        public static final String USUARIO = "usuario";
        public static final String EMAIL = "email";
        public static final String CEP = "cep";
        public static final String COMPLEMENTO = "complemento";
        public static final String NUMERO = "numero";
        public static final String CIDADE = "cidade";
        public static final String[] COLUNAS_CLIENTES = new String[]{
                _ID, SALDO, USUARIO, EMAIL, CEP, COMPLEMENTO, NUMERO, CIDADE,
        };
    }

    public static class Perfis {
        public static final String TABELA_PERFIS = "perfis";
        public static final String ID = "id";
        public static final String ID_USUARIO = "usuario";
        public static final String ID_PERFIL = "perfil";
        public static final String[] COLUNAS_PERFIS = new String[]{
                ID, ID_USUARIO, ID_PERFIL,
        };
    }
    public static class CarroCliente {
        public static final String TABELA_CARROCLIENTE = "carroCliente";
        public static final String ID = "_id";
        public static final String ID_CLIENTE = "cliente";
        public static final String ID_CARRO = "carro";
        public static final String[] COLUNAS_CARROCLIENTE = new String[]{
                ID, ID_CLIENTE, ID_CARRO,
        };
    }
    public static class Carros {
        public static final String TABELA_CARROS = "carros";
        public static final String ID = "_id";
        public static final String FABRICANTE = "fabricante";
        public static final String MODELO = "modelo";
        public static final String COR = "cor";
        public static final String ANO = "ano";
        public static final String PLACA = "placa";
        public static final String[] COLUNAS_CARRO = new String[]{
                ID, FABRICANTE, MODELO, ANO, COR, PLACA
        };
    }

    public static class Compra {
        public static final String TABELA_COMPRA = "compra";
        public static final String ID = "_id";
        public static final String TIPO = "tipo";
        public static final String VALOR = "valor";
        public static final String ID_COMPRADOR = "id_comprador";
        public static final String DATA = "data";
        public static final String[] COLUNAS_COMPRA = new String[]{
                ID, TIPO, VALOR, ID_COMPRADOR, DATA
        };
    }
}