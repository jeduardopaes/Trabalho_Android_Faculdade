package br.com.vaciprev.vaciperv.modelos;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String getDataFormated(Date data){
        DateFormat formatadorDeData = new SimpleDateFormat("dd/MM/YYYY");

        return formatadorDeData.format(data);
    }

}
