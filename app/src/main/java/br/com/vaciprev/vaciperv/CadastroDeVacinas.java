package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


public class CadastroDeVacinas extends AppCompatActivity {

    TextInputLayout IDVacina;
    TextInputLayout DataDaVacina;
    Switch SegundaDose;
    Button Salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_vacinas);

        IDVacina = (TextInputLayout) findViewById(R.id.IDVacina);
        DataDaVacina = (TextInputLayout) findViewById(R.id.DataDaVacina);
        SegundaDose = (Switch) findViewById(R.id.SegundaDose);

        Salvar = (Button) findViewById(R.id.Salvar);

        Salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroDeVacinas.this, IDVacina.getEditText().getText().toString()+ " "+DataDaVacina.getEditText().getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }




}
