package br.com.vaciprev.vaciperv.modelos;

import java.util.ArrayList;
import java.util.Date;

public class ListPopuladaComVacinas {

    public static ArrayList<Vacina> getList(){
        ArrayList<Vacina> vacinas = new ArrayList<>();

        vacinas.add(new Vacina("Hepatite", new Date("08/11/2016")));
        vacinas.add(new Vacina("Anti-tetânica", new Date("02/11/2016"), new Date("11/11/2017")));
        vacinas.add(new Vacina("Poliomielite", new Date("03/12/2016")));



        return vacinas;
    }
}
