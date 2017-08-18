package comviewzonazul.google.httpssites.zonazul.estacionamento.negocio;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import comviewzonazul.google.httpssites.zonazul.cliente.dominio.Cliente;
import comviewzonazul.google.httpssites.zonazul.compra.dao.CompraDAO;
import comviewzonazul.google.httpssites.zonazul.estacionamento.dao.EstacionarDAO;


public class EstacionarNegocio {
    private Context context;
    EstacionarDAO estacionarDAO;
    Date currentDate = new Date();
    GregorianCalendar calendar = new GregorianCalendar();


    public EstacionarNegocio(Context context){
        this.context = context;
        estacionarDAO = new EstacionarDAO(context);
    }


    public boolean estaEstacionado(String placa) {
        String[] resultado = estacionarDAO.ultimoEstacionamento(placa);
        if(resultado == null){
            return false;
        }
        String horaSaida = resultado[2];
        String horaAgora = horaEntrada();
        String[] horasDeSaida = horaSaida.split(":");
        String[] momentoHora = horaAgora.split(":");


        if(horaSaida == horaEntrada()){
            return true;
        }
        if(Integer.parseInt(horasDeSaida[0]+horasDeSaida[1])> Integer.parseInt(momentoHora[0]+momentoHora[1])){
            return true;
        }
        return false;
    }

    public void estacionar(String placa,Cliente cliente){
        estacionarDAO.estacionar(placa, pegarData(), horaEntrada(), horaSaida());
        CompraDAO compraDAO = new CompraDAO(context);
        compraDAO.atualizarSaldo(cliente,-3.00d,cliente.getSaldo());
        compraDAO.atualizarBanco(cliente,-3.00d,pegarData() + " - " + horaEntrada(),"Estacionamento");
    }

    public String pegarData(){
        calendar.setTime(currentDate);
        String txtDate = new SimpleDateFormat("dd/MM/yyyy").format(currentDate);
        return txtDate;
    }
    public String horaEntrada(){
        calendar.setTime(currentDate);
        String txtDate = new SimpleDateFormat("HH:mm").format(currentDate);
        return txtDate;
    }
    public String horaSaida(){
        calendar.setTime(currentDate);
        calendar.add(calendar.MINUTE,60);
        String txtDate = new SimpleDateFormat("HH:mm").format(calendar.getTime());
        return txtDate;
    }


}
