package br.com.vaciprev.vaciperv;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import br.com.vaciprev.vaciperv.adapter.VacinaAdapter;
import br.com.vaciprev.vaciperv.modelos.CarteiraDeVacinacao;
import br.com.vaciprev.vaciperv.modelos.CarteiraDeVacinacaoDAO;
import br.com.vaciprev.vaciperv.modelos.Vacina;

public class MainActivity extends AppCompatActivity  {

    FloatingActionButton adicionarVacina;

    CarteiraDeVacinacao carteiraDeVacinacao;

    //para o recycler
    LinearLayoutManager linearLayoutManager;
    VacinaAdapter vacinaAdapter;
    RecyclerView recyclerView;

    //DataBase
//    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference dbLista = dbRef.child("Titulo");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initCarteiraDeVacinacao();



        initToolbar();

        initFloatButton();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                initRecyclerView();
            }
        }, 1500);
        //initRecyclerView();

    }

    public void initCarteiraDeVacinacao(){
        CarteiraDeVacinacaoDAO carteiraDeVacinacaoDAO = new CarteiraDeVacinacaoDAO();

        carteiraDeVacinacao = new CarteiraDeVacinacao(carteiraDeVacinacaoDAO.carregaCarteiraDeVacina());
    }

    public void initFloatButton(){
        adicionarVacina = (FloatingActionButton) findViewById(R.id.Add_Vacina);

        adicionarVacina.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastroDeVacinas.class);
                startActivity(i);
            }
        });
    }

    public void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        vacinaAdapter = new VacinaAdapter(MainActivity.this,
                (ArrayList<Vacina>) carteiraDeVacinacao.getCarteira());

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vacinaAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
