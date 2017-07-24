package comviewzonazul.google.httpssites.zonazul.infraestrutura.dominio;

/**
 * Created by Augusto on 24/07/17.
 */

import android.content.Context;

import comviewzonazul.google.httpssites.zonazul.usuario.dominio.Usuario;


public class Session {
    private static Usuario usuario;
    private static Context context;


    public static Context getContext() {
        return context;
    }
    public static void setContext(Context context) {
        Session.context = context;
    }
    public static Usuario getUsuario() {
        return usuario;
    }
    public static void setUsuario(Usuario usuario) {
        Session.usuario = usuario;
    }


}