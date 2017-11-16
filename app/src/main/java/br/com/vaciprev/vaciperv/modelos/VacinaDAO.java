package br.com.vaciprev.vaciperv.modelos;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VacinaDAO {

    public void save(Vacina vacina){
        //DataBase
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbLista = dbRef.child("CarteiraDeVacinacao");

        Log.d("Salvando","Dados Sendo Salvos.");

        try{

            dbLista.push().setValue(vacina);
            Log.d("Salvando","Salvos.");
        }catch (Exception e){
            Log.d("Salvando","ERROR: Dados não Salvos.");
            e.printStackTrace();

        }
    }


    public static void remove(Vacina vacina){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbLista = dbRef.child("CarteiraDeVacinacao");

        dbLista.child(vacina.getIdVacina()).removeValue();
    }

}
