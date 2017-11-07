package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    FloatingActionButton adicionarVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        adicionarVacina = (FloatingActionButton) findViewById(R.id.Add_Vacina);

        adicionarVacina.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroDeVacinas.class));
            }
        });



    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        toolbar.setTitle(R.string.app_name);
        toolbar.setLogo(R.mipmap.ic_logo);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Settings:
                Toast.makeText(this, "Opções Gerais", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.User:
                Toast.makeText(this, "Dados do Usuário", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }
}