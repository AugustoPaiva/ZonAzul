package comviewzonazul.google.httpssites.zonazul.cliente.dominio;

public class Cliente {
    public int id;
    public int user_id;
    public String email;
    public double saldo = 0;
    public Endereco endereco;
    private byte[] image;

    public Cliente(int id_, int user_id_, String email_, Double saldo_, Endereco endereco_){
        id = id_;
        user_id = user_id_;
        email = email_;
        saldo = saldo_;
        endereco = endereco_;
        image = null;
    }
    public Cliente(int id_, int user_id_, String email_, Double saldo_, Endereco endereco_, byte[] image){
        id = id_;
        user_id = user_id_;
        email = email_;
        saldo = saldo_;
        endereco = endereco_;
        this.image = image;
    }

    public Cliente(int id_, String email_, Endereco endereco_){
        id = id_;
        email = email_;
        endereco = endereco_;
        image = null;
    }

    public Cliente(String email_,  Endereco endereco_, int user_id_){
        email = email_;
        endereco = endereco_;
        user_id = user_id_;
        image = null;
    }

    public Cliente(String email_){
        email = email_;
    }

    public Cliente() {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
