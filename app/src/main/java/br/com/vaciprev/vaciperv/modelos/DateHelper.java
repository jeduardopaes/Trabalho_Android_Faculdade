package br.com.vaciprev.vaciperv.modelos;


import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public static String getDataFormated(Date data){
        DateFormat formatadorDeData = new SimpleDateFormat("dd/MM/YYYY");

        return formatadorDeData.format(data);
    }

    public static Date ordenarParaDate(String dataEmTexto){

        String[] dataQuebrada = dataEmTexto.split("/");

        Log.d("DATA EM TEXTO:", dataEmTexto);

        Date data = new Date(dataQuebrada[1]+"/"+dataQuebrada[0]+"/"+dataQuebrada[2]);

        return data;
    }

    public static Date parseParaLong(String dataEmTexto){

        String[] dataQuebrada = dataEmTexto.split("/");

        Date data = new Date(dataQuebrada[1]+"/"+dataQuebrada[0]+"/"+dataQuebrada[2]);

        return data;
    }

}
