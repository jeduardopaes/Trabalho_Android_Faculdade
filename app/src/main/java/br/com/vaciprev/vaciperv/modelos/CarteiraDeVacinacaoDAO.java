package br.com.vaciprev.vaciperv.modelos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CarteiraDeVacinacaoDAO {

    final List<Vacina> vacinas = new ArrayList<Vacina>();

    public List<Vacina> carregaCarteiraDeVacina(){

        //DataBase
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbLista = dbRef.child("CarteiraDeVacinacao");

        dbLista.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //todas as vacinas do banco de dados.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot dadosDoBanco : children) {

                    Vacina vacina = dadosDoBanco.getValue(Vacina.class);

                    vacinas.add(vacina);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return vacinas;
    }


}
