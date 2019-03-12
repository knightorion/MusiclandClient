package com.example.diam.giovannii.presenter;

public class PromotoreVisualizzato {

    private String nomePromotore;
    private String emailPromotore;

    public PromotoreVisualizzato(String nomePromotore, String emailPromotore){
        this.nomePromotore = nomePromotore;
        this.emailPromotore = emailPromotore;
    }

    public String getEmailPromotore() {
        return emailPromotore;
    }

    public String getNomePromotore() {
        return nomePromotore;
    }

    public void setEmailPromotore(String emailPromotore) {
        this.emailPromotore = emailPromotore;
    }

    public void setNomePromotore(String nomePromotore) {
        this.nomePromotore = nomePromotore;
    }
}
