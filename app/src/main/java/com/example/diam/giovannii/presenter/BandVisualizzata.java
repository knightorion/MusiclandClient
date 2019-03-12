package com.example.diam.giovannii.presenter;

public class BandVisualizzata {

    private String emailBand;
    private String nomeBand;

    public BandVisualizzata(String nomeBand, String emailBand){
        this.nomeBand = nomeBand;
        this.emailBand = emailBand;
    }

    public String getEmailBand() {
        return emailBand;
    }

    public String getNomeBand() {
        return nomeBand;
    }

    public void setEmailBand(String emailBand) {
        this.emailBand = emailBand;
    }

    public void setNomeBand(String nomeBand) {
        this.nomeBand = nomeBand;
    }
}
