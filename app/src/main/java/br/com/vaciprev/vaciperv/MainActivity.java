package br.com.vaciprev.vaciperv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initCarteiraDeVacinacao();



        initToolbar();

        initFloatButton();

        initRecyclerView();



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


        vacinaAdapter = new VacinaAdapter(MainActivity.this);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(vacinaAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbLista = dbRef.child("CarteiraDeVacinacao");

        dbLista.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vacinaAdapter.clearList();

                //todas as vacinas do banco de dados.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot dadosDoBanco : children) {

                    Vacina vacina = dadosDoBanco.getValue(Vacina.class);
                    vacina.setIdVacina(dadosDoBanco.getKey());
                    vacinaAdapter.addVacina(vacina);



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void update(Context context){


        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        toolbar.setTitle(R.string.app_name);
        toolbar.setLogo(R.mipmap.ic_logo_dark);
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
                startActivity(new Intent(MainActivity.this, PerfilDoUsuario.class));
                break;
            case R.id.Logoff:
                startActivity(new Intent(MainActivity.this, TelaLogin.class));
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
