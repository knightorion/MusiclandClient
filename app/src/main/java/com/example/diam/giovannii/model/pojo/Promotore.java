package com.example.diam.giovannii.model.pojo;

import java.util.ArrayList;

public class Promotore extends Utente {

    public Promotore(){

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Evento> getMieiEventi() {
        return mieiEventi;
    }

    public void setMieiEventi(ArrayList<Evento> mieiEventi) {
        this.mieiEventi = mieiEventi;
    }

    public ArrayList<PropostaEvento> getMieProposte() {
        return mieProposte;
    }

    public void setMieProposte(ArrayList<PropostaEvento> mieProposte) {
        this.mieProposte = mieProposte;
    }

    private String nome;
    private String cognome;
    private String sesso;
    private int age;
    private ArrayList<Evento> mieiEventi;
    private ArrayList<PropostaEvento> mieProposte;
}
