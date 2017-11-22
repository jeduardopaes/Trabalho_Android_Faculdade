package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class TelaLogin extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    TextInputEditText loginUsuario;
    TextInputEditText loginSenha;

    Button btnLogin;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        loginUsuario = (TextInputEditText) findViewById(R.id.txt_user);
        loginSenha = (TextInputEditText) findViewById(R.id.txt_pass);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar);

        btnRegistrar.setOnClickListener((v)->{

            SharedPreferences logins = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = logins.edit();

            editor.putString("login_text_user",
                    loginUsuario.getText().toString());
            editor.putString("login_text_password",
                    loginSenha.getText().toString());

            editor.commit();
            startActivity(new Intent(TelaLogin.this, MainActivity.class));
            finish();

        });

        btnLogin.setOnClickListener((v)->{

            String usuario = loginUsuario.getText().toString();
            String senha = loginSenha.getText().toString();

            SharedPreferences logins = getSharedPreferences(PREFS_NAME, 0);

            if(usuario.equals(logins.getString("login_text_user", null))&&
                    senha.equals(logins.getString("login_text_password",null))){
                startActivity(new Intent(TelaLogin.this, MainActivity.class));
                finish();
            }else{
                loginUsuario.setError("Usuário inválido!");
                loginSenha.setError("Senha inválida!");
            }
        });

    }
}
