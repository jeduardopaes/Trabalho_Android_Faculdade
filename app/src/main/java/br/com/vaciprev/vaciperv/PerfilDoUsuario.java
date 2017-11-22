package br.com.vaciprev.vaciperv;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PerfilDoUsuario extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    EditText user;
    EditText senha;
    Spinner spinner;
    String sexo;

    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_do_usuario);

        SharedPreferences logins = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = logins.edit();

        if(logins.getAll().size()<3){
            editor.putString("Sexo", "");
            editor.commit();
        }


        initSpinner();

        user = (EditText) findViewById(R.id.text_user);
        senha = (EditText) findViewById(R.id.text_senha);

        user.setText(logins.getString("login_text_user", null));

        senha.setText(logins.getString("login_text_password", null));

        save = (Button) findViewById(R.id.btn_save);

        save.setOnClickListener(v->{

            editor.putString("login_text_user",
                    user.getText().toString());
            editor.putString("login_text_password",
                    senha.getText().toString());

            editor.commit();
            startActivity(new Intent(PerfilDoUsuario.this, MainActivity.class));
            finish();
        });



    }

    public void initSpinner(){

        SharedPreferences logins = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = logins.edit();

        spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String itemSelecionado = parentView.getItemAtPosition(position).toString();
                editor.putString("Sexo", itemSelecionado);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        sexo = logins.getString("Sexo",null);

        if(sexo.equals("Masculino"))
            spinner.setSelection(1);
        else if(sexo.equals("Feminino"))
            spinner.setSelection(2);

    }

}

