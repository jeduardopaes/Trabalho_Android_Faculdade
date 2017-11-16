package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Date;

import br.com.vaciprev.vaciperv.modelos.DateHelper;
import br.com.vaciprev.vaciperv.modelos.Vacina;
import br.com.vaciprev.vaciperv.modelos.VacinaDAO;


public class CadastroDeVacinas extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextInputLayout IDVacina;
    EditText DataDaVacina;
    EditText DataDaSegundaDose;
    Switch SegundaDose;
    Button Salvar;

    private VacinaDAO vacinaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_de_vacinas);

        initAll();

        initButtonSalvar();

    }

    public void initAll(){
        vacinaDAO = new VacinaDAO();
        IDVacina = (TextInputLayout) findViewById(R.id.IDVacina);
        DataDaVacina = (EditText) findViewById(R.id.DataDaVacina);
        SegundaDose = (Switch) findViewById(R.id.SegundaDose);
        DataDaSegundaDose = (EditText) findViewById(R.id.DataSegundaDose);

        DataDaSegundaDose.setVisibility(View.INVISIBLE);


        SegundaDose.setOnCheckedChangeListener(this);
    }

    public void initButtonSalvar(){
        Salvar = (Button) findViewById(R.id.Salvar);

        Salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                if(validacao()){

                    if(SegundaDose.isChecked()){

                        if(validacaoSegundaData()) {
                            cadastrarVacinaDuasDatas();
                            chamaOutraActivity();
                        }
                    }else{
                        cadastrarVacinaUmaData();
                        chamaOutraActivity();
                    }



                }
            }
        });

    }

    private void chamaOutraActivity(){
        Intent intent = new Intent(CadastroDeVacinas.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private void cadastrarVacinaDuasDatas(){
        String vacinaTitulo = IDVacina.getEditText().getText().toString();
        Long data = DateHelper.ordenarParaDate(DataDaVacina.getText().toString()).getTime();
        Long data2 = DateHelper.ordenarParaDate(DataDaSegundaDose.getText().toString()).getTime();

        vacinaDAO.save(new Vacina(vacinaTitulo, data, data2));

        Log.d("Salvou", "++++++SALVOU++++++++");

        Toast.makeText(CadastroDeVacinas.this,
                IDVacina.getEditText().getText().toString() + " "
                        + DataDaVacina.getText().toString() + " "
                        + DataDaSegundaDose.getText().toString() + " "
                , Toast.LENGTH_SHORT).show();
    }

    private void cadastrarVacinaUmaData(){
        String vacinaTitulo = IDVacina.getEditText().getText().toString();
        Long data = DateHelper.ordenarParaDate(DataDaVacina.getText().toString()).getTime();

        Log.d("Salvou222222","++++++SALVOU++++++++");

        vacinaDAO.save(new Vacina(vacinaTitulo, data));

        Toast.makeText(CadastroDeVacinas.this,
                IDVacina.getEditText().getText().toString()+ " "
                        +DataDaVacina.getText().toString()+" "
                , Toast.LENGTH_SHORT).show();
    }

    private boolean validacao(){
        if( IDVacina.getEditText().getText().toString().length() == 0 ) {
            IDVacina.getEditText().setError("Preencha os campos");

            return false;
        }else if( DataDaVacina.getText().toString().length() == 0
                || !DataDaVacina.getText().toString().matches("\\d{2}/\\d{2}/\\d{4}")) {
            DataDaVacina.setError("Preencha os campos - DD/MM/AAAA");

            return false;
        }else{
            return true;
        }
    }

    private boolean validacaoSegundaData(){
        if(DataDaSegundaDose.getText().toString().length() == 0
                || !DataDaSegundaDose.getText().toString().matches("\\d{2}/\\d{2}/\\d{4}")){
            DataDaSegundaDose.setError("Preencha os campos - DD/MM/AAAA");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(SegundaDose.isChecked()){
            DataDaSegundaDose.setVisibility(View.VISIBLE);

        }else{
            DataDaSegundaDose.setVisibility(View.INVISIBLE);
        }
    }

}
