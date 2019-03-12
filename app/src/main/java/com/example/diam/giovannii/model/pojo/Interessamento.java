package com.example.diam.giovannii.model.pojo;

public class Interessamento {

    public Interessamento(){

    }

    public int getIdPropostaEvento() {
        return idPropostaEvento;
    }

    public void setIdPropostaEvento(int idPropostaEvento) {
        this.idPropostaEvento = idPropostaEvento;
    }

    public int getIdArtista() {
        return idArtistaInteressato;
    }

    public void setIdArtista(int idArtista) {
        this.idArtistaInteressato = idArtista;
    }

    public String getIdBand() {
        return emailBandInteressata;
    }

    public void setIdBand(String idBand) {
        this.emailBandInteressata = idBand;
    }

    private int idPropostaEvento;
    private int idArtistaInteressato;
    private String emailBandInteressata; //email band
}
