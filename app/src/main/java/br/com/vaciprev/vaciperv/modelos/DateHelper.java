package br.com.vaciprev.vaciperv.modelos;


import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String getDataFormated(Date data){
        DateFormat formatadorDeData = new SimpleDateFormat("dd/MM/YYYY");

        return formatadorDeData.format(data);
    }

    public static Date ordenarParaDate(String dataEmTexto){
        String day = dataEmTexto.charAt(0)+""+dataEmTexto.charAt(1);
        String month = dataEmTexto.charAt(3)+""+dataEmTexto.charAt(4);
        String year = dataEmTexto.charAt(6)+""+dataEmTexto.charAt(7)+""
                +dataEmTexto.charAt(8)+""+dataEmTexto.charAt(9);


        return new Date(month+"/"+day+"/"+year);
    }

}
