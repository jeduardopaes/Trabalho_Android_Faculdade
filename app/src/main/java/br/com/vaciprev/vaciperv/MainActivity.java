package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import br.com.vaciprev.vaciperv.adapter.VacinaAdapter;
import br.com.vaciprev.vaciperv.modelos.CarteiraDeVacinacao;
import br.com.vaciprev.vaciperv.modelos.ListPopuladaComVacinas;
import br.com.vaciprev.vaciperv.modelos.Vacina;

public class MainActivity extends AppCompatActivity  {

    FloatingActionButton adicionarVacina;

    CarteiraDeVacinacao carteiraDeVacinacao = new CarteiraDeVacinacao(ListPopuladaComVacinas.getList());

    //para o recycler
    LinearLayoutManager linearLayoutManager;
    //ArrayList<Vacina> vacinas;
    VacinaAdapter vacinaAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        adicionarVacina = (FloatingActionButton) findViewById(R.id.Add_Vacina);

        adicionarVacina.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastroDeVacinas.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //vacinas = ListPopuladaComVacinas.getList();

        //vacinas = new ArrayList<>();

        vacinaAdapter = new VacinaAdapter(MainActivity.this,
                (ArrayList<Vacina>) carteiraDeVacinacao.getCarteira());

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vacinaAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                boolean value = extras.getBoolean("vacina_segundaDose");
                if (value) {
                    carteiraDeVacinacao.addVacina(new Vacina(extras.getString("vacina_Nome"),
                            extras.getString("vacina_Data"),
                            extras.getString("vacina_Segunda_Data")
                    ));
                } else {
                    carteiraDeVacinacao.addVacina(new Vacina(extras.getString("vacina_Nome"),
                            extras.getString("vacina_Data")
                    ));
                }
            }catch (Exception e){
                Toast.makeText(this, "Deu Merda!!", Toast.LENGTH_SHORT).show();
            }

        }

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
