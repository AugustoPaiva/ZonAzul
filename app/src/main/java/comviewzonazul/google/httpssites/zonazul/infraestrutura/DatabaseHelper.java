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
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuarios
        db.execSQL("create table Usuarios(_id integer primary key autoincrement, "
                + "nome text not null, login text not null, senha text not null)");
        // Tabela de clientes
        db.execSQL("create table Clientes(_id integer primary key autoincrement, "
                + "email text not null, cep text not null, complemento text not null, numero text not null, cidade text not null, id_usuario integer)");
        //Tabela de sessao
        db.execSQL("create table Sessao(_id integer primary key autoincrement, "
                + "login_logado text not null, senha_logado text not null)");
        //Cadastrar um usuario
        db.execSQL("insert into Usuarios(nome, login, senha) values('Admin', 'admin','123')");
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
        public static final String EMAIL = "email";
        public static final String CEP = "cep";
        public static final String COMPLEMENTO = "complemento";
        public static final String NUMERO = "numero";
        public static final String CIDADE = "cidade";
        public static final String ID_USUARIO = "id_usuario";


        public static final String[] COLUNAS_CLIENTES = new String[]{
                _ID, EMAIL, CEP, COMPLEMENTO, NUMERO, CIDADE, ID_USUARIO
        };
    }

    public static class Sessao {
            public static final String TABELA_SESSAO = "sessao";
            public static final String _ID = "_id";
            public static final String LOGIN_LOGADO = "login_logado";
            public static final String SENHA_LOGADO = "senha_logado";


            public static final String[] COLUNAS_SESSAO = new String[]{
                    _ID, LOGIN_LOGADO, SENHA_LOGADO
            };
        }

}
