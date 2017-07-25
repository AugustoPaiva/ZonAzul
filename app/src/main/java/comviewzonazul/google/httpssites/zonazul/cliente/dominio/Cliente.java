package comviewzonazul.google.httpssites.zonazul.cliente.dominio;

/**
 * Created by root on 23/07/17.
 */

public class Cliente {
    public int id;
    public int user_id;
    public String email;
    public double saldo = 0;
    public Endereco endereco;

    public Cliente(int id, String email, Endereco endereco){
    }
    public Cliente(String email,  Endereco endereco, int user_id){
    }
    public Cliente(String email){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
