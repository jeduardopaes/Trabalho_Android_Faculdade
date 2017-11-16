package br.com.vaciprev.vaciperv.modelos;

import java.util.Date;

public class Vacina {

    private String idVacina;
    private String nome;
    private Long dataDeAplicacao;
    private boolean segundaDose;
    private Long dataDaSegundaDose;

    public Vacina(String nome, Long dataDeAplicacao, Long dataDaSegundaDose) {
        this.nome = nome;
        this.dataDeAplicacao = dataDeAplicacao;
        this.segundaDose = true;
        this.dataDaSegundaDose = dataDaSegundaDose;
    }

    public Vacina(String nome, Long dataDeAplicacao) {
        this.nome = nome;
        this.dataDeAplicacao = dataDeAplicacao;
        this.segundaDose = false;
    }

    public Vacina(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDataDeAplicacao() {
        return dataDeAplicacao;
    }

    public void setDataDeAplicacao(Long dataDeAplicacao) {
        this.dataDeAplicacao = dataDeAplicacao;
    }

    public boolean isSegundaDose() {
        return segundaDose;
    }

    public void setSegundaDose(boolean segundaDose) {
        this.segundaDose = segundaDose;
    }

    public Long getDataDaSegundaDose() {
        return dataDaSegundaDose;
    }

    public void setDataDaSegundaDose(Long dataDaSegundaDose) {
        this.dataDaSegundaDose = dataDaSegundaDose;
    }

    public String getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(String idVacina) {
        this.idVacina = idVacina;
    }

    @Override
    public String toString() {


        String vacina = "";

        vacina += this.getIdVacina()+" - ";
        vacina += this.getNome()+" - ";
        vacina += DateHelper.getDataFormated(new Date(this.getDataDeAplicacao()))  +". ";

        return vacina;
    }

    public boolean hasSegundaDose(){
        return this.segundaDose;
    }



}
