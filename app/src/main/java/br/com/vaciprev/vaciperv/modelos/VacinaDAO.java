package br.com.vaciprev.vaciperv.modelos;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VacinaDAO {

    public void save(Vacina vacina){
        //DataBase
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbLista = dbRef.child("CarteiraDeVacinacao");

        try{
            dbLista.push().setValue(vacina);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
