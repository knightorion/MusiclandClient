package com.example.diam.giovannii.model.pojo;

public class Partecipazione {

    public Partecipazione(){

    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdArtistaPartecipante() {
        return idArtistaPartecipante;
    }

    public void setIdArtistaPartecipante(int idArtistaPartecipante) {
        this.idArtistaPartecipante = idArtistaPartecipante;
    }

    public String getIdBandPartecipante() {
        return emailBandPartecipante;
    }

    public void setIdBandPartecipante(String idBand) {
        this.emailBandPartecipante = emailBandPartecipante;
    }

    private int idEvento;
    private int idArtistaPartecipante;
    private String emailBandPartecipante; //email band
}
