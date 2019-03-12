package com.example.diam.giovannii.model.pojo;

import java.util.ArrayList;

public class PropostaEvento {

    public PropostaEvento(){

    }

    public int getIdPropostaEvento() {
        return idPropostaEvento;
    }

    public void setIdPropostaEvento(int idPropostaEvento) {
        this.idPropostaEvento = idPropostaEvento;
    }

    public String getNomePropostaEvento() {
        return nomePropostaEvento;
    }

    public void setNomePropostaEvento(String nomePropostaEvento) {
        this.nomePropostaEvento = nomePropostaEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getOrarioEvento() {
        return orarioEvento;
    }

    public void setOrarioEvento(String orarioEvento) {
        this.orarioEvento = orarioEvento;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNomeLocale() {
        return nomeLocale;
    }

    public void setNomeLocale(String nomeLocale) {
        this.nomeLocale = nomeLocale;
    }

    public ArrayList<String> getGeneriRichiesti() {
        return generiRichiesti;
    }

    public void setGeneriRichiesti(ArrayList<String> generiRichiesti) {
        this.generiRichiesti = generiRichiesti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getEmailPromotore() {
        return emailPromotore;
    }

    public void setEmailPromotore(String emailPromotore) {
        this.emailPromotore = emailPromotore;
    }

    private int idPropostaEvento;
    private String nomePropostaEvento;
    private String dataEvento;
    private String orarioEvento;
    private Indirizzo indirizzo;
    private String nomeLocale;
    private ArrayList<String> generiRichiesti;
    private String descrizione;
    private String emailPromotore; //classe Promotore?
}
