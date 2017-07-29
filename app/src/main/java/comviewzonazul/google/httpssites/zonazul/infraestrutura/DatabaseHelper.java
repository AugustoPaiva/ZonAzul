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
                + "saldo text not null, usuario text not null, email text not null, cep text not null, complemento text not null, numero text not null, cidade text not null, id_usuario integer)");
        //Cadastrar um usuario
        db.execSQL("insert into Usuarios(nome, login, senha) values('Admin', 'admin','123')");
        //Associações
        db.execSQL("create table Perfis(id integer primary key autoincrement, "
                + "usuario text not null, perfil text not null)");
        //tabela compras
        db.execSQL("create table Compras(_id integer primary key autoincrement, "
                + "cliente text not null, valor text not null, data text not null, pagamento text not null)");

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

    public static class Compras{
        public static final String TABELA_COMPRAS = "compras";
        public static final String ID = "id";
        public static final String ID_CLIENTE = "cliente";
        public static final String VALOR = "valor";
        public static final String DATA = "data";
        public static final String PAGAMENTO = "pagamento";
        public static final String[] COLUNAS_COMPRA = new String[]{
                ID, ID_CLIENTE, VALOR, DATA, PAGAMENTO
        };
    }


}