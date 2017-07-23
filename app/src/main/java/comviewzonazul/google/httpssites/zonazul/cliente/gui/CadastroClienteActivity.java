package comviewzonazul.google.httpssites.zonazul.cliente.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import comviewzonazul.google.httpssites.zonazul.R;

/**
 * Created by Augusto on 23/07/17.
 */

public class CadastroClienteActivity {

    EditText txt_email;
    public class cadastroCliente extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro_cliente);
        }
    }

    public void  findItens(){
   //     txt_email = (EditText)findViewById(R.id.txt_email);
    }

   // public cadastrar(){}


}
