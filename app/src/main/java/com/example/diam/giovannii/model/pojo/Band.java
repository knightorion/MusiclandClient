package com.example.diam.giovannii.model.pojo;

import java.util.ArrayList;

public class Band extends Utente {

    public Band(){

    }

    public String getNomeBand() {
        return nomeBand;
    }

    public void setNomeBand(String nomeBand) {
        this.nomeBand = nomeBand;
    }

    public int getNumeroComponenti() {
        return numeroComponenti;
    }

    public void setNumeroComponenti(int numeroComponenti) {
        this.numeroComponenti = numeroComponenti;
    }

    public ArrayList<Artista> getArtisti() {
        return artisti;
    }

    public void setArtisti(ArrayList<Artista> artisti) {
        this.artisti = artisti;
    }

    public ArrayList<String> getGeneriMusicali() {
        return generiMusicali;
    }

    public void setGeneriMusicali(ArrayList<String> generiMusicali) {
        this.generiMusicali = generiMusicali;
    }

    public ArrayList<Evento> getMieiEventi() {
        return mieiEventi;
    }

    public void setMieiEventi(ArrayList<Evento> mieiEventi) {
        this.mieiEventi = mieiEventi;
    }

    private String nomeBand;
    private int numeroComponenti;
    private ArrayList<Artista> artisti;
    private ArrayList<String> generiMusicali;
    private ArrayList<Evento> mieiEventi;

}
