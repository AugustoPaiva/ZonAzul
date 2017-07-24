package comviewzonazul.google.httpssites.zonazul.cliente.dominio;

/**
 * Created by root on 23/07/17.
 */

public class Cliente {
    public int id;
    public String email;
    public double saldo = 0;
    public Endereco endereco;

    public Cliente(int id, String email, double saldo, Endereco endereco){

    }
    public Cliente(String email,  Endereco endereco){

    }
    public Cliente(String email){

    }
}
