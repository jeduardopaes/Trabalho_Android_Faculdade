package br.com.vaciprev.vaciperv.modelos;

/**
 * Created by jedua on 11/11/2017.
 */

import java.util.Date;

public class Vacina {

    private int idVacina;
    private String nome;
    private Date dataDeAplicacao;
    private boolean segundaDose;
    private Date dataDaSegundaDose;

    public Vacina(String nome, String dataDeAplicacao, String dataDaSegundaDose) {
        this.nome = nome;
        this.dataDeAplicacao = DateHelper.ordenarParaDate(dataDeAplicacao);
        this.segundaDose = true;
        this.dataDaSegundaDose = DateHelper.ordenarParaDate(dataDaSegundaDose);
    }

    public Vacina(String nome, String dataDeAplicacao) {
        this.nome = nome;
        this.dataDeAplicacao = DateHelper.ordenarParaDate(dataDeAplicacao);
        this.segundaDose = false;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataDeAplicacao() {
        return dataDeAplicacao;
    }

    public boolean hasSegundaDose() {
        return segundaDose;
    }

    public Date getDataDaSegundaDose() {
        return dataDaSegundaDose;
    }

    public int getIdVacina(){
        return this.idVacina;
    }

    public void setSegundaDose(boolean segundaDose){
        this.segundaDose = segundaDose;
    }


    @Override
    public String toString() {


        String vacina = "";

        vacina += this.getIdVacina()+" - ";
        vacina += this.getNome()+" - ";
        vacina += DateHelper.getDataFormated(this.getDataDeAplicacao())  +". ";

        return vacina;
    }
}
