package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;


public class CadastroDeVacinas extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextInputLayout IDVacina;
    EditText DataDaVacina;
    EditText DataDaSegundaDose;
    Switch SegundaDose;
    Button Salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_vacinas);

        IDVacina = (TextInputLayout) findViewById(R.id.IDVacina);
        DataDaVacina = (EditText) findViewById(R.id.DataDaVacina);
        SegundaDose = (Switch) findViewById(R.id.SegundaDose);
        DataDaSegundaDose = (EditText) findViewById(R.id.DataSegundaDose);


        Salvar = (Button) findViewById(R.id.Salvar);

        DataDaSegundaDose.setVisibility(View.INVISIBLE);


        Salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if( IDVacina.getEditText().getText().toString().length() == 0 ) {
                    IDVacina.getEditText().setError("Preencha os campos");
                }else if( DataDaVacina.getText().toString().length() == 0 ){
                    DataDaVacina.setError( "Preencha os campos" );
                }else{
                    Intent intent = new Intent(CadastroDeVacinas.this, MainActivity.class);
                    intent.putExtra("vacina_Nome", IDVacina.getEditText().getText().toString());
                    intent.putExtra("vacina_Data", DataDaVacina.getText().toString());
                    intent.putExtra("vacina_segundaDose", SegundaDose.isChecked());
                    intent.putExtra("vacina_Segunda_Data", DataDaSegundaDose.getText().toString());
                    startActivity(intent);

                    if(SegundaDose.isChecked()){
                        Toast.makeText(CadastroDeVacinas.this,
                                IDVacina.getEditText().getText().toString()+" "
                                        +DataDaVacina.getText().toString()+" "
                                        +DataDaSegundaDose.getText().toString()+" "
                                , Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(CadastroDeVacinas.this,
                                IDVacina.getEditText().getText().toString()+ " "
                                        +DataDaVacina.getText().toString()+" "
                                , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        SegundaDose.setOnCheckedChangeListener(this);


//        DataDaVacina.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    showDatePickerDialog(v);
//                }
//            }
//        });
//
//        DataDaSegundaDose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    showDatePickerDialog(v);
//                }
//            }
//        });

    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(SegundaDose.isChecked()){
            DataDaSegundaDose.setVisibility(View.VISIBLE);

        }else{
            DataDaSegundaDose.setVisibility(View.INVISIBLE);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
